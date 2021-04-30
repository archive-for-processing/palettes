import palettes.*;

Palettes p;

void setup() {
  size(1000, 1000);
  noLoop();
}

void draw() {
  clear();

  Palettes p = new Palettes(this);

  p.getPalette();
  background(p.background);

  p.getPalette();
  fill(p.colors[0]);
  stroke(p.stroke);
  rect(100, 100, 100, 100);

  p.getPalette("flag");
  fill(p.colors[2]);
  noStroke();
  circle(width / 2, height / 2, 500);
}

void mouseClicked() {
  redraw();
}