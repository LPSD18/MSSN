package setup;

import processing.core.PApplet;

public class Hello implements IProcessingApp {

	private int c;
	
	public void setup(PApplet p) {
		
		c = p.color(255,0,0);
		
		
	}
	
	public void draw(PApplet p, float dt) {

		p.fill(c);
		p.circle(p.mouseX,p.mouseY,50);

	}

	public void mousePressed(PApplet p) {
		
		c = p.color(0, 255, 0);

	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
	}

	@Override
	public void mouseDragged(PApplet p) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
	}
	
	
	
}
