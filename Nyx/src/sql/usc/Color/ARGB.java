package sql.usc.Color;

/**
 * Created by mianwan on 7/14/15.
 */
public class ARGB {
    int a;
    Color rgb;

    public ARGB (String color) {
        long argb = Long.parseLong(color.substring(1), 16);
        a = (int) (argb >> 24) & 0xff;
        int iRgb = (int) argb & 0xffffff;
        rgb = new Color(iRgb);
    }

    public String toHexString()
    {
        String a = Integer.toHexString(this.a);
        String rgb = this.rgb.toHexString();
        if(a.length()==1)
            a = "0" + a;
        return "#" + a + rgb.replace("#", "");
    }

    public Color getRgb() {
        return rgb;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        int result = 17;
        result = 31 * result + a;
        result = 31 * result + rgb.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (!(obj instanceof ARGB)) {
            return false;
        }
        ARGB argb = (ARGB) obj;
        return (this.a == argb.a && this.rgb == argb.rgb);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "argb(" + this.a + "," + this.rgb.getR() + "," + this.rgb.getG() + "," + this.rgb.getB() + ")";
    }
}
