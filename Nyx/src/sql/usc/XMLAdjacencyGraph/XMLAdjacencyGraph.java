package sql.usc.XMLAdjacencyGraph;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sql.usc.Color.ARGB;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mianwan on 7/14/15.
 */
public class XMLAdjacencyGraph {
    private String sourceXml = "";
    private Set<XMLAdjacencyNode> heads = new HashSet<XMLAdjacencyNode>();
    private Set<XMLAdjacencyNode> allNodes = new HashSet<XMLAdjacencyNode>();

    public Set<XMLAdjacencyNode> getHeads() {
        return heads;
    }

    public Set<XMLAdjacencyNode> getAllNodes() {
        return allNodes;
    }

    public XMLAdjacencyGraph(String layoutPath) {
        File xml = new File(layoutPath);
        SAXReader reader = new SAXReader();
        try {
            Document layoutDoc = reader.read(xml);

            sourceXml = xml.getName();
            // if windowBackground != @null
            String window = "PhoneWindow$DecorView";
            XMLAdjacencyNode root = new XMLAdjacencyNode(window);
            // -----decide the top-level mDecor's style----
            // *******code for finding windowBackground******


            ARGB windowBackground = new ARGB("#ffeeeeee"); // colorBackground
            root.getBackgroundColors().add(windowBackground);
            ARGB titleColor = new ARGB("#de000000");
            root.getTextColors().add(titleColor);

            XMLAdjacencyNode subRoot = generateNodesfromLayout(layoutDoc);
            root.getSuccessors().add(subRoot);
            subRoot.getPredecessors().add(root);
            heads.add(root);
            allNodes.add(root);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

//        Set<ARGB> rootBg = root.getBackgroundColors();
//        rootBg.add(windowBackground);
//
//
//
//        return root;

    }

    public XMLAdjacencyNode generateNodesfromLayout (Document doc) {
        Element rootElement = doc.getRootElement();
        XMLAdjacencyNode root = visitChildren(rootElement);
        return root;
    }

    public XMLAdjacencyNode visitChildren(Element e) {
        String tag = e.getName();
        String xpath = e.getPath();
        XMLAdjacencyNode parent = new XMLAdjacencyNode(xpath);


        System.out.println(parent.getId() + " " + parent.getComponent());
        for (Attribute a : e.attributes()) {
            System.out.println(a.getQName().getNamespace() + ":" + a.getQName().getName());
        }

        // ------- decide style of each comp ----------
        if (tag.contains("Layout")) {
            if (e.attributeValue("background") != null) {
                String background = e.attributeValue("background");
                // ******* parse user's RGB value ***********
                if (background.startsWith("#"))
                    parent.getBackgroundColors().add(new ARGB(e.attributeValue(background)));
                else if (background.startsWith("@color")) {

                } else if (background.startsWith("@drawable")) {

                }
            }
        }
        if (tag.equals("TextView")) {
            // *******code for find textColor****
            if (e.attributeValue("textColor") != null) {
                parent.getTextColors().add(new ARGB(e.attributeValue("textColor")));
            } else {
                parent.getTextColors().add(new ARGB("#8a000000"));
            }

            // Default no background, but if user assign one
            if (e.attributeValue("background") != null) {
                String background = e.attributeValue("background");
                if (background.startsWith("#"))
                    parent.getBackgroundColors().add(new ARGB(e.attributeValue(background)));
                else if (background.startsWith("@color")) {

                } else if (background.startsWith("@drawable")) {

                }
            }
        }

        if (tag.equals("Button")) {
            // *********** code for find textColor & background **********
            if (e.attributeValue("textColor") != null) {
                parent.getTextColors().add(new ARGB(e.attributeValue("textColor")));
            } else {
                parent.getTextColors().add(new ARGB("#de000000"));
            }

            // Default no background, but if user assign one
            if (e.attributeValue("background") != null) {
                String background = e.attributeValue("background");
                if (background.startsWith("#"))
                    parent.getBackgroundColors().add(new ARGB(e.attributeValue(background)));
                else if (background.startsWith("@color")) {

                } else if (background.startsWith("@drawable")) {

                }
            } else {
                parent.getBackgroundColors().add(new ARGB("#ffd6d7d7"));
            }
        }

        if (tag.equals("EditText")) {
            // *********** code for find textColor & background **********
            if (e.attributeValue("textColor") != null) {
                parent.getTextColors().add(new ARGB(e.attributeValue("textColor")));
            } else {
                parent.getTextColors().add(new ARGB("#de000000"));
            }

            // Default no background, but if user assign one
            if (e.attributeValue("background") != null) {
                String background = e.attributeValue("background");
                if (background.startsWith("#"))
                    parent.getBackgroundColors().add(new ARGB(e.attributeValue(background)));
                else if (background.startsWith("@color")) {

                } else if (background.startsWith("@drawable")) {

                }
            }
        }

        Set<XMLAdjacencyNode> succSet = new HashSet<XMLAdjacencyNode>();
        // Visit children recursively
        List<Element> childList = e.elements();
        for (Element childElement : childList) {
            XMLAdjacencyNode childNode = visitChildren(childElement);
            childNode.getPredecessors().add(parent);
            succSet.add(childNode);
        }
        parent.getSuccessors().addAll(succSet);
        allNodes.add(parent);

        return parent;
    }

    public String toDot() {
        StringBuilder dotGraph = new StringBuilder();
        dotGraph.append("digraph directed_graph {\n\tlabel=\"" + sourceXml + "\";\n");
        dotGraph.append("\tlabelloc=t;\n");
        for (XMLAdjacencyNode node : allNodes) {
            dotGraph.append("\t" + node.toDot()+ "\n");
        }

        for (XMLAdjacencyNode source : allNodes) {
            for (XMLAdjacencyNode target : source.getSuccessors()) {
                dotGraph.append("\t");
                dotGraph.append(source.getId() + " -> " + target.getId());
                dotGraph.append("\n");
            }
        }
        dotGraph.append("}\n");
        return dotGraph.toString();
    }
}
