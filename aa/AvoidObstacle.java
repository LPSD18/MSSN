package aa;

import ecosystem.Patch;
import ecosystem.Predator;
import physics.Body;
import processing.core.PVector;

public class AvoidObstacle extends Behavior{

    public AvoidObstacle(float weight) {
        super(weight);
    }

    @Override
    public PVector getDesiredVelocity(Boid me) {
        float s = hasObstacle(me);
        if(s==0) return me.getVel().copy();
        PVector vd = new PVector(me.getVel().y,-me.getVel().x);
        if(s>0) vd.mult(-1);
        return vd;
    }

    private float hasObstacle(Boid me){
        for(Body body : me.eye.getFarSight()){
            //if é teste
            if(body instanceof Predator){}
            else{
                PVector r = PVector.sub(body.getPos(), me.getPos());
                PVector vd = new PVector(me.getVel().y,-me.getVel().x);
                return PVector.dot(vd, r);
            }
        }
        return 0;
    }
    
    
}
