package Graph.Models;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Java Code to get a color name from rgb/hex value/awt color
 * 
 * The part of looking up a color name from the rgb values is edited from
 * https://gist.github.com/nightlark/6482130#file-gistfile1-java (that has some
 * errors) by Ryan Mast (nightlark)
 * 
 * @author Xiaoxiao Li
 * 
 */
public class ColorUtils {

	public ArrayList<ColorName> initColorList() {
		ArrayList<ColorName> colorList = new ArrayList<ColorName>();
		colorList.add(new ColorName("AliceBlue", 0xF0, 0xF8, 0xFF));
		colorList.add(new ColorName("AntiqueWhite", 0xFA, 0xEB, 0xD7));
		colorList.add(new ColorName("Aqua", 0x00, 0xFF, 0xFF));
		colorList.add(new ColorName("Aquamarine", 0x7F, 0xFF, 0xD4));
		colorList.add(new ColorName("Azure", 0xF0, 0xFF, 0xFF));
		colorList.add(new ColorName("Beige", 0xF5, 0xF5, 0xDC));
		colorList.add(new ColorName("Bisque", 0xFF, 0xE4, 0xC4));
		colorList.add(new ColorName("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
		colorList.add(new ColorName("Coral", 0xFF, 0x7F, 0x50));
		colorList.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
		colorList.add(new ColorName("CornflowerBlue", 0x64, 0x95, 0xED));
		colorList.add(new ColorName("Cornsilk", 0xFF, 0xF8, 0xDC));
		colorList.add(new ColorName("FloralWhite", 0xFF, 0xFA, 0xF0));
		colorList.add(new ColorName("GreenYellow", 0xAD, 0xFF, 0x2F));
		colorList.add(new ColorName("HoneyDew", 0xF0, 0xFF, 0xF0));
		colorList.add(new ColorName("Lavender", 0xE6, 0xE6, 0xFA));
		colorList.add(new ColorName("LavenderBlush", 0xFF, 0xF0, 0xF5));
		colorList.add(new ColorName("LightBlue", 0xAD, 0xD8, 0xE6));
		colorList.add(new ColorName("LemonChiffon", 0xFF, 0xFA, 0xCD));
		colorList.add(new ColorName("LightCoral", 0xF0, 0x80, 0x80));
		colorList.add(new ColorName("LightCyan", 0xE0, 0xFF, 0xFF));
		colorList.add(new ColorName("LightGoldenRodYellow", 0xFA, 0xFA, 0xD2));
		colorList.add(new ColorName("LightGray", 0xD3, 0xD3, 0xD3));
		colorList.add(new ColorName("LightGreen", 0x90, 0xEE, 0x90));
		colorList.add(new ColorName("LightPink", 0xFF, 0xB6, 0xC1));
		colorList.add(new ColorName("LightSalmon", 0xFF, 0xA0, 0x7A));
		colorList.add(new ColorName("LightSeaGreen", 0x20, 0xB2, 0xAA));
		colorList.add(new ColorName("LightSkyBlue", 0x87, 0xCE, 0xFA));
		colorList.add(new ColorName("LightSlateGray", 0x77, 0x88, 0x99));
		colorList.add(new ColorName("LightSteelBlue", 0xB0, 0xC4, 0xDE));
		colorList.add(new ColorName("LightYellow", 0xFF, 0xFF, 0xE0));
		colorList.add(new ColorName("MediumOrchid", 0xBA, 0x55, 0xD3));
		colorList.add(new ColorName("MintCream", 0xF5, 0xFF, 0xFA));
		colorList.add(new ColorName("NavajoWhite", 0xFF, 0xDE, 0xAD));
		colorList.add(new ColorName("Navy", 0x00, 0x00, 0x80));
		colorList.add(new ColorName("OldLace", 0xFD, 0xF5, 0xE6));
		colorList.add(new ColorName("PapayaWhip", 0xFF, 0xEF, 0xD5));
		colorList.add(new ColorName("PeachPuff", 0xFF, 0xDA, 0xB9));
		colorList.add(new ColorName("PowderBlue", 0xB0, 0xE0, 0xE6));
		colorList.add(new ColorName("Thistle", 0xD8, 0xBF, 0xD8));
		return colorList;
	}

	public String getColorNameFromRgb(int r, int g, int b) {
		ArrayList<ColorName> colorList = initColorList();
		ColorName closestMatch = null;
		int minMSE = Integer.MAX_VALUE;
		int mse;
		for (ColorName c : colorList) {
			mse = c.computeMSE(r, g, b);
			if (mse < minMSE) {
				minMSE = mse;
				closestMatch = c;
			}
		}
		if (closestMatch != null) {
			return closestMatch.getName();
		} else {
			return "No matched color name.";
		}
	}

	public String getColorNameFromHex(int hexColor) {
		int r = (hexColor & 0xFF0000) >> 16;
		int g = (hexColor & 0xFF00) >> 8;
		int b = (hexColor & 0xFF);
		return getColorNameFromRgb(r, g, b);
	}

	public int colorToHex(Color c) {
		return Integer.decode("0x" + Integer.toHexString(c.getRGB()).substring(2));
	}

	public String getColorNameFromColor(Color color) {
		return getColorNameFromRgb(color.getRed(), color.getGreen(), color.getBlue());
	}

	public class ColorName {
		public int r, g, b;
		public String name;

		public ColorName(String name, int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.name = name;
		}

		public int computeMSE(int pixR, int pixG, int pixB) {
			return (int) (((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b) * (pixB - b)) / 3);
		}

		public int getR() {
			return r;
		}

		public int getG() {
			return g;
		}

		public int getB() {
			return b;
		}

		public String getName() {
			return name;
		}
	}
}
