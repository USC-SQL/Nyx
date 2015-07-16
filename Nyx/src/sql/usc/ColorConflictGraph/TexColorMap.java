package sql.usc.ColorConflictGraph;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import sql.usc.Color.ARGB;
import sql.usc.Color.Color;
import sql.usc.HTMLContentGraph.*;
import sql.usc.XMLAdjacencyGraph.XMLAdjacencyGraph;
import sql.usc.XMLAdjacencyGraph.XMLAdjacencyNode;


public class TexColorMap {
	Set<CCGNode> textcolors = new HashSet<CCGNode>();
	Set<CCGNode> bgcolors = new HashSet<CCGNode>();
	Set<ConflictPair> conflicts = new HashSet<ConflictPair>();
	public TexColorMap(Color background, Set<Color> foreground)
	{
		CCGNode bgcolor = new CCGNode(background,0);
		bgcolors.add(bgcolor);
		int id = 0;
		for(Color c:foreground)
		{
			CCGNode textc = new CCGNode(c,id);
			id++;
			textcolors.add(textc);
			conflicts.add(new ConflictPair(bgcolor,textc));
		}
	}
	public Set<CCGNode> GetTextColor()
	{
		return textcolors;
	}
	public Set<CCGNode> GetBGColor()
	{
		return bgcolors;
	}
	public Set<ConflictPair> ConflictPairs()
	{
		return conflicts;
	}
	public Set<CCGNode> QueryAllConfBGColor(CCGNode text)
	{
		Set<CCGNode> r = new HashSet<CCGNode>();
		for(ConflictPair confp : conflicts)
		{
			if(confp.text.equals(text))
				r.add(confp.background);
		}
		return r;
	}
	public String toDot()
	{
		StringBuilder b = new StringBuilder("digraph TextColorMap {\n");
		b.append("  rankdir = LR;\n");
		for(CCGNode node : textcolors)
		{
			String tagname = "\"" + node.getID() + ";" + node.color.toString() + "\"";
			b.append("  ").append(tagname);
			b.append("[shape=circle];\n");
			int thisid = node.getID();
			//b.append("  ").append(current.Value.getFiniteStrings());
		}
		for(CCGNode node : bgcolors)
		{
			String tagname = "\"" + node.getID() + ";" + node.color.toString() + "\"";
			b.append("  ").append(tagname);
			b.append("[shape=box];\n");
			int thisid = node.getID();
			//b.append("  ").append(current.Value.getFiniteStrings());
		}
		for (ConflictPair conf : conflicts) {
			b.append("  ");
			String tagname = "\"" + conf.text.getID() + ";" + conf.text.color.toString() + "\"";

			//System.out.println(tagname);
			String nextsig = "\"" + conf.background.getID() + ";" + conf.background.color.toString() + "\"";
			b.append(tagname + " -> " + nextsig + "\n");
		}
		return b.append("}\n").toString();
	}
	// Constructor for HTMLs
	public TexColorMap(HTMLContentGraph HTMLcg)
	{
		Hashtable<Color, CCGNode> nodetable = new Hashtable<Color, CCGNode>();
		Hashtable<HTMLContentNode, Set<CCGNode>> INtable = new Hashtable<HTMLContentNode, Set<CCGNode>>();
		Hashtable<HTMLContentNode, Set<CCGNode>> OUTtable = new Hashtable<HTMLContentNode, Set<CCGNode>>();
		Set<HTMLContentNode> allcontentnode = HTMLcg.getAllNodes();
		int id = 0;
		for(HTMLContentNode contentnode:allcontentnode)
		{
			INtable.put(contentnode, new HashSet<CCGNode>());
			OUTtable.put(contentnode, new HashSet<CCGNode>());

			for(Color c: contentnode.getBgColors())
			{
				if(nodetable.containsKey(c))
				{
					CCGNode ccgnode = nodetable.get(c);
					ccgnode.addSigs(contentnode.getSig());
					nodetable.put(c, ccgnode);
				}
				else
				{
					CCGNode ccgnode = new CCGNode(c,id);
					id++;
					ccgnode.addSigs(contentnode.getSig());
					nodetable.put(c, ccgnode);
					bgcolors.add(ccgnode);
				}
			}
		}
		boolean flag=true;
		while(flag)
		{
			flag=false;
			for(HTMLContentNode contentnode : allcontentnode)
			{
				Set<CCGNode> INset = new HashSet<CCGNode>();
				Set<CCGNode> OUTset;
				for(HTMLContentNode p : contentnode.getPreds())
				{
					INset.addAll(OUTtable.get(p));
				}
				Set<CCGNode> GENset = new HashSet<CCGNode>();

				for(Color c : contentnode.getBgColors())
				{
					CCGNode node = nodetable.get(c);
					if(node != null)
						GENset.add(node);
				}
				if(GENset.isEmpty())
				{
					OUTset = INset;
				}
				else
				{
					OUTset = GENset;
				}
				Set<CCGNode> oldOUT = OUTtable.get(contentnode);
				if(!oldOUT.equals(OUTset))
				{
					flag = true;
					OUTtable.put(contentnode, OUTset);
				}
			}
		}
		Hashtable<Color, CCGNode> textnodetable = new Hashtable<Color, CCGNode>();
		id=bgcolors.size()+1;
		for(HTMLContentNode contentnode:allcontentnode)
		{
			Set<CCGNode> BGset=OUTtable.get(contentnode);

			for(Color c: contentnode.getTextColors())
			{
				CCGNode ctext;
				if(textnodetable.containsKey(c))
				{
					ctext=textnodetable.get(c);
					ctext.addSigs(contentnode.getSig());
					textnodetable.put(c, ctext);
				}
				else
				{
					ctext=new CCGNode(c,id);
					id++;
					ctext.addSigs(contentnode.getSig());
					textnodetable.put(c, ctext);
					textcolors.add(ctext);
				}
				for(CCGNode bg:BGset)
				{
					ConflictPair conf=new ConflictPair(bg,ctext);
					conflicts.add(conf);
				}
			}
			for(Color c: contentnode.getLinkColors())
			{
				CCGNode ctext;
				if(textnodetable.containsKey(c))
				{
					ctext=textnodetable.get(c);
					ctext.addSigs(contentnode.getSig());
					textnodetable.put(c, ctext);
				}
				else
				{
					ctext=new CCGNode(c,id);
					id++;
					ctext.addSigs(contentnode.getSig());
					textnodetable.put(c, ctext);
					textcolors.add(ctext);
				}
				for(CCGNode bg:BGset)
				{
					ConflictPair conf=new ConflictPair(bg,ctext);
					conflicts.add(conf);
				}
			}
		}
	}

