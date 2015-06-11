package sql.usc.ColorTansformScheme;

import sql.usc.Color.Color;
import sql.usc.ColorConflictGraph.ColorConfictGraph;
import sql.usc.ColorConflictGraph.TexColorMap;

import java.io.*;

public class ColorTansformScheme {
	BackgroundColorScheme bgsheme;
	TextColorScheme textsheme;
	public void Display()
	{
		bgsheme.Display();
		textsheme.Display();
	}
	public ColorTansformScheme(ColorConfictGraph ccg, TexColorMap tcm,Color bg)
	{
		bgsheme=new BackgroundColorScheme(ccg,bg);
		textsheme=new TextColorScheme(tcm,bgsheme);
	}
	public String getJavaCode(){
		String r="";
		r=bgsheme.getJavaCode()+textsheme.getJavaCode();
		return r;

	}
	public String GeneratePerl(){
		String r="";

		return r;


	}
	public void GenerateJavaFromTemplate(String inpath, String out){
		File f=new File(inpath);
		System.out.println(f.getAbsolutePath());
		String read="";
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = br.readLine()) != null) {
				read+=line+"\n";
			}
			br.close();
			String replace=bgsheme.getJavaCode()+textsheme.getJavaCode();
			String r=read.replaceAll("replace_point",replace);
			PrintWriter pw=new PrintWriter(out);
			pw.println(r);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
