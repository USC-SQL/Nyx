package usc.edu.Hooker;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sql.Color.Color;

public class Hooker {
    public static String replace(String s)
    {
        //change background color

        Hashtable<Color, Color> bgtable=new Hashtable<Color, Color>();
        Hashtable<Color, Color> texttable=new Hashtable<Color, Color>();

        bgtable.put(new Color("#336699"), new Color("#ff6c7f"));
        bgtable.put(new Color("#ffeac5"), new Color("#00006f"));
        bgtable.put(new Color("#ffffff"), new Color("#000000"));
        texttable.put(new Color("#0000ff"), new Color("#60c0d0"));
        texttable.put(new Color("#ce7e00"), new Color("#a05060"));
        texttable.put(new Color("#ffffff"), new Color("#1040c0"));
        texttable.put(new Color("#000000"), new Color("#fffff0"));

        //Pattern pattern1 = Pattern.compile(".*background-color\\s*:\\s*([\\#A-Za-z0-9\\(\\),]+).*");
        Pattern pattern1 = Pattern.compile(".*background-color\\s*:\\s*([\\#A-Za-z0-9\\(\\),]+).*");
        Pattern pattern2 = Pattern.compile(".*bgcolor\\s*=[^\\#]*([\\#A-Za-z0-9\\(\\),]+)\\W*.*");
        String p=s.replaceAll("\\s", " ");
        String r=s;


        //System.out.println(r);
        Matcher m1=pattern1.matcher(p);
        Matcher m2=pattern2.matcher(p);
        if(m1.matches())
        {
            //System.out.println(s);
            // throw new Error(s);

            String colordec=m1.group(1);
            Color c=new Color(colordec);
            Color nc=bgtable.get(c);
            if(nc==null)
                throw new Error(c.toString() + p);
            r=r.replaceAll(colordec, nc.toString());

            //return r;

        }
        else if(m2.matches())
        {
            String colordec=m2.group(1);
            Color c=new Color(colordec);
            Color nc=bgtable.get(c);
            if(nc==null)
                throw new Error(c.toString());
            r=r.replaceAll(colordec, nc.toString());

            //return r;
        }
        //change text color
        Pattern pattern3 = Pattern.compile(".*[^bg-]color\\s*:\\s*([\\#A-Za-z0-9\\(\\),]+).*");
        //Pattern pattern4 = Pattern.compile(".*[^bg-]color\\s*=\\s*\\\"\\s*([\\#A-Za-z0-9\\(\\),]+)\\s*\\\".*");
        Pattern pattern4 = Pattern.compile(".*[^bg-]color\\s*=[^\\#]*([\\#A-Za-z0-9\\(\\),]+)\\W*.*");

        Matcher m3=pattern3.matcher(p);
        Matcher m4=pattern4.matcher(p);
        if(m3.matches())
        {
            //System.out.println(s);
            String colordec=m3.group(1);
            Color c=new Color(colordec);
            Color nc=texttable.get(c);
            if(nc==null)
                throw new Error(c.toString());
            r=r.replaceAll(colordec,nc.toString());
        }
        else if(m4.matches())
        {
            //System.out.println(s);
            String colordec=m4.group(1);
            Color c=new Color(colordec);
            Color nc=texttable.get(c);
            if(nc==null)
                throw new Error(c.toString());
            r=r.replaceAll(colordec,nc.toString());
            //	throw new Error(s);

        }

        //add default color
        //for body background
        Pattern pattern5 = Pattern.compile(".*<body.*");
        Matcher m5=pattern5.matcher(p);
        Pattern pattern7 = Pattern.compile(".*link\\s*=[^\\#]*([\\#A-Za-z0-9\\(\\),]+)\\W*.*");
        Matcher m7=pattern7.matcher(p);

        if(m5.matches()&&!m1.matches()&&!m2.matches())
        {
            Color defb=bgtable.get(new Color("WHITE"));
            r=r.replaceAll("<body", "<body bgcolor=\""+defb.toString()+"\" ");
        }
        if(m5.matches()&&!m3.matches()&&!m4.matches())
        {
            Color defc=texttable.get(new Color("BLACK"));
            r=r.replaceAll("<body", "<body text=\""+defc.toString()+"\" ");
        }
        if(m5.matches()&&!m7.matches())
        {
            Color defc=texttable.get(new Color("BLUE"));
            r=r.replaceAll("<body", "<body link=\""+defc.toString()+"\" ");
        }
        return r;
    }
}
