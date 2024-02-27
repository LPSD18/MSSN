package TP2;

import java.util.ArrayList;
import java.util.List;

import aa.Flock;
import physics.Body;
import physics.PSControl;
import physics.ParticleSystem;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP2E2App implements IProcessingApp{

    private Flock flock;
    private float[] sacWeights = {1,1,1};
    private double[] window = {-10,10,-10,10};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private List<Body> flockRemove;
    private float[] VelParams = {PApplet.radians(180),PApplet.radians(180),1,3};
    private float[] lifetimeParams = {3,5};
    private float[] radiusParams = {0.1f,0.2f};
    private float flow = 10;
    private List<ParticleSystem>pss;
    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        flock = new Flock(200, 0.1f, 0.3f, p.color(255), sacWeights, p, plt);
        flockRemove = new ArrayList<Body>();
        pss = new ArrayList<ParticleSystem>();

    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(0);
        flock.applyBehavior(dt);
        flock.display(p, plt);
        for(Body b : flock.getBoids()){
            for(Body bb : flock.getBoids()){
                if(!b.equals(bb)&&b.getPos().dist(bb.getPos())<=b.getRadius()/5){
                    flockRemove.add(b);
                    flockRemove.add(bb);
                    int color = p.color(p.random(255),p.random(255),p.random(255));
                PSControl psc = new PSControl(VelParams,lifetimeParams,radiusParams,flow,color);
                ParticleSystem ps = new ParticleSystem(b.getPos(), new PVector(), 1f,0.2f,psc);
                pss.add(ps);
                }
            }
        }
        for(Body b : flockRemove){
            flock.getBoids().remove(b);
        }
        for(ParticleSystem ps:pss){
            ps.applyForce(new PVector(0,0));
        }
        for(ParticleSystem ps:pss){
            ps.move(dt);
            ps.display(p, plt);
        }
    }

    @Override
    public void mousePressed(PApplet p) {
        // double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
        // for(Body b : flock.getBoids()){
        //     if(b.getPos().x<=(float)ww[0]&&b.getPos().y<=(float)ww[1]){
        //         System.out.println("ggbg");
        //         flockRemove.add(b);
        //         int color = p.color(p.random(255),p.random(255),p.random(255));
        //         PSControl psc = new PSControl(VelParams,lifetimeParams,radiusParams,flow,color);
        //         ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]), new PVector(), 1f,0.2f,psc);
        //         pss.add(ps);
        //     }
        // }
        // for(Body b : flockRemove){
        //     flock.getBoids().remove(b);
        // }
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
