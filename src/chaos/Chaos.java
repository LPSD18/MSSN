package chaos;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

public class Chaos implements IProcessingApp {
    
    private PVector currentPoint;
    private List<PVector> initialPoints;
    private boolean start=false;

    private void initShape(PApplet p){
        initialPoints = new ArrayList<PVector>();
        initialPoints.add(new PVector(p.width-50,p.height-50));
        initialPoints.add(new PVector(p.width/2,50));
        initialPoints.add(new PVector(50,p.height-50));
        for(PVector point : initialPoints){
            p.point(point.x, point.y);
        }

    }

    private void drawInitialRandomPoint(PApplet p){
        PVector random = new PVector(p.random(p.width),p.random(p.height));
        p.point(random.x, random.y);
        currentPoint = random;
    }

    @Override
    public void setup(PApplet p) {
        //initShape(p);
        initialPoints = new ArrayList<PVector>();
        drawInitialRandomPoint(p);
        p.background(0);
    }
    @Override
    public void draw(PApplet p, float dt) {
        p.stroke(255);
        if(start){
            for(int i=0;i!=1000;i++){
                int r = (int) Math.floor(p.random(initialPoints.size()));
                float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.5f);
                float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.5f);
                p.point(x,y);
                currentPoint = new PVector(x,y);
            }
        }
    }
    @Override
    public void mousePressed(PApplet p) {
        initialPoints.add(new PVector(p.mouseX,p.mouseY));
        p.point(p.mouseX, p.mouseY);
    }
    @Override
    public void mouseReleased(PApplet p) {
        
    }
    @Override
    public void mouseDragged(PApplet p) {
        
    }
    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='s'){
            start=true;
        }
    }

}
