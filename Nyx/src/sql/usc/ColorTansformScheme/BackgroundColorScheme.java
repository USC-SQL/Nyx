package sql.usc.ColorTansformScheme;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import sql.usc.Color.Color;
import sql.usc.Color.ColorDatabase;
import sql.usc.Color.ColorDistCalculator;
import sql.usc.ColorConflictGraph.CCGNode;
import sql.usc.ColorConflictGraph.ColorConflictGraph;
import java.util.Random;

public class BackgroundColorScheme {
	Hashtable<CCGNode, Color> transformTable;
	public Color QueryTransformedColor(CCGNode key)
	{
		return transformTable.get(key);
	}

	private double averDiff(ColorConflictGraph ccg, Hashtable<CCGNode, Color> transformTable)
	{
		double h = 0;
		int cnt = 0;
		for(CCGNode ccgnode1 : ccg.getAllNodes())
			for(CCGNode ccgnode2 : ccg.getAllNodes())
			{
				cnt++;
				if(ccgnode2 == ccgnode1)
					continue;
					Color originalC1 = ccgnode1.getColor();
					Color originalC2 = ccgnode2.getColor();
					Color transformedC1 = transformTable.get(ccgnode1);
					Color transformedC2 = transformTable.get(ccgnode2);
					double w = ccg.queryWight(ccgnode1, ccgnode2);

					double originalDist = ColorDistCalculator.CalDist(originalC1, originalC2);
					double transformedDist = ColorDistCalculator.CalDist(transformedC1, transformedC2);
					h += Math.sqrt((originalDist - transformedDist) * (originalDist - transformedDist));

			}
		return h / cnt;
	}

	private double averageDist(ColorConflictGraph ccg)
	{
		double ac = 0;
		double cnt = 0;
		for(CCGNode ccgnode1 : ccg.getAllNodes())
			for(CCGNode ccgnode2 : ccg.getAllNodes())
			{
				if(ccgnode2 == ccgnode1)
					continue;
				cnt++;
				Color originalC1 = ccgnode1.getColor();
				Color originalC2 = ccgnode2.getColor();
				double originalDist = ColorDistCalculator.CalDist(originalC1, originalC2);
				ac += Math.sqrt(originalDist * originalDist);
			}
		return ac / cnt;
	}

	private double accumulateDist(ColorConflictGraph ccg)
	{
		double ac = 0;
		for(CCGNode ccgnode1 : ccg.getAllNodes())
			for(CCGNode ccgnode2 : ccg.getAllNodes())
			{
				if(ccgnode2 == ccgnode1)
					continue;
					Color originalC1 = ccgnode1.getColor();
					Color originalC2 = ccgnode2.getColor();
					double w = ccg.queryWight(ccgnode1, ccgnode2);

					double originalDist = ColorDistCalculator.CalDist(originalC1, originalC2);
					ac += w * originalDist * originalDist;
			}
		return ac;
	}

	// Mutate
	private Hashtable<CCGNode, Color>  findNext(Hashtable<CCGNode, Color> current, CCGNode root)
	{
		int delta = 4;
		Hashtable<CCGNode, Color> cloneTable = new Hashtable<CCGNode, Color>();
		for(CCGNode key:current.keySet())
			cloneTable.put(key, new Color(current.get(key)));
		Calendar clock1 = Calendar.getInstance();
		long time1 = clock1.getTimeInMillis();
		Random generator = new Random(time1);
		for(CCGNode key:cloneTable.keySet())
		{
			if(key == root)
				continue;
			Color c = cloneTable.get(key);
			int cR = c.getR();
			int cG = c.getG();
			int cB = c.getB();

			int sign = 0;
		 	sign = generator.nextInt(3) - 1;
		 	cR += sign * delta;
		 	c.setR(cR);
			sign = generator.nextInt(3) - 1;
			cG += sign * delta;
			c.setG(cG);
			sign = generator.nextInt(3) - 1;
			cB += sign * delta;
			c.setB(cB);
		}
		return cloneTable;
	}

	private double H(ColorConflictGraph ccg, Hashtable<CCGNode, Color> transformTable)
	{
		double h = 0;
		for(CCGNode ccgnode1:ccg.getAllNodes())
			for(CCGNode ccgnode2:ccg.getAllNodes())
			{
				if(ccgnode2 == ccgnode1)
					continue;
					Color originalC1 = ccgnode1.getColor();
					Color originalC2 = ccgnode2.getColor();
					Color transformedC1 = transformTable.get(ccgnode1);
					Color transformedC2 = transformTable.get(ccgnode2);
					double w = ccg.queryWight(ccgnode1, ccgnode2);

					double originalDist = ColorDistCalculator.CalDist(originalC1, originalC2);
					double transformedDist = ColorDistCalculator.CalDist(transformedC1, transformedC2);
					h += w * (originalDist - transformedDist) * (originalDist - transformedDist);

			}
		return h;
	}

