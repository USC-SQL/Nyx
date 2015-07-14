package sql.usc.Color;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.*;
import java.util.*;

/**
 * Created by mianwan on 7/14/15.
 */
public class AndroidColorDB {
    private static Map<String, ARGB> colorMap;
    private static Map<String, HashSet<ARGB>> colorSetMap;
    private static final String RESOURCE = "resources";
    private static final String COLOR = "color";
    private static final String SELECTOR = "selector";
    private static final String ITEM = "item";
    static {
        colorMap = new HashMap<String, ARGB>();
        colorSetMap= new HashMap<String, HashSet<ARGB>>();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        String resRootDir = "/home/mianwan/Current/ReverseApk/";
        SAXReader reader = new SAXReader();
        File xml = new File(resRootDir +"framework-res/res/values/colors.xml");
        try {
            Document doc = reader.read(xml);
            Element root = doc.getRootElement();
            if (!root.getName().equals(RESOURCE)) {
                System.err.println("This is not a resource xml file");
                return;
            }

            Iterator<Element> it = root.elementIterator();
            Map<String, String> cacheMap = new HashMap<String, String>();

            // --------------Phase 1: define color directly using #AARRGGBB----------
            while (it.hasNext()) {
                Element entry = it.next();
                if (entry.getName().equals(COLOR)) {
                    String colorName = entry.attributeValue("name");
                    String value = entry.getText();
                    ARGB color = null;
//                    System.out.println(colorName);
                    // this is a simple definition
                    if (value.startsWith("#")) {
                        color = new ARGB(value);
                        colorMap.put(colorName, color);
                        // cache this referred color
                    } else if (value.startsWith("@color")) {
                        String referee = value.split("/")[1];
                        cacheMap.put(colorName, referee);
                    }
                }

            }

            int beforeSize = 0;
            int afterSize = 0;
            // -----------------Phase 2: Parse the referred color in this color.xml-------------
            do {
                Set<String> foundColorSet = new HashSet<String>();
                for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
                    String srcColor = entry.getKey();
                    String targetColor = entry.getValue();
                    ARGB foundColor = colorMap.get(targetColor);
                    if (foundColor != null) {
                        colorMap.put(srcColor, foundColor);
                        foundColorSet.add(srcColor);
                    }
                }
                beforeSize = cacheMap.size();
                // Remove color already founded
                if (foundColorSet.size() > 0) {
                    for (String key : foundColorSet) {
                        cacheMap.remove(key);
                    }
                }
                afterSize = cacheMap.size();
//                System.out.println(beforeSize - afterSize);
            } while (beforeSize - afterSize > 0);

            // -------------------- Phase 3: Parse color defined in XML ----------------------------
            File folder = new File(resRootDir + "framework-res/res/color/");
            File[] files = folder.listFiles();
            for (File f : files) {
                String fileName = f.getName();
                if(!fileName.contains(".xml"))
                    continue;
                String srcColor = fileName.substring(0, fileName.length() - ".xml".length());
//                System.out.println(srcColor);
                SAXReader subReader = new SAXReader();
                Document subDoc = subReader.read(f);
                Element subRoot = subDoc.getRootElement();

                Iterator<Element> subIt = subRoot.elementIterator();
                HashSet<ARGB> colorSet = new HashSet<ARGB>();
                while (subIt.hasNext()) {
                    Element subEle = subIt.next();
                    if (subEle.getName().equals(ITEM)) {
                        String refereeColor = subEle.attributeValue("color");
//                            System.out.println(refereeColor);
                        if (refereeColor.contains("@color")) {
                            String subKey = refereeColor.split("/")[1];
                            ARGB subColor = colorMap.get(subKey);
                            if (subColor != null) {
                                colorSet.add(subColor);
                            } else {
                                throw new RuntimeException("Cannot find color!");
                            }
                        } else {
                            ARGB color = new ARGB(refereeColor);
                            colorSet.add(color);
                        }
                    }
                }
//                System.out.println(srcColor + "\t" + colorSet);
                colorSetMap.put(srcColor, colorSet);
            }

            // -----------Phase 4: Parse the referred color in /color folder(a.k.a <selector>)------------
            if (cacheMap.size() > 0) {
                for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
                    String srcColor = entry.getKey();
                    String targetColor = entry.getValue();

                    HashSet<ARGB> colorSet = colorSetMap.get(targetColor);
                    if (colorSet == null) {
                        throw new RuntimeException("Referring an undefined color.");
                    } else {
//                        System.out.println(srcColor + "\t" + colorSet);
                        colorSetMap.put(srcColor, colorSet);
                    }
                }
            }
            System.out.println(colorMap.size());
            System.out.println(colorSetMap.size());

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/home/mianwan/Current/color.txt")));
                for (Map.Entry<String, ARGB> entry : colorMap.entrySet()) {
                    bw.write(entry.getKey() + "\t" + entry.getValue().toHexString());
                    bw.write("\n");
                }
                for (Map.Entry<String, HashSet<ARGB>> e : colorSetMap.entrySet()) {
                    bw.write(e.getKey() + "\t");
                    bw.write("[");
                    String strColor = "";
                    for (ARGB color : e.getValue()) {
                        strColor += color.toHexString();
                        strColor += ",";
                    }
                    // remove last ","
                    strColor = strColor.substring(0, strColor.lastIndexOf(","));
                    bw.write(strColor);
                    bw.write("]");
                    bw.write("\n");
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
