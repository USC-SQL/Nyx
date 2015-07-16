package sql.usc.ColorTansformScheme;

import java.util.Hashtable;
import java.util.Set;

import Debug.Debuglog;

import sql.usc.Color.Color;
import sql.usc.Color.ColorDatabase;
import sql.usc.Color.ColorDistCalculator;
import sql.usc.ColorConflictGraph.CCGNode;
import sql.usc.ColorConflictGraph.TexColorMap;

public class TextColorScheme {
	Hashtable<CCGNode, Color> transformTable =new Hashtable<CCGNode, Color>();
	public void DisplayTransform(String filename)
	{
		for(CCGNode n: transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "convert  -fuzz 10% "+ filename + " -fill \'" + n.getColor().toHexString() + "\' -opaque \'" + n.getColor().toHexString() + "\' " + filename;
			System.out.println(out);
		}
		for(CCGNode n: transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "convert "+ filename +" -fill \'"+trans.toHexString()+"\' -opaque \'"+n.getColor().toHexString() +"\' "+ filename ;
			System.out.println(out);
		}

	}
	public String GetImageScript(String filename)
	{
		String r = "";
		for(CCGNode n : transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "convert  -fuzz 10% "+ filename +" -fill \'"+n.getColor().toHexString()+"\' -opaque \'"+n.getColor().toHexString() +"\' "+ filename ;
			r += (out + "\n");
		}
		for(CCGNode n: transformTable.keySet())
		{
			Color trans= transformTable.get(n);
			String out = "convert "+ filename + " -fill \'" + trans.toHexString() + "\' -opaque \'" + n.getColor().toHexString() + "\' " + filename ;
			r += (out+"\n");
		}
		return r;

	}
	public void Display()
	{
		String tag ="text";
		for(CCGNode n: transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "texttable.put(new Color(\""+ n.getColor().toHexString()+"\"), new Color(\""+trans.toHexString()+"\"));";
			System.out.println(out);
		}
	}
	public String getJavaCode()
	{
		String r = "";
		for(CCGNode n: transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "texttable.put(new Color(\""+ n.getColor().toHexString()+"\"), new Color(\""+trans.toHexString()+"\"));";
			r += (out + "\n");
		}
		return r;
	}
	private double subH(Set<CCGNode> bgs, BackgroundColorScheme bgt, Color newtext, Color oldtext)
	{
		double h = 0;
		for(CCGNode bg : bgs)
		{
				Color bgcolor = bg.getColor();
				Color transBgcolor = bgt.QueryTransformedColor(bg);
				double originalDist = ColorDistCalculator.CalDist(bgcolor, oldtext);
				double transDist = ColorDistCalculator.CalDist(transBgcolor, newtext);

				h += (originalDist - transDist) * (originalDist - transDist);
		}
		return h;
	}

	public TextColorScheme(TexColorMap tcm, BackgroundColorScheme bgt)
	{
		Set<CCGNode> texts = tcm.GetTextColor();
		Set<CCGNode> bg = tcm.GetBGColor();
		ColorDatabase codb = new ColorDatabase();

		for(CCGNode node : texts)
		{
			Set<CCGNode> conflictbg = tcm.QueryAllConfBGColor(node);
			double minsubh = Double.MAX_VALUE;
			Color bestColor = null;
			for(Color c:codb.GetAllColor())
			{
				double temp=subH(conflictbg, bgt, c, node.getColor());
				if(temp<minsubh)
				{
					minsubh=temp;
					bestColor=c;
				}
			}
			Debuglog.log(node.getColor());
			Debuglog.log(bestColor);
			transformTable.put(node, bestColor);
		}


		
	}
}
