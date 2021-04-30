package palettes;

import processing.core.*;
import processing.data.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Palettes {
	PApplet parent;

	public final static String version = "1.0.0";

	JSONArray json;
	JSONObject palette;
	public int[] colors;
	public int background;
	public int stroke;
	public Set<String> paletteNames;
	public int numPalettes;

	/**
	 * Constructor
	 *
	 * @param parent_ the parent PApplet
	 */
	public Palettes(PApplet parent_) {
		parent = parent_;
		json = parent.loadJSONArray("data/palettes.json");
		numPalettes = json.size();

		loadPaletteNames();
	}

	/**
	 * Return the size of the current palette.
	 *
	 * @return int
	 */
	public int getPaletteSize() {
		return this.colors.length;
	}

	/**
	 * Return a random palette.
	 */
	public void getPalette() {
		int randomIndex = PApplet.parseInt(parent.random(json.size()));
		palette = json.getJSONObject(randomIndex);
		this.loadPalette(palette);
	}

	/**
	 * Get a palette by name.
	 *
	 * @param name
	 */
	public void getPalette(String name) {
		assert paletteNames.contains(name);

		for (int i = 0; i < json.size(); i++) {
			palette = json.getJSONObject(i);

			if (palette.getString("name").equals(name)) {
				this.loadPalette(palette);
				break;
			}
		}
	}

	/**
	 * Unpack a single color palette.
	 *
	 * @param palette
	 */
	private void loadPalette(JSONObject palette) {
		int[] values = new int[0];
		JSONArray colorArray = palette.getJSONArray("colors");

		for (int i = 0; i < colorArray.size(); i++) {
			int color = PApplet.unhex("FF" + colorArray.getString(i).substring(1));
			values = PApplet.append(values, color);
		}

		background = PApplet.unhex("FF" + palette.getString("background").substring(1));
		stroke = PApplet.unhex("FF" + palette.getString("stroke").substring(1));
		colors = values;
	}

	/**
	 * Return an array containg the palette names.
	 */
	private void loadPaletteNames() {
		paletteNames = new HashSet<String>();

		for (int i = 0; i < json.size(); i++) {
			String colorName = json.getJSONObject(i).getString("name");
			paletteNames.add(colorName);
		}
	}
}
