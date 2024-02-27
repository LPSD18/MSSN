package TP2;

import java.util.ArrayList;
import java.util.List;

import aa.Arrive;
import aa.Boid;
import aa.Eye;
import aa.Flee;
import aa.Flock;
import aa.Seek;
import aa.Wander;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP2E1App implements IProcessingApp{

    private Flock flock;
    private Boid b;
    private List<Body> allTrackingBodies;
    private List<Body> allTrackingBodies2;
    private float[] sacWeights = {1,5,2};
    private double[] window = {-10,10,-10,10};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width,p.height);
        flock = new Flock(200, 0.1f,0.3f,p.color(0,0,0), sacWeights, p, plt);
        b = new Boid(new PVector(), 0.3f, 0.3f, p.color(255,0,0), p, plt);
        b.addBehavior(new Arrive(1));
        allTrackingBodies = new ArrayList<Body>();
        allTrackingBodies.add(flock.getBoid(1));
        Eye eye = new Eye(b, allTrackingBodies);
        b.setEye(eye);
        flock.getBoid(1).setShape(p, plt, 0.3f, p.color(0,0,255));
        

    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(255);
        flock.applyBehavior(dt);
        flock.display(p, plt);
        b.applyBehaviors(dt);
        b.display(p, plt);
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
