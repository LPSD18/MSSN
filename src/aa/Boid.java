package aa;

import java.util.ArrayList;
import java.util.List;

import physics.Body;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;
import tools.SubPlot;

public class Boid extends Body{

    private SubPlot plt;
    private PShape shape;
    protected DNA dna;
    protected Eye eye;
    protected List<Behavior> behaviors;
    protected float phiWander;
    private double[] window;
    private float sumWeights;


    public Boid(PVector pos, float mass, float radius, int color,PApplet p, SubPlot plt) {
        super(pos, new PVector(), mass, radius, color);
        behaviors = new ArrayList<Behavior>();
        dna = new DNA();
        this.plt=plt;
        setShape(p, plt);
        window = plt.getWindow();
    }

    public void setEye(Eye eye){
        this.eye=eye;
    }

    public Eye getEye(){
        return eye; 
    }

    private void updateSumWeights(){
        sumWeights = 0;
        for(Behavior beh : behaviors){
            //System.out.println("Weight"+beh.getWeight());
            sumWeights += beh.getWeight();
        }
    }

    public int getNumBehaviors(){
        return behaviors.size();
    }

    public void addBehavior(Behavior behavior){
        behaviors.add(behavior);
        updateSumWeights();
    }
    public void removeBehavior(Behavior behavior){
        if(behaviors.contains(behavior)) behaviors.remove(behavior);
        updateSumWeights();
    }

    public void applyBehavior(int i,float dt){
        if(eye!=null) eye.look();
        Behavior behavior = behaviors.get(i);
        PVector vd = behavior.getDesiredVelocity(this);
        move(dt,vd);
    }

    public void applyBehaviors(float dt){
        if(eye!=null) eye.look();
        PVector vd = new PVector();
        for(Behavior behavior : behaviors){
            PVector vdd = behavior.getDesiredVelocity(this);
            vdd.mult(behavior.getWeight()/sumWeights);
            vd.add(vdd);
        }
        move(dt,vd);
    }

    private void move(float dt, PVector vd){
        vd.normalize().mult(dna.maxSpeed);
        PVector fs = PVector.sub(vd, vel);
        applyForce(fs.limit(dna.maxForce));
        super.move(dt);
        if(pos.x < window[0]) pos.x +=window[1]-window[0];
        if(pos.y < window[2]) pos.y +=window[3]-window[2];
        if(pos.x >= window[1]) pos.x -=window[1]-window[0];
        if(pos.y >= window[3]) pos.y -=window[3]-window[2];

    }

    public void setShape(PApplet p, SubPlot plt,float radius, int color){
        this.radius=radius;
        this.color=color;
        setShape(p, plt);
    }

    public void setShape(PApplet p, SubPlot plt){
        float[] rr = plt.getVectorCoord(radius,radius);
        shape = p.createShape();
        shape.beginShape();
        shape.noStroke();
        shape.fill(color);
        shape.vertex(-rr[0],rr[0]/2);
        shape.vertex(rr[0], 0);
        shape.vertex(-rr[0], -rr[0]/2);
        shape.vertex(-rr[0]/2,0);
        shape.endShape(PConstants.CLOSE);
    }

    @Override
    public void display(PApplet p,SubPlot plt){
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(pos.x, pos.y);
        float[] vv = plt.getVectorCoord(vel.x,vel.y);
        PVector vaux = new PVector(vv[0],vv[1]);
        p.translate(pp[0], pp[1]);
        p.rotate(-vaux.heading());
        p.shape(shape,0,0);
        p.popMatrix();
    }

    public void displayVel(PApplet p, SubPlot plt){
        p.pushStyle();
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(pos.x, pos.y);
        p.translate(pp[0], pp[1]);
        p.rotate(-vel.heading());
        p.noFill();
        p.stroke(0,255,0);
        p.strokeWeight(3);
        float[] dd = plt.getVectorCoord(dna.visionDistance, dna.visionDistance);
        p.line(0, 0, dd[0], 0);
        p.popMatrix();
        p.popStyle();
    }

    public void mutateBehaviors(){
        for(Behavior behavior : behaviors){
            if(behavior instanceof AvoidObstacle){
                behavior.weight += DNA.random(-0.5f,0.5f);
                behavior.weight = Math.max(0, behavior.weight);
            }
            if(behavior instanceof Wander){
                behavior.weight += DNA.random(-0.1f,0.1f);
                behavior.weight = Math.max(0, behavior.weight);
            }
            if(behavior instanceof Flee){
                behavior.weight += DNA.random(-0.5f,0.5f);
                behavior.weight = Math.max(0, behavior.weight);
            }
            if(behavior instanceof Seek){
                behavior.weight += DNA.random(-0.5f,0.5f);
                behavior.weight = Math.max(0, behavior.weight);
            }
        }
        updateSumWeights();
    }

    public DNA getDNA(){
        return dna;
    }

    public List<Behavior> getBehaviors(){
        return behaviors;
    }

    
}
