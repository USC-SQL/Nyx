package sql.Color;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class ColorDatabase {
	Hashtable<String, Color> predefinedcolors=new Hashtable<String, Color>();
	Set<Color> allcolors=new HashSet<Color>();
	Hashtable<String, Color> disttable=new Hashtable<String, Color>();
	private double CalDist(Color c1, Color c2)
	{
		return 0.0;
	}
	public Set<Color> GetAllColor()
	{
		return allcolors;
	}
	public static boolean isPredefined(String color)
	{
		Hashtable<String, Color> predefinedcolors=new Hashtable<String, Color>();
		predefinedcolors.put("ALICEBLUE", new Color(240,248,255));
		predefinedcolors.put("ANTIQUEWHITE", new Color(250,235,215));
		predefinedcolors.put("AQUA", new Color(0,255,255));
		predefinedcolors.put("AQUAMARINE", new Color(127,255,212));
		predefinedcolors.put("AZURE", new Color(240,255,255));
		predefinedcolors.put("BEIGE", new Color(245,245,220));
		predefinedcolors.put("BISQUE", new Color(255,228,196));
		predefinedcolors.put("BLACK", new Color(0,0,0));
		predefinedcolors.put("BLANCHEDALMOND", new Color(255,235,205));
		predefinedcolors.put("BLUE", new Color(0,0,255));
		predefinedcolors.put("BLUEVIOLET", new Color(138,43,226));
		predefinedcolors.put("BROWN", new Color(165,42,42));
		predefinedcolors.put("BURLYWOOD", new Color(222,184,135));
		predefinedcolors.put("CADETBLUE", new Color(95,158,160));
		predefinedcolors.put("CHARTREUSE", new Color(127,255,0));
		predefinedcolors.put("CHOCOLATE", new Color(210,105,30));
		predefinedcolors.put("CORAL", new Color(255,127,80));
		predefinedcolors.put("CORNFLOWERBLUE", new Color(100,149,237));
		predefinedcolors.put("CORNSILK", new Color(255,248,220));
		predefinedcolors.put("CRIMSON", new Color(220,20,60));
		predefinedcolors.put("CYAN", new Color(0,255,255));
		predefinedcolors.put("DARKBLUE", new Color(0,0,139));
		predefinedcolors.put("DARKCYAN", new Color(0,139,139));
		predefinedcolors.put("DARKGOLDENROD", new Color(184,134,11));
		predefinedcolors.put("DARKGRAY", new Color(169,169,169));
		predefinedcolors.put("DARKGREEN", new Color(0,100,0));
		predefinedcolors.put("DARKKHAKI", new Color(189,183,107));
		predefinedcolors.put("DARKMAGENTA", new Color(139,0,139));
		predefinedcolors.put("DARKOLIVEGREEN", new Color(85,107,47));
		predefinedcolors.put("DARKORANGE", new Color(255,140,0));
		predefinedcolors.put("DARKORCHID", new Color(153,50,204));
		predefinedcolors.put("DARKRED", new Color(139,0,0));
		predefinedcolors.put("DARKSALMON", new Color(233,150,122));
		predefinedcolors.put("DARKSEAGREEN", new Color(143,188,143));
		predefinedcolors.put("DARKSLATEBLUE", new Color(72,61,139));
		predefinedcolors.put("DARKSLATEGRAY", new Color(47,79,79));
		predefinedcolors.put("DARKTURQUOISE", new Color(0,206,209));
		predefinedcolors.put("DARKVIOLET", new Color(148,0,211));
		predefinedcolors.put("DEEPPINK", new Color(255,20,147));
		predefinedcolors.put("DEEPSKYBLUE", new Color(0,191,255));
		predefinedcolors.put("DIMGRAY", new Color(105,105,105));
		predefinedcolors.put("DODGERBLUE", new Color(30,144,255));
		predefinedcolors.put("FIREBRICK", new Color(178,34,34));
		predefinedcolors.put("FLORALWHITE", new Color(255,250,240));
		predefinedcolors.put("FORESTGREEN", new Color(34,139,34));
		predefinedcolors.put("FUCHSIA", new Color(255,0,255));
		predefinedcolors.put("GAINSBORO", new Color(220,220,220));
		predefinedcolors.put("GHOSTWHITE", new Color(248,248,255));
		predefinedcolors.put("GOLD", new Color(255,215,0));
		predefinedcolors.put("GOLDENROD", new Color(218,165,32));
		predefinedcolors.put("GRAY", new Color(128,128,128));
		predefinedcolors.put("GREEN", new Color(0,128,0));
		predefinedcolors.put("GREENYELLOW", new Color(173,255,47));
		predefinedcolors.put("HONEYDEW", new Color(240,255,240));
		predefinedcolors.put("HOTPINK", new Color(255,105,180));
		predefinedcolors.put("INDIANRED", new Color(205,92,92));
		predefinedcolors.put("INDIGO", new Color(75,0,130));
		predefinedcolors.put("IVORY", new Color(255,255,240));
		predefinedcolors.put("KHAKI", new Color(240,230,140));
		predefinedcolors.put("LAVENDER", new Color(230,230,250));
		predefinedcolors.put("LAVENDERBLUSH", new Color(255,240,245));
		predefinedcolors.put("LAWNGREEN", new Color(124,252,0));
		predefinedcolors.put("LEMONCHIFFON", new Color(255,250,205));
		predefinedcolors.put("LIGHTBLUE", new Color(173,216,230));
		predefinedcolors.put("LIGHTCORAL", new Color(240,128,128));
		predefinedcolors.put("LIGHTCYAN", new Color(224,255,255));
		predefinedcolors.put("LIGHTGOLDENRODYELLOW", new Color(250,250,210));
		predefinedcolors.put("LIGHTGRAY", new Color(211,211,211));
		predefinedcolors.put("LIGHTGREEN", new Color(144,238,144));
		predefinedcolors.put("LIGHTPINK", new Color(255,182,193));
		predefinedcolors.put("LIGHTSALMON", new Color(255,160,122));
		predefinedcolors.put("LIGHTSEAGREEN", new Color(32,178,170));
		predefinedcolors.put("LIGHTSKYBLUE", new Color(135,206,250));
		predefinedcolors.put("LIGHTSLATEGRAY", new Color(119,136,153));
		predefinedcolors.put("LIGHTSTEELBLUE", new Color(176,196,222));
		predefinedcolors.put("LIGHTYELLOW", new Color(255,255,224));
		predefinedcolors.put("LIME", new Color(0,255,0));
		predefinedcolors.put("LIMEGREEN", new Color(50,205,50));
		predefinedcolors.put("LINEN", new Color(250,240,230));
		predefinedcolors.put("MAGENTA", new Color(255,0,255));
		predefinedcolors.put("MAROON", new Color(128,0,0));
		predefinedcolors.put("MEDIUMAQUAMARINE", new Color(102,205,170));
		predefinedcolors.put("MEDIUMBLUE", new Color(0,0,205));
		predefinedcolors.put("MEDIUMORCHID", new Color(186,85,211));
		predefinedcolors.put("MEDIUMPURPLE", new Color(147,112,219));
		predefinedcolors.put("MEDIUMSEAGREEN", new Color(60,179,113));
		predefinedcolors.put("MEDIUMSLATEBLUE", new Color(123,104,238));
		predefinedcolors.put("MEDIUMSPRINGGREEN", new Color(0,250,154));
		predefinedcolors.put("MEDIUMTURQUOISE", new Color(72,209,204));
		predefinedcolors.put("MEDIUMVIOLETRED", new Color(199,21,133));
		predefinedcolors.put("MIDNIGHTBLUE", new Color(25,25,112));
		predefinedcolors.put("MINTCREAM", new Color(245,255,250));
		predefinedcolors.put("MISTYROSE", new Color(255,228,225));
		predefinedcolors.put("MOCCASIN", new Color(255,228,181));
		predefinedcolors.put("NAVAJOWHITE", new Color(255,222,173));
		predefinedcolors.put("NAVY", new Color(0,0,128));
		predefinedcolors.put("OLDLACE", new Color(253,245,230));
		predefinedcolors.put("OLIVE", new Color(128,128,0));
		predefinedcolors.put("OLIVEDRAB", new Color(107,142,35));
		predefinedcolors.put("ORANGE", new Color(255,165,0));
		predefinedcolors.put("ORANGERED", new Color(255,69,0));
		predefinedcolors.put("ORCHID", new Color(218,112,214));
		predefinedcolors.put("PALEGOLDENROD", new Color(238,232,170));
		predefinedcolors.put("PALEGREEN", new Color(152,251,152));
		predefinedcolors.put("PALETURQUOISE", new Color(175,238,238));
		predefinedcolors.put("PALEVIOLETRED", new Color(219,112,147));
		predefinedcolors.put("PAPAYAWHIP", new Color(255,239,213));
		predefinedcolors.put("PEACHPUFF", new Color(255,218,185));
		predefinedcolors.put("PERU", new Color(205,133,63));
		predefinedcolors.put("PINK", new Color(255,192,203));
		predefinedcolors.put("PLUM", new Color(221,160,221));
		predefinedcolors.put("POWDERBLUE", new Color(176,224,230));
		predefinedcolors.put("PURPLE", new Color(128,0,128));
		predefinedcolors.put("RED", new Color(255,0,0));
		predefinedcolors.put("ROSYBROWN", new Color(188,143,143));
		predefinedcolors.put("ROYALBLUE", new Color(65,105,225));
		predefinedcolors.put("SADDLEBROWN", new Color(139,69,19));
		predefinedcolors.put("SALMON", new Color(250,128,114));
		predefinedcolors.put("SANDYBROWN", new Color(244,164,96));
		predefinedcolors.put("SEAGREEN", new Color(46,139,87));
		predefinedcolors.put("SEASHELL", new Color(255,245,238));
		predefinedcolors.put("SIENNA", new Color(160,82,45));
		predefinedcolors.put("SILVER", new Color(192,192,192));
		predefinedcolors.put("SKYBLUE", new Color(135,206,235));
		predefinedcolors.put("SLATEBLUE", new Color(106,90,205));
		predefinedcolors.put("SLATEGRAY", new Color(112,128,144));
		predefinedcolors.put("SNOW", new Color(255,250,250));
		predefinedcolors.put("SPRINGGREEN", new Color(0,255,127));
		predefinedcolors.put("STEELBLUE", new Color(70,130,180));
		predefinedcolors.put("TAN", new Color(210,180,140));
		predefinedcolors.put("TEAL", new Color(0,128,128));
		predefinedcolors.put("THISTLE", new Color(216,191,216));
		predefinedcolors.put("TOMATO", new Color(255,99,71));
		predefinedcolors.put("TURQUOISE", new Color(64,224,208));
		predefinedcolors.put("VIOLET", new Color(238,130,238));
		predefinedcolors.put("WHEAT", new Color(245,222,179));
		predefinedcolors.put("WHITE", new Color(255,255,255));
		predefinedcolors.put("WHITESMOKE", new Color(245,245,245));
		predefinedcolors.put("YELLOW", new Color(255,255,0));
		predefinedcolors.put("YELLOWGREEN", new Color(154,205,50));
		return predefinedcolors.containsKey(color);
	}
	public static Color getPredefinedColor(String color)
	{
		Hashtable<String, Color> predefinedcolors=new Hashtable<String, Color>();
		predefinedcolors.put("ALICEBLUE", new Color(240,248,255));
		predefinedcolors.put("ANTIQUEWHITE", new Color(250,235,215));
		predefinedcolors.put("AQUA", new Color(0,255,255));
		predefinedcolors.put("AQUAMARINE", new Color(127,255,212));
		predefinedcolors.put("AZURE", new Color(240,255,255));
		predefinedcolors.put("BEIGE", new Color(245,245,220));
		predefinedcolors.put("BISQUE", new Color(255,228,196));
		predefinedcolors.put("BLACK", new Color(0,0,0));
		predefinedcolors.put("BLANCHEDALMOND", new Color(255,235,205));
		predefinedcolors.put("BLUE", new Color(0,0,255));
		predefinedcolors.put("BLUEVIOLET", new Color(138,43,226));
		predefinedcolors.put("BROWN", new Color(165,42,42));
		predefinedcolors.put("BURLYWOOD", new Color(222,184,135));
		predefinedcolors.put("CADETBLUE", new Color(95,158,160));
		predefinedcolors.put("CHARTREUSE", new Color(127,255,0));
		predefinedcolors.put("CHOCOLATE", new Color(210,105,30));
		predefinedcolors.put("CORAL", new Color(255,127,80));
		predefinedcolors.put("CORNFLOWERBLUE", new Color(100,149,237));
		predefinedcolors.put("CORNSILK", new Color(255,248,220));
		predefinedcolors.put("CRIMSON", new Color(220,20,60));
		predefinedcolors.put("CYAN", new Color(0,255,255));
		predefinedcolors.put("DARKBLUE", new Color(0,0,139));
		predefinedcolors.put("DARKCYAN", new Color(0,139,139));
		predefinedcolors.put("DARKGOLDENROD", new Color(184,134,11));
		predefinedcolors.put("DARKGRAY", new Color(169,169,169));
		predefinedcolors.put("DARKGREEN", new Color(0,100,0));
		predefinedcolors.put("DARKKHAKI", new Color(189,183,107));
		predefinedcolors.put("DARKMAGENTA", new Color(139,0,139));
		predefinedcolors.put("DARKOLIVEGREEN", new Color(85,107,47));
		predefinedcolors.put("DARKORANGE", new Color(255,140,0));
		predefinedcolors.put("DARKORCHID", new Color(153,50,204));
		predefinedcolors.put("DARKRED", new Color(139,0,0));
		predefinedcolors.put("DARKSALMON", new Color(233,150,122));
		predefinedcolors.put("DARKSEAGREEN", new Color(143,188,143));
		predefinedcolors.put("DARKSLATEBLUE", new Color(72,61,139));
		predefinedcolors.put("DARKSLATEGRAY", new Color(47,79,79));
		predefinedcolors.put("DARKTURQUOISE", new Color(0,206,209));
		predefinedcolors.put("DARKVIOLET", new Color(148,0,211));
		predefinedcolors.put("DEEPPINK", new Color(255,20,147));
		predefinedcolors.put("DEEPSKYBLUE", new Color(0,191,255));
		predefinedcolors.put("DIMGRAY", new Color(105,105,105));
		predefinedcolors.put("DODGERBLUE", new Color(30,144,255));
		predefinedcolors.put("FIREBRICK", new Color(178,34,34));
		predefinedcolors.put("FLORALWHITE", new Color(255,250,240));
		predefinedcolors.put("FORESTGREEN", new Color(34,139,34));
		predefinedcolors.put("FUCHSIA", new Color(255,0,255));
		predefinedcolors.put("GAINSBORO", new Color(220,220,220));
		predefinedcolors.put("GHOSTWHITE", new Color(248,248,255));
		predefinedcolors.put("GOLD", new Color(255,215,0));
		predefinedcolors.put("GOLDENROD", new Color(218,165,32));
		predefinedcolors.put("GRAY", new Color(128,128,128));
		predefinedcolors.put("GREEN", new Color(0,128,0));
		predefinedcolors.put("GREENYELLOW", new Color(173,255,47));
		predefinedcolors.put("HONEYDEW", new Color(240,255,240));
		predefinedcolors.put("HOTPINK", new Color(255,105,180));
		predefinedcolors.put("INDIANRED", new Color(205,92,92));
		predefinedcolors.put("INDIGO", new Color(75,0,130));
		predefinedcolors.put("IVORY", new Color(255,255,240));
		predefinedcolors.put("KHAKI", new Color(240,230,140));
		predefinedcolors.put("LAVENDER", new Color(230,230,250));
		predefinedcolors.put("LAVENDERBLUSH", new Color(255,240,245));
		predefinedcolors.put("LAWNGREEN", new Color(124,252,0));
		predefinedcolors.put("LEMONCHIFFON", new Color(255,250,205));
		predefinedcolors.put("LIGHTBLUE", new Color(173,216,230));
		predefinedcolors.put("LIGHTCORAL", new Color(240,128,128));
		predefinedcolors.put("LIGHTCYAN", new Color(224,255,255));
		predefinedcolors.put("LIGHTGOLDENRODYELLOW", new Color(250,250,210));
		predefinedcolors.put("LIGHTGRAY", new Color(211,211,211));
		predefinedcolors.put("LIGHTGREEN", new Color(144,238,144));
		predefinedcolors.put("LIGHTPINK", new Color(255,182,193));
		predefinedcolors.put("LIGHTSALMON", new Color(255,160,122));
		predefinedcolors.put("LIGHTSEAGREEN", new Color(32,178,170));
		predefinedcolors.put("LIGHTSKYBLUE", new Color(135,206,250));
		predefinedcolors.put("LIGHTSLATEGRAY", new Color(119,136,153));
		predefinedcolors.put("LIGHTSTEELBLUE", new Color(176,196,222));
		predefinedcolors.put("LIGHTYELLOW", new Color(255,255,224));
		predefinedcolors.put("LIME", new Color(0,255,0));
		predefinedcolors.put("LIMEGREEN", new Color(50,205,50));
		predefinedcolors.put("LINEN", new Color(250,240,230));
		predefinedcolors.put("MAGENTA", new Color(255,0,255));
		predefinedcolors.put("MAROON", new Color(128,0,0));
		predefinedcolors.put("MEDIUMAQUAMARINE", new Color(102,205,170));
		predefinedcolors.put("MEDIUMBLUE", new Color(0,0,205));
		predefinedcolors.put("MEDIUMORCHID", new Color(186,85,211));
		predefinedcolors.put("MEDIUMPURPLE", new Color(147,112,219));
		predefinedcolors.put("MEDIUMSEAGREEN", new Color(60,179,113));
		predefinedcolors.put("MEDIUMSLATEBLUE", new Color(123,104,238));
		predefinedcolors.put("MEDIUMSPRINGGREEN", new Color(0,250,154));
		predefinedcolors.put("MEDIUMTURQUOISE", new Color(72,209,204));
		predefinedcolors.put("MEDIUMVIOLETRED", new Color(199,21,133));
		predefinedcolors.put("MIDNIGHTBLUE", new Color(25,25,112));
		predefinedcolors.put("MINTCREAM", new Color(245,255,250));
		predefinedcolors.put("MISTYROSE", new Color(255,228,225));
		predefinedcolors.put("MOCCASIN", new Color(255,228,181));
		predefinedcolors.put("NAVAJOWHITE", new Color(255,222,173));
		predefinedcolors.put("NAVY", new Color(0,0,128));
		predefinedcolors.put("OLDLACE", new Color(253,245,230));
		predefinedcolors.put("OLIVE", new Color(128,128,0));
		predefinedcolors.put("OLIVEDRAB", new Color(107,142,35));
		predefinedcolors.put("ORANGE", new Color(255,165,0));
		predefinedcolors.put("ORANGERED", new Color(255,69,0));
		predefinedcolors.put("ORCHID", new Color(218,112,214));
		predefinedcolors.put("PALEGOLDENROD", new Color(238,232,170));
		predefinedcolors.put("PALEGREEN", new Color(152,251,152));
		predefinedcolors.put("PALETURQUOISE", new Color(175,238,238));
		predefinedcolors.put("PALEVIOLETRED", new Color(219,112,147));
		predefinedcolors.put("PAPAYAWHIP", new Color(255,239,213));
		predefinedcolors.put("PEACHPUFF", new Color(255,218,185));
		predefinedcolors.put("PERU", new Color(205,133,63));
		predefinedcolors.put("PINK", new Color(255,192,203));
		predefinedcolors.put("PLUM", new Color(221,160,221));
		predefinedcolors.put("POWDERBLUE", new Color(176,224,230));
		predefinedcolors.put("PURPLE", new Color(128,0,128));
		predefinedcolors.put("RED", new Color(255,0,0));
		predefinedcolors.put("ROSYBROWN", new Color(188,143,143));
		predefinedcolors.put("ROYALBLUE", new Color(65,105,225));
		predefinedcolors.put("SADDLEBROWN", new Color(139,69,19));
		predefinedcolors.put("SALMON", new Color(250,128,114));
		predefinedcolors.put("SANDYBROWN", new Color(244,164,96));
		predefinedcolors.put("SEAGREEN", new Color(46,139,87));
		predefinedcolors.put("SEASHELL", new Color(255,245,238));
		predefinedcolors.put("SIENNA", new Color(160,82,45));
		predefinedcolors.put("SILVER", new Color(192,192,192));
		predefinedcolors.put("SKYBLUE", new Color(135,206,235));
		predefinedcolors.put("SLATEBLUE", new Color(106,90,205));
		predefinedcolors.put("SLATEGRAY", new Color(112,128,144));
		predefinedcolors.put("SNOW", new Color(255,250,250));
		predefinedcolors.put("SPRINGGREEN", new Color(0,255,127));
		predefinedcolors.put("STEELBLUE", new Color(70,130,180));
		predefinedcolors.put("TAN", new Color(210,180,140));
		predefinedcolors.put("TEAL", new Color(0,128,128));
		predefinedcolors.put("THISTLE", new Color(216,191,216));
		predefinedcolors.put("TOMATO", new Color(255,99,71));
		predefinedcolors.put("TURQUOISE", new Color(64,224,208));
		predefinedcolors.put("VIOLET", new Color(238,130,238));
		predefinedcolors.put("WHEAT", new Color(245,222,179));
		predefinedcolors.put("WHITE", new Color(255,255,255));
		predefinedcolors.put("WHITESMOKE", new Color(245,245,245));
		predefinedcolors.put("YELLOW", new Color(255,255,0));
		predefinedcolors.put("YELLOWGREEN", new Color(154,205,50));
		return predefinedcolors.get(color);
	}
	public ColorDatabase()
	{
		predefinedcolors.put("ALICEBLUE", new Color(240,248,255));
		predefinedcolors.put("ANTIQUEWHITE", new Color(250,235,215));
		predefinedcolors.put("AQUA", new Color(0,255,255));
		predefinedcolors.put("AQUAMARINE", new Color(127,255,212));
		predefinedcolors.put("AZURE", new Color(240,255,255));
		predefinedcolors.put("BEIGE", new Color(245,245,220));
		predefinedcolors.put("BISQUE", new Color(255,228,196));
		predefinedcolors.put("BLACK", new Color(0,0,0));
		predefinedcolors.put("BLANCHEDALMOND", new Color(255,235,205));
		predefinedcolors.put("BLUE", new Color(0,0,255));
		predefinedcolors.put("BLUEVIOLET", new Color(138,43,226));
		predefinedcolors.put("BROWN", new Color(165,42,42));
		predefinedcolors.put("BURLYWOOD", new Color(222,184,135));
		predefinedcolors.put("CADETBLUE", new Color(95,158,160));
		predefinedcolors.put("CHARTREUSE", new Color(127,255,0));
		predefinedcolors.put("CHOCOLATE", new Color(210,105,30));
		predefinedcolors.put("CORAL", new Color(255,127,80));
		predefinedcolors.put("CORNFLOWERBLUE", new Color(100,149,237));
		predefinedcolors.put("CORNSILK", new Color(255,248,220));
		predefinedcolors.put("CRIMSON", new Color(220,20,60));
		predefinedcolors.put("CYAN", new Color(0,255,255));
		predefinedcolors.put("DARKBLUE", new Color(0,0,139));
		predefinedcolors.put("DARKCYAN", new Color(0,139,139));
		predefinedcolors.put("DARKGOLDENROD", new Color(184,134,11));
		predefinedcolors.put("DARKGRAY", new Color(169,169,169));
		predefinedcolors.put("DARKGREEN", new Color(0,100,0));
		predefinedcolors.put("DARKKHAKI", new Color(189,183,107));
		predefinedcolors.put("DARKMAGENTA", new Color(139,0,139));
		predefinedcolors.put("DARKOLIVEGREEN", new Color(85,107,47));
		predefinedcolors.put("DARKORANGE", new Color(255,140,0));
		predefinedcolors.put("DARKORCHID", new Color(153,50,204));
		predefinedcolors.put("DARKRED", new Color(139,0,0));
		predefinedcolors.put("DARKSALMON", new Color(233,150,122));
		predefinedcolors.put("DARKSEAGREEN", new Color(143,188,143));
		predefinedcolors.put("DARKSLATEBLUE", new Color(72,61,139));
		predefinedcolors.put("DARKSLATEGRAY", new Color(47,79,79));
		predefinedcolors.put("DARKTURQUOISE", new Color(0,206,209));
		predefinedcolors.put("DARKVIOLET", new Color(148,0,211));
		predefinedcolors.put("DEEPPINK", new Color(255,20,147));
		predefinedcolors.put("DEEPSKYBLUE", new Color(0,191,255));
		predefinedcolors.put("DIMGRAY", new Color(105,105,105));
		predefinedcolors.put("DODGERBLUE", new Color(30,144,255));
		predefinedcolors.put("FIREBRICK", new Color(178,34,34));
		predefinedcolors.put("FLORALWHITE", new Color(255,250,240));
		predefinedcolors.put("FORESTGREEN", new Color(34,139,34));
		predefinedcolors.put("FUCHSIA", new Color(255,0,255));
		predefinedcolors.put("GAINSBORO", new Color(220,220,220));
		predefinedcolors.put("GHOSTWHITE", new Color(248,248,255));
		predefinedcolors.put("GOLD", new Color(255,215,0));
		predefinedcolors.put("GOLDENROD", new Color(218,165,32));
		predefinedcolors.put("GRAY", new Color(128,128,128));
		predefinedcolors.put("GREEN", new Color(0,128,0));
		predefinedcolors.put("GREENYELLOW", new Color(173,255,47));
		predefinedcolors.put("HONEYDEW", new Color(240,255,240));
		predefinedcolors.put("HOTPINK", new Color(255,105,180));
		predefinedcolors.put("INDIANRED", new Color(205,92,92));
		predefinedcolors.put("INDIGO", new Color(75,0,130));
		predefinedcolors.put("IVORY", new Color(255,255,240));
		predefinedcolors.put("KHAKI", new Color(240,230,140));
		predefinedcolors.put("LAVENDER", new Color(230,230,250));
		predefinedcolors.put("LAVENDERBLUSH", new Color(255,240,245));
		predefinedcolors.put("LAWNGREEN", new Color(124,252,0));
		predefinedcolors.put("LEMONCHIFFON", new Color(255,250,205));
		predefinedcolors.put("LIGHTBLUE", new Color(173,216,230));
		predefinedcolors.put("LIGHTCORAL", new Color(240,128,128));
		predefinedcolors.put("LIGHTCYAN", new Color(224,255,255));
		predefinedcolors.put("LIGHTGOLDENRODYELLOW", new Color(250,250,210));
		predefinedcolors.put("LIGHTGRAY", new Color(211,211,211));
		predefinedcolors.put("LIGHTGREEN", new Color(144,238,144));
		predefinedcolors.put("LIGHTPINK", new Color(255,182,193));
		predefinedcolors.put("LIGHTSALMON", new Color(255,160,122));
		predefinedcolors.put("LIGHTSEAGREEN", new Color(32,178,170));
		predefinedcolors.put("LIGHTSKYBLUE", new Color(135,206,250));
		predefinedcolors.put("LIGHTSLATEGRAY", new Color(119,136,153));
		predefinedcolors.put("LIGHTSTEELBLUE", new Color(176,196,222));
		predefinedcolors.put("LIGHTYELLOW", new Color(255,255,224));
		predefinedcolors.put("LIME", new Color(0,255,0));
		predefinedcolors.put("LIMEGREEN", new Color(50,205,50));
		predefinedcolors.put("LINEN", new Color(250,240,230));
		predefinedcolors.put("MAGENTA", new Color(255,0,255));
		predefinedcolors.put("MAROON", new Color(128,0,0));
		predefinedcolors.put("MEDIUMAQUAMARINE", new Color(102,205,170));
		predefinedcolors.put("MEDIUMBLUE", new Color(0,0,205));
		predefinedcolors.put("MEDIUMORCHID", new Color(186,85,211));
		predefinedcolors.put("MEDIUMPURPLE", new Color(147,112,219));
		predefinedcolors.put("MEDIUMSEAGREEN", new Color(60,179,113));
		predefinedcolors.put("MEDIUMSLATEBLUE", new Color(123,104,238));
		predefinedcolors.put("MEDIUMSPRINGGREEN", new Color(0,250,154));
		predefinedcolors.put("MEDIUMTURQUOISE", new Color(72,209,204));
		predefinedcolors.put("MEDIUMVIOLETRED", new Color(199,21,133));
		predefinedcolors.put("MIDNIGHTBLUE", new Color(25,25,112));
		predefinedcolors.put("MINTCREAM", new Color(245,255,250));
		predefinedcolors.put("MISTYROSE", new Color(255,228,225));
		predefinedcolors.put("MOCCASIN", new Color(255,228,181));
		predefinedcolors.put("NAVAJOWHITE", new Color(255,222,173));
		predefinedcolors.put("NAVY", new Color(0,0,128));
		predefinedcolors.put("OLDLACE", new Color(253,245,230));
		predefinedcolors.put("OLIVE", new Color(128,128,0));
		predefinedcolors.put("OLIVEDRAB", new Color(107,142,35));
		predefinedcolors.put("ORANGE", new Color(255,165,0));
		predefinedcolors.put("ORANGERED", new Color(255,69,0));
		predefinedcolors.put("ORCHID", new Color(218,112,214));
		predefinedcolors.put("PALEGOLDENROD", new Color(238,232,170));
		predefinedcolors.put("PALEGREEN", new Color(152,251,152));
		predefinedcolors.put("PALETURQUOISE", new Color(175,238,238));
		predefinedcolors.put("PALEVIOLETRED", new Color(219,112,147));
		predefinedcolors.put("PAPAYAWHIP", new Color(255,239,213));
		predefinedcolors.put("PEACHPUFF", new Color(255,218,185));
		predefinedcolors.put("PERU", new Color(205,133,63));
		predefinedcolors.put("PINK", new Color(255,192,203));
		predefinedcolors.put("PLUM", new Color(221,160,221));
		predefinedcolors.put("POWDERBLUE", new Color(176,224,230));
		predefinedcolors.put("PURPLE", new Color(128,0,128));
		predefinedcolors.put("RED", new Color(255,0,0));
		predefinedcolors.put("ROSYBROWN", new Color(188,143,143));
		predefinedcolors.put("ROYALBLUE", new Color(65,105,225));
		predefinedcolors.put("SADDLEBROWN", new Color(139,69,19));
		predefinedcolors.put("SALMON", new Color(250,128,114));
		predefinedcolors.put("SANDYBROWN", new Color(244,164,96));
		predefinedcolors.put("SEAGREEN", new Color(46,139,87));
		predefinedcolors.put("SEASHELL", new Color(255,245,238));
		predefinedcolors.put("SIENNA", new Color(160,82,45));
		predefinedcolors.put("SILVER", new Color(192,192,192));
		predefinedcolors.put("SKYBLUE", new Color(135,206,235));
		predefinedcolors.put("SLATEBLUE", new Color(106,90,205));
		predefinedcolors.put("SLATEGRAY", new Color(112,128,144));
		predefinedcolors.put("SNOW", new Color(255,250,250));
		predefinedcolors.put("SPRINGGREEN", new Color(0,255,127));
		predefinedcolors.put("STEELBLUE", new Color(70,130,180));
		predefinedcolors.put("TAN", new Color(210,180,140));
		predefinedcolors.put("TEAL", new Color(0,128,128));
		predefinedcolors.put("THISTLE", new Color(216,191,216));
		predefinedcolors.put("TOMATO", new Color(255,99,71));
		predefinedcolors.put("TURQUOISE", new Color(64,224,208));
		predefinedcolors.put("VIOLET", new Color(238,130,238));
		predefinedcolors.put("WHEAT", new Color(245,222,179));
		predefinedcolors.put("WHITE", new Color(255,255,255));
		predefinedcolors.put("WHITESMOKE", new Color(245,245,245));
		predefinedcolors.put("YELLOW", new Color(255,255,0));
		predefinedcolors.put("YELLOWGREEN", new Color(154,205,50));
		
		for(int r=0;r<256;r+=16)
			for(int g=0;g<256;g+=16)
				for(int b=0;b<256;b+=16)
				{
					allcolors.add(new Color(r,g,b));
				}
		for(String s: predefinedcolors.keySet())
			allcolors.add(predefinedcolors.get(s));
	}
}