	// Constructor for XMLs
	public TexColorMap(XMLAdjacencyGraph xmlAg) {
		// Temporarily store CCGNode by index
		Map<Color, CCGNode> nodeTable = new Hashtable<Color, CCGNode>();
		// inTable & outTable for future reaching definition
		Map<XMLAdjacencyNode, Set<CCGNode>> inTable = new Hashtable<XMLAdjacencyNode, Set<CCGNode>>();
		Map<XMLAdjacencyNode, Set<CCGNode>> outTable = new Hashtable<XMLAdjacencyNode, Set<CCGNode>>();

		Set<XMLAdjacencyNode> allAgNodes = xmlAg.getAllNodes();
		int id = 0;

		for (XMLAdjacencyNode agNode : allAgNodes) {

			// initialize inTable & outTable to empty set
			inTable.put(agNode, new HashSet<CCGNode>());
			outTable.put(agNode, new HashSet<CCGNode>());

			for (ARGB argb : agNode.getBackgroundColors()) {
				Color c = argb.getRgb();

				if (nodeTable.containsKey(c)) {
					CCGNode ccgNode = nodeTable.get(c);
					ccgNode.addLocation(agNode.getComponent());
					nodeTable.put(c, ccgNode);
				} else {
					CCGNode ccgNode = new CCGNode(c, id);
					ccgNode.addLocation(agNode.getComponent());
					nodeTable.put(c, ccgNode);
					id++;
					bgcolors.add(ccgNode);
				}
			}

		}

		// Use flowAnalysis to decide background color for each node
		boolean flag;
		do {
			flag = false;
			for (XMLAdjacencyNode agNode : allAgNodes) {
				Set<CCGNode> inSet = new HashSet<CCGNode>();
				Set<CCGNode> outSet;
				// Update IN set for the current node from predecessors
				for (XMLAdjacencyNode pred : agNode.getPredecessors()) {
					inSet.addAll(outTable.get(pred));
				}

				// Update inSet for current node
				inTable.put(agNode, inSet);

				Set<CCGNode> genSet = new HashSet<CCGNode>();
				for (ARGB argb : agNode.getBackgroundColors()) {
					Color c = argb.getRgb();
					CCGNode node = nodeTable.get(c);
					if (node != null) {
						genSet.add(node);
					}
				}
				// Update OUT set according to GEN set
				if (genSet.isEmpty()) {
					outSet = inSet;
				} else {
					outSet = genSet;
				}
				// check if reaches fix point
				Set<CCGNode> oldOutSet = outTable.get(agNode);

				if (!oldOutSet.equals(outSet)) {
					outTable.put(agNode, outSet);
					flag = true;
				}

			}
		} while (flag);

		Map<Color, CCGNode> textNodeTable = new Hashtable<Color, CCGNode>();
		for (XMLAdjacencyNode agNode : allAgNodes) {
			// retrieve the background for this node
			Set<CCGNode> background = outTable.get(agNode);

			for (ARGB argb : agNode.getTextColors()) {
				Color txtColor = argb.getRgb();

				// generate or find corresponding CCGNode for this text color
				CCGNode textNode = null;
				if (textNodeTable.containsKey(txtColor)) {
					textNode = textNodeTable.get(txtColor);
					textNode.addLocation(agNode.getComponent());
					textNodeTable.put(txtColor, textNode);
				} else {
					textNode = new CCGNode(txtColor, id);
					id++;
					textNode.addLocation(agNode.getComponent());
					textNodeTable.put(txtColor, textNode);
					textcolors.add(textNode);
				}

				// Store the conflict relationship
				for (CCGNode back : background) {
					ConflictPair cp = new ConflictPair(back, textNode);
					conflicts.add(cp);
				}
			}
		}
	}
}
