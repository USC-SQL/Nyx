package test;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ImageProcessor {
	private static String GetImage(String path) throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://www.cssdrive.com/imagepalette/index.php");
		File file = new File(path);
		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody cbFile = new FileBody(file);
		mpEntity.addPart("uploadFile", cbFile);
		httppost.setEntity(mpEntity);
		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = client.execute(httppost);
		HttpEntity resEntity = response.getEntity();



		System.out.println(response.getStatusLine());
		if (resEntity != null) {
			System.out.println(EntityUtils.toString(resEntity));
		}
		HttpGet httpget = new HttpGet("http://www.cssdrive.com/imagepalette/export.php?e=css");
		HttpResponse response2 = client.execute(httpget);
		HttpEntity resEntity2 = response2.getEntity();

		System.out.println(response2.getStatusLine());
		String r="";
		if (resEntity2 != null) {
			r=EntityUtils.toString(resEntity2);
		}
		r=r.replaceAll("<br/>","\n");
		System.out.println(r);
		return r;


	}

	public static  void main(String argv[]) throws Exception
	{
		GetImage("/home/dingli/NyxExecutable/icon_shop.gif");
		/*Color bg=new Color("#ffffff");
		Set<Color> fore=new HashSet<Color>();
		FileInputStream fstream = new FileInputStream("/home/dingli/csscolor");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String line;
		while ((line = br.readLine()) != null) {
			Pattern pattern = Pattern.compile(".*(#[A-Za-z0-9]+).*");
			Matcher m=pattern.matcher(line);
			if(m.matches())
				fore.add(new Color(m.group(1)));
		}
		br.close();

		PrintWriter pw=new PrintWriter("/home/dingli/graph1.gv");


		ColorConflictGraph CCG=new ColorConflictGraph(new Color("#ffffff"),new HashSet<Color>());
		BackgroundColorScheme bgsheme=new BackgroundColorScheme(CCG,new Color("#ffffff"));
		TexColorMap TCM=new TexColorMap(bg,fore);
		pw.println(TCM.toDot());
		pw.println(CCG.toDot());
		pw.close();
		TextColorScheme	textsheme=new TextColorScheme(TCM,bgsheme);

		//BackgroundColorScheme bgsheme=new BackgroundColorScheme(CCG);

		textsheme.DisplayTransform("trans.gif");*/
	}
}
