// This generates the image in /resources/overview.ong

import palettes.*;

Palettes p;
PFont mono;

String sketchName = "example_02";
int padding = 50;
int paletteWidth = 500;
int paletteHeight = 150;

void settings() {
  int height = 30 * paletteHeight + (30 + 1) * padding;
  int width = paletteWidth + 2 * padding;
  size(width, height);
  pixelDensity(displayDensity());
}

void setup() {
  noLoop();
}

void draw() {
  Palettes p = new Palettes(this);

  background(#FFF1F1);
  strokeWeight(4);
  mono = createFont("Monaco", 20);
  textFont(mono);

  translate(padding, padding);

  for (String paletteName : p.paletteNames) {
    p.getPalette(paletteName);
    drawPalette(paletteName, p.colors, p.stroke, p.background);
    translate(0, paletteHeight + padding);
  }
}

void drawPalette(String paletteName, int[] colors_, int stroke_, int background_) {
  stroke(stroke_);

  push();
  fill(background_);
  rect(0, 0, paletteWidth, paletteHeight);

  int colorRectWidth = floor(paletteWidth / (2 * colors_.length + 1));

  push();
  noStroke();
  fill(#1c1c1c);
  text(paletteName, 2, -10);
  pop();

  translate(0, 25);

  for (int c : colors_) {
    translate(colorRectWidth, 0);
    fill(c);
    rect(0, 0, colorRectWidth, 100);
    translate(colorRectWidth, 0);
  }

  pop();
}
