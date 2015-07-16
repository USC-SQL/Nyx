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


public class ColorConflictGraph {
	final double alpha = 3;
	final double beta = 1;
	final double gamma = 0.5;
	double[][] Matrix;
	Set<CCGNode> allnodes = new HashSet<CCGNode>();
	Hashtable<Integer,CCGNode> idtoNode = new Hashtable<Integer,CCGNode>();

	public ColorConflictGraph(Color background, Set<Color> foreground)
	{
		CCGNode bgnode = new CCGNode(background,0);
		idtoNode.put(bgnode.getID(), bgnode);
		allnodes.add(bgnode);
		Hashtable<Color, CCGNode> nodeTable = new Hashtable<Color, CCGNode>();
		int id = 1;
		for(Color c:foreground)
		{
				CCGNode ccgnode = new CCGNode(c,id);
				idtoNode.put(ccgnode.getID(), ccgnode);
				id++;
				allnodes.add(ccgnode);
				
		}
		Matrix = new double[allnodes.size()][allnodes.size()];
		for(int i = 0;i < allnodes.size();i++)
			for(int j = 0;j < allnodes.size();j++)
			{
				if(i ==0 || j == 0)
					Matrix[i][j] = alpha;
				else
					Matrix[i][j] = beta;

			}

	}
	public Set<CCGNode> getAllNodes() {
		return allnodes;
	}

	public double queryWight(CCGNode a, CCGNode b) {
		return Matrix[a.getID()][b.getID()];
	}
	public String toDot() {
		StringBuilder b = new StringBuilder("digraph GolorConflictGraph {\n");
		b.append("  rankdir = LR;\n");
		for(CCGNode node:allnodes)
		{
			String tagname ="\""+node.getID()+";"+node.color.toString()+"\"";
			b.append("  ").append(tagname);
			b.append("[shape=circle];\n");
			int thisid = node.getID();
			//b.append("  ").append(current.Value.getFiniteStrings());
			for (int i = 0;i < allnodes.size();i++) {
				if(i == thisid)
					continue;
				b.append("  ");
				CCGNode nextnode = idtoNode.get(i);
				//System.out.println(tagname);
				String nextsig ="\""+nextnode.getID()+";"+nextnode.color.toString()+"\"";
				b.append(tagname +" -> " + nextsig + "\n");
				b.append("[label = \"" + Matrix[thisid][i] + "\"]");

			}
		}
		return b.append("}\n").toString();
	}

	public ColorConflictGraph(HTMLContentGraph HTMLcg)
	{
		Hashtable<Color, CCGNode> nodetable = new Hashtable<Color, CCGNode>();
		Hashtable<HTMLContentNode, Set<CCGNode>> INtable = new Hashtable<HTMLContentNode, Set<CCGNode>>();
		Hashtable<HTMLContentNode, Set<CCGNode>> OUTtable = new Hashtable<HTMLContentNode, Set<CCGNode>>();
		Set<HTMLContentNode> allcontentnode = HTMLcg.getAllNodes();
		int id = 0;
		for(HTMLContentNode contentnode : allcontentnode)
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

					idtoNode.put(ccgnode.getID(), ccgnode);
					id++;
					ccgnode.addSigs(contentnode.getSig());
					nodetable.put(c, ccgnode);
					allnodes.add(ccgnode);
				}
			}
		}
		Matrix = new double[allnodes.size()][allnodes.size()];
		boolean flag = true;
		while(flag)
		{
			flag = false;
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
		System.out.println(allnodes.size());
		for(int i = 0;i < allnodes.size(); i++)
			for(int j = 0;j < allnodes.size(); j++)
			{
				Matrix[i][j] = gamma;
			}

		for(HTMLContentNode contentnode : allcontentnode)
		{
			Set<CCGNode> INset = new HashSet<CCGNode>();
			Set<CCGNode> OUTset = OUTtable.get(contentnode);
			for(HTMLContentNode p:contentnode.getPreds())
			{
				INset.addAll(OUTtable.get(p));
			}
			for(HTMLContentNode s1 : contentnode.getSuccs())
				for(HTMLContentNode s2 : contentnode.getSuccs())
				{
					if(s1 == s2)
						continue;
					for(CCGNode suc1 : OUTtable.get(s1))
						for(CCGNode suc2 : OUTtable.get(s2))
						{
							Matrix[suc1.getID()][suc2.getID()] = beta;
							Matrix[suc2.getID()][suc1.getID()] = beta;
						}
				}
			
		}

		for(HTMLContentNode contentnode : allcontentnode)
		{
			Set<CCGNode> INset = new HashSet<CCGNode>();
			Set<CCGNode> OUTset = OUTtable.get(contentnode);
			for(HTMLContentNode p : contentnode.getPreds())
			{
				INset.addAll(OUTtable.get(p));
			}
			for(CCGNode p : INset)
				for(CCGNode def : OUTset)
				{
					Matrix[p.getID()][def.getID()] = alpha;
					Matrix[def.getID()][p.getID()] = alpha;
				}
		}	
	}

    public ColorConflictGraph(XMLAdjacencyGraph xmlAg) {
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
                    idtoNode.put(ccgNode.getID(), ccgNode);
                    allnodes.add(ccgNode);
                    nodeTable.put(c, ccgNode);
                    id++;
                }
            }

        }

        Matrix = new double[allnodes.size()][allnodes.size()];
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

        for (int i = 0; i < allnodes.size(); i++)
            for (int j = 0; j < allnodes.size(); j++)
                Matrix[i][j] = gamma;

        for (XMLAdjacencyNode node : allAgNodes) {
            for (XMLAdjacencyNode s1 : node.getSuccessors())
                for (XMLAdjacencyNode s2 : node.getSuccessors()) {
                    if (s1 == s2)
                        continue;
                    for (CCGNode ccgS1 : outTable.get(s1))
                        for (CCGNode ccgS2 : outTable.get(s2)) {
                            Matrix[ccgS1.getID()][ccgS2.getID()] = beta;
                            Matrix[ccgS2.getID()][ccgS1.getID()] = beta;
                        }
                }
        }

        for (XMLAdjacencyNode node : allAgNodes) {
            Set<CCGNode> inSet = inTable.get(node);
            Set<CCGNode> outSet = outTable.get(node);
            for (CCGNode parent : inSet)
                for (CCGNode child : outSet) {
                    Matrix[parent.getID()][child.getID()] = alpha;
                    Matrix[child.getID()][parent.getID()] = alpha;
                }
        }


    }
}