	public void DisplayTransform(String filename)
	{
		for(CCGNode n : transformTable.keySet())
		{
			Color trans = transformTable.get(n);
			String out = "convert -fuzz 8000 " + filename + " -fill \'" + trans.toHexString() + "\' -opaque \'" + n.getColor().toHexString() +"\' "+ filename;
			System.out.println(out);
		}

	}

	public void Display()
	{
		String tag="backgournd";

		for(CCGNode n: transformTable.keySet())
		{
			Color trans= transformTable.get(n);
			String out="bgtable.put(new Color(\""+ n.getColor().toHexString()+"\"), new Color(\""+trans.toHexString()+"\"));";
			System.out.println(out);
		}

	}

	public String getJavaCode(){
		String r="";

		for(CCGNode n: transformTable.keySet())
		{
			Color trans= transformTable.get(n);
			String out="bgtable.put(new Color(\""+ n.getColor().toHexString()+"\"), new Color(\""+trans.toHexString()+"\"));";
			r+=(out+"\n");
		}
		return r;
	}

	public String getPerlCode(){
		String r="";

		for(CCGNode n: transformTable.keySet())
		{
			Color trans= transformTable.get(n);
			String out="bgtable.put(new Color(\""+ n.getColor().toHexString()+"\"), new Color(\""+trans.toHexString()+"\"));";
			r+=(out+"\n");
		}
		return r;
	}
	// Evaluation function: the sum of weighed color distance
	private double H(Set<CCGNode> workset, ColorConflictGraph ccg, Hashtable<CCGNode, Color> transformTable)
	{
		double h=0;
		for(CCGNode ccgnode1:workset)
			for(CCGNode ccgnode2:workset)
			{
				if(ccgnode2 == ccgnode1)
					continue;
					Color originalC1 = ccgnode1.getColor();
					Color originalC2 = ccgnode2.getColor();
					Color transformedC1 = transformTable.get(ccgnode1);
					Color transformedC2 = transformTable.get(ccgnode2);
					double w = ccg.queryWight(ccgnode1, ccgnode2);

					double originalDist = ColorDistCalculator.CalDist(originalC1, originalC2);
					double transformedDist = ColorDistCalculator.CalDist(transformedC1, transformedC2);
					h += w * (originalDist - transformedDist) * (originalDist - transformedDist);

			}
		return h;
	}

	public BackgroundColorScheme(ColorConflictGraph ccg, Color bg)
	{
		CCGNode root = null;
		ColorDatabase codb = new ColorDatabase();
		Hashtable<CCGNode, Color> tmpTransformTable = new Hashtable<CCGNode, Color>();

		for(CCGNode ccgnode:ccg.getAllNodes())
		{
			if(ccgnode.getColor().equals(bg))//change it to the color you want to adapt
				root=ccgnode;
			tmpTransformTable.put(ccgnode, new Color(0, 0, 0));
		}
		// initialize the value with greedy
		Set<CCGNode> WorkSet = new HashSet<CCGNode>();
		Set<CCGNode> WhiteSet = new HashSet<CCGNode>();
		WorkSet.add(root);
		WhiteSet.addAll(ccg.getAllNodes());
		WhiteSet.remove(root);
		while(!WhiteSet.isEmpty())
		{
			CCGNode nextNode = null;
			double largestValue = 0;
			// Find the node having largest weight as nextNode
			for(CCGNode n : WorkSet)
				for(CCGNode w : WhiteSet)
				{
					double wight = ccg.queryWight(n, w);
					if(wight > largestValue)
					{
						largestValue = wight;
						nextNode = w;
					}
				}
			WhiteSet.remove(nextNode);
			WorkSet.add(nextNode);
			double minH = Double.MAX_VALUE;
			Color minColor = null;
			for(Color c:codb.GetAllColor())
			{
				tmpTransformTable.put(nextNode, c);
				double h = H(WorkSet, ccg, tmpTransformTable);
				if(h < minH)
				{
					minH = h;
					minColor = c;
				}
			}
			tmpTransformTable.put(nextNode, minColor);

		}
		transformTable = tmpTransformTable;

		//Start Simulated Annealing
		int T = 19890416;
		Calendar clock1 = Calendar.getInstance();
		long time1 = clock1.getTimeInMillis();
		Random generator = new Random(time1);
		while(T > 0)
		{
			T -= generator.nextInt(200) + 1;
			Hashtable<CCGNode, Color> nexttable = findNext(transformTable,root);
			if(H(ccg, transformTable) > H(ccg,nexttable))
				transformTable = nexttable;
			else if(H(ccg,nexttable) > H(ccg, transformTable))
			{
				double index = (H(ccg, transformTable) - H(ccg, nexttable)) / ((double) T) * 10;
				//System.out.println(index);
				double p = Math.pow(Math.E,index);
				double divpoint = generator.nextDouble();
				if(divpoint<p)
				{
					transformTable = nexttable;
				}
			}
		}
		System.out.println(H(ccg, transformTable));
		System.out.println(accumulateDist(ccg));
		System.out.println(averDiff(ccg, transformTable));
		System.out.println(averageDist(ccg));


	}
}
