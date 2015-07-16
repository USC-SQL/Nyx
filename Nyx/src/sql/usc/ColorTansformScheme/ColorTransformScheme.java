package sql.usc.ColorTansformScheme;

import sql.usc.Color.Color;
import sql.usc.ColorConflictGraph.ColorConflictGraph;
import sql.usc.ColorConflictGraph.TexColorMap;

import java.io.*;

public class ColorTransformScheme {
	BackgroundColorScheme bgscheme;
	TextColorScheme textscheme;
	public void Display()
	{
		bgscheme.Display();
		textscheme.Display();
	}
	public ColorTransformScheme(ColorConflictGraph ccg, TexColorMap tcm, Color bg)
	{
		bgscheme = new BackgroundColorScheme(ccg, bg);
		textscheme = new TextColorScheme(tcm, bgscheme);
	}
	public String getJavaCode(){
		String r = "";
		r = bgscheme.getJavaCode() + textscheme.getJavaCode();
		return r;
	}
	public String GeneratePerl(){
		String r = "";

		return r;


	}
	public void GenerateJavaFromTemplate(String inpath, String out){
		File f = new File(inpath);
		System.out.println(f.getAbsolutePath());
		String read="";
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String line;
			while ((line = br.readLine()) != null) {
				read += line+"\n";
			}
			br.close();
			String replace = bgscheme.getJavaCode()+ textscheme.getJavaCode();
			String r = read.replaceAll("replace_point",replace);
			PrintWriter pw = new PrintWriter(out);
			pw.println(r);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
