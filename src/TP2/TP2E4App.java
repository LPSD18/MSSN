package TP2;

import java.util.List;

import aa.Boid;
import aa.Flock;
import physics.Body;
import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP2E4App implements IProcessingApp{

    private Flock flock;
    private float[] sacWeights = {1,1,1};
    private double[] window = {-10,10,-10,10};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private int i;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        flock = new Flock(200, 0.1f, 0.3f, p.color(0), sacWeights, p, plt);
        flock.getBoid(0).setShape(p, plt, 0.3f, p.color(0,0,255));
        
    }

    @Override
    public void draw(PApplet p, float dt) {
        if(i%1==0){
            p.background(255);
            flock.applyBehavior(dt);
            flock.display(p, plt);
            flock.getBoid(0).getEye().display(p, plt);
            flock.getBoid(0).displayVel(p, plt);
            flock.getBoid(0).getEye().look();
            List<Body> farSight = flock.getBoid(0).getEye().getFarSight();
            List<Body> nearSight = flock.getBoid(0).getEye().getNearSight();
            for(Boid b : flock.getBoids()){
                if(farSight.contains(b)&&(!nearSight.contains(b))){
                    b.setShape(p, plt, 0.3f, p.color(255,0,0));
                }
                else if(nearSight.contains(b)){
                    b.setShape(p, plt, 0.3f, p.color(0,255,0));
                }
                else{
                    b.setShape(p, plt, 0.3f, p.color(0));
                }
            }
        }
        i++;
    }

    @Override
    public void mousePressed(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        
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
