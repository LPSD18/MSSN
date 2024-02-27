package TP2;

import java.util.ArrayList;
import java.util.List;

import aa.Arrive;
import aa.Boid;
import aa.Eye;
import aa.Flee;
import aa.Seek;
import aa.Wander;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP2DApp implements IProcessingApp{

    private Boid b;
    private double[] window = {-10,10,-10,10};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private Body target;
    private List<Body> allTrackingBodies;
    private float dv;
    private int behav;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        b = new Boid(new PVector(), 1, 0.5f, p.color(255,128,0), p, plt);
        b.addBehavior(new Seek(1));
        b.addBehavior(new Arrive(1));
        b.addBehavior(new Wander(1));
        b.addBehavior(new Flee(1));
        target = new Body(new PVector(), new PVector(), 1, 0.2f, p.color(255));
        allTrackingBodies = new ArrayList<Body>();
        allTrackingBodies.add(target);
        Eye eye = new Eye(b, allTrackingBodies);
        b.setEye(eye);
        System.out.println("Para indicar o comportamento desejado clique nas seguintes teclas");
        System.out.println("0 - Seek");
        System.out.println("1 - Arrive");
        System.out.println("2 - Wander");
        System.out.println("3 - Flee");
        System.out.println("Para aumentar ou diminuir a velocidade clique nas seguintes teclas");
        System.out.println("f - Aumentar velocidade");
        System.out.println("s - Diminuir velocidade");
        dv=0;
        behav=0;
    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(0);
        b.applyBehavior(behav, dt);
        b.display(p, plt);
        target.display(p, plt);
        dv = dt;
    }

    @Override
    public void mousePressed(PApplet p) {
        double[] ww = plt.getWorldCoord(p.mouseX,p.mouseY);
        target.setPos(new PVector((float)ww[0],(float)ww[1]));
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='f'){
            b.getVel().mult(2f);
        }
        if(p.key=='s'){
            b.getVel().div(2f);
        }
        if(p.key=='0'){
            behav=0;
        }
        if(p.key=='1'){
            behav=1;
        }
        if(p.key=='2'){
            behav=2;
        }
        if(p.key=='3'){
            behav=3;
        }
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
