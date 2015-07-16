package sql.usc.XMLAdjacencyGraph;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import soot.SootClass;
import soot.SootMethod;
import sql.usc.Color.Color;
import sql.usc.ColorConflictGraph.ColorConflictGraph;
import sql.usc.ColorTansformScheme.BackgroundColorScheme;
import sql.usc.Soot.AndroidApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mianwan on 7/14/15.
 */
public class AdjacencyTest {
    private static final String androidJar = "/home/mianwan/IdeaProjects/libs";
    private static final String apkPath = "/home/mianwan/IdeaProjects/apps/toy/colors.apk";
    private static final String classList = "/home/mianwan/IdeaProjects/apps/toy/classList.txt";

    public static void main(String[] args) {
        String appRootDir = "/home/mianwan/IdeaProjects/colors/";
        Map<String, String> activityThemes = getApplicationTheme(appRootDir, 21);

        AndroidApp app = new AndroidApp(androidJar, apkPath, classList);

        // ************** part of future work ************************
//        for (String k : activityThemes.keySet()) {
//            SootClass sc = app.getAllClasses().get(k);
//            SootMethod onCreate = sc.getMethodByName("onCreate");
//            Body body = onCreate.retrieveActiveBody();
//            Chain<Unit> units = body.getUnits();
//            Iterator<Unit> it = units.iterator();
//            Value layoutId = null;
//            while (it.hasNext()) {
//                Stmt stmt = (Stmt) it.next();
//                if (stmt instanceof InvokeStmt) {
//                    if (stmt.getInvokeExpr().getMethod().getName().equals("setContentView")) {
//                        layoutId = stmt.getInvokeExpr().getArg(0);
//                        break;
//                    }
//                }
//            }
//
//        }
//
//        SootClass R = app.getAllClasses().get("com.example.colors.R$layout");
//        getLayoutMap(R);

        String xmlPath = "/home/mianwan/IdeaProjects/colors" + "/res/layout/" + "main.xml";
        SAXReader reader = new SAXReader();
        XMLAdjacencyGraph ag = new XMLAdjacencyGraph(xmlPath);
        ColorConflictGraph ccg = new ColorConflictGraph(ag);
        BackgroundColorScheme bgScheme = new BackgroundColorScheme(ccg, new Color(0xee, 0xee, 0xee));

        FileWriter fw = null;
        try {
            fw = new FileWriter("/home/mianwan/Current/Adjacency.dot");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ag.toDot());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Map<String, String> getApplicationTheme(String appRootDir, int androidVersion) {
        final String manifestFile = "AndroidManifest.xml";
        Map<String, String> activityThemeMap = new HashMap<String, String>();
        SAXReader sr = new SAXReader();
        String appThemeName = "";
        try {
            // Read package info from root <manifest>
            Document doc = sr.read(new File(appRootDir + manifestFile));
            Element root = doc.getRootElement();
            String pkg = root.attributeValue("package");

            // Visit the first and only <application> tag
            Element application = root.element("application");
            Attribute themeAttr = application.attribute("theme");
            if (themeAttr != null) {
                appThemeName = themeAttr.getValue();
            } else {
                // Default theme name for target android version
                if (androidVersion < 10)
                    appThemeName = "@android:style/Theme";
                else if (androidVersion < 21)
                    appThemeName = "@android:style/Theme.Holo";
                else
                    appThemeName = "@android:style/Theme.Material";
            }

            // Visit all the <activity> tags
            List<Element> activities = application.elements("activity");
            Iterator<Element> activityIt = activities.iterator();
            while (activityIt.hasNext()) {

                Element act = activityIt.next();
                String actName = act.attributeValue("name");
                if (!actName.contains(pkg)) {
                    actName = pkg + "." + actName.replace(".", "");
                }

                String actThemeName;
                if (act.attribute("theme") != null) {
                    actThemeName = act.attributeValue("theme");
                } else {
                    actThemeName = appThemeName;
                }

                activityThemeMap.put(actName, actThemeName);
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return activityThemeMap;
    }

    public static Map<Integer, String> getLayoutMap(SootClass sc) {
        for (SootMethod med : sc.getMethods()) {
            System.out.println(med);
        }

//        SootMethod sm = sc.getMethodByName("<init>");
//        Body body = sm.retrieveActiveBody();
//        Chain<Unit> units = body.getUnits();
//        Iterator<Unit> it = units.iterator();
//        while (it.hasNext()) {
//            Stmt stmt = (Stmt) it.next();
//            System.out.println(stmt);
//        }
        return null;
    }
}
