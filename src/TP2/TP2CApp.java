package TP2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import physics.Body;
import physics.PSControl;
import physics.ParticleSystem;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP2CApp implements IProcessingApp{

    private float sunMass = 1.989e30f;
    private float mercuryMass = 3.30e23f;
    private float venusMass = 4.87e24f;
    private float earthMass = 5.97e24f;
    private float marsMass = 6.42e23f;
    private float jupiterMass = 1.90e27f;
    private float saturnMass = 5.68e23f;
    private float uranusMass = 8.68e25f;
    private float neptuneMass = 1.02e26f;

    private float distMercurySun = 5.79e10f;
    private float distVenusSun = 1.082e11f;
    private float distEarthSun = 1.496e11f;
    private float distMarsSun = 2.279e11f;
    private float distJupiterSun = 7.786e11f;
    private float distSaturnSun = 1.4335e12f;
    private float distUranusSun = 2.8725e12f;
    private float distNeptuneSun = 4.4951e12f;

    private float mercurySpeed = 47870f;
    private float venusSpeed = 35020f;
    private float earthSpeed = 3e4f;
    private float marsSpeed = 24077f;
    private float jupiterSpeed = 13070f;
    private float saturnSpeed = 9690f;
    private float uranusSpeed = 6810f;
    private float neptuneSped = 5430f;

    private PSControl psc;
    private float[] VelParams = {PApplet.radians(180),PApplet.radians(20),1,3};
    private float[] lifetimeParams = {3,5};
    private float[] radiusParams = {distEarthSun/20f,distEarthSun/20f};

    private float[] viewport = {0f,0f,1f,1f};
    private double[] window = {-1.2f*distJupiterSun,1.2f*distJupiterSun,-1.2f*distJupiterSun,1.2f*distJupiterSun};

    private SubPlot plt;
    private Body sun;
    private Body mercury;
    private Body venus;
    private Body earth;
    private Body mars;
    private Body jupiter;
    private Body saturn;
    private Body uranus;
    private Body neptune;
    private List<Body> planets;


    private float speedUp = 60*60*24*30;

    private List<ParticleSystem> pss;
    protected List<ParticleSystem> pssRemove;
    

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport,p.width, p.height);
        sun = new Body(new PVector(), new PVector(), sunMass, distEarthSun/10, p.color(255,255,0));
        mercury = new Body(new PVector(0,distMercurySun), new PVector(mercurySpeed,0), mercuryMass, distMercurySun/20, p.color(128));
        venus = new Body(new PVector(0,distVenusSun), new PVector(venusSpeed,0), venusMass,distVenusSun/20,p.color(255,128,0));
        earth = new Body(new PVector(0,distEarthSun), new PVector(earthSpeed,0), earthMass, distEarthSun/20, p.color(0,180,120));
        mars = new Body(new PVector(0,distMarsSun),new PVector(marsSpeed,0), marsMass, distMarsSun/20,p.color(255,0,0));
        jupiter = new Body(new PVector(0,distJupiterSun), new PVector(jupiterSpeed,0), jupiterMass, distJupiterSun/20, p.color(128,128,0));
        planets = new ArrayList<Body>();
        planets.add(sun);
        planets.add(mercury);
        planets.add(venus);
        planets.add(earth);
        planets.add(mars);
        planets.add(jupiter);
        pss = new ArrayList<ParticleSystem>();
        pssRemove = new ArrayList<ParticleSystem>();
    }

    @Override
    public void draw(PApplet p, float dt) {
        float[] pp = plt.getBoundingBox();
        p.fill(0,16);// arrasto da Terra
        p.rect(pp[0], pp[1], pp[2], pp[3]);
        sun.display(p, plt);
        PVector mercuryV = sun.attraction(mercury);
        PVector venusV = sun.attraction(venus);
        PVector earthV = sun.attraction(earth);
        PVector marsV  = sun.attraction(mars);
        PVector jupiterV = sun.attraction(jupiter);
        mercury.applyForce(mercuryV);
        venus.applyForce(venusV);
        earth.applyForce(earthV);
        mars.applyForce(marsV);
        jupiter.applyForce(jupiterV);
        mercury.move(dt*speedUp);
        venus.move(dt*speedUp);
        earth.move(dt*speedUp);
        mars.move(dt*speedUp);
        jupiter.move(dt*speedUp);
        mercury.display(p, plt);
        venus.display(p, plt);
        earth.display(p, plt);
        mars.display(p, plt);
        jupiter.display(p, plt);
        for(ParticleSystem ps : pss){
            for(Body b : planets){
                if(b.getPos().dist(ps.getPos()) <= b.getRadius())
                    ps.getPsControl().setColor(p.color(200,0,0));
                if(b.getPos().dist(ps.getPos()) <= 2*b.getRadius()/3) 
                    ps.getPsControl().setColor(p.color(250,165,0));
                if(b.getPos().dist(ps.getPos()) <= b.getRadius()/3){
                    if(b.equals(sun)){
                        b.setMass(b.getMass()/1e5f);
                    }
                    pssRemove.add(ps);
                    if(Math.random()>0.5f){
                        b.setMass(b.getMass()/1e10f);
                        System.out.println("Retirou massa");
                    }
                    else{ 
                        b.setVel(b.getVel().div(2f));
                        System.out.println("Retirou velocidade");
                    }
                }
                
            }
            ps.applyForce(new PVector(distEarthSun/2,0));
            ps.move(dt);
            ps.display(p, plt);
        }
        for(int i=0;i<pssRemove.size();i++){
            pss.remove(pssRemove.get(i));
        }
    }

    @Override
    public void mousePressed(PApplet p) {
        double[] ww = plt.getWorldCoord(p.mouseX, p.mouseY);
        int color = p.color(255);
        float lifespan = p.random(1, 3);
        psc = new PSControl(VelParams, lifetimeParams, radiusParams, lifespan, color);
        ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]),new PVector(),1f,distMercurySun/3, psc);
        pss.add(ps);
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
