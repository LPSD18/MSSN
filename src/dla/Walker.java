package dla;


import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Walker {
    
    public enum State{
        STOPPED,
        WANDER
    };

    private PVector pos;
    private State state;
    private int colour;
    private static int radius = 4;
    public static int num_wanders = 0;
    public static int num_stopped = 0;
    private float stick;
    
    public Walker(PApplet p){
        pos = new PVector(p.width/2,p.height/2 );
        PVector r = PVector.random2D();
        r.mult(p.width/2);
        pos.add(r); 
        setState(p, State.WANDER);
    }

    public Walker(PApplet p, PVector pos){
        this.pos = pos;
        setState(p, State.STOPPED);
        colour = p.color(0);
    }

    

    public State getState(){
        return state;
    }

    public void setState(PApplet p,State state){
            this.state = state;
            if(state==State.STOPPED){
                colour = p.color(0);
                num_stopped++;
                float distToCenter = PVector.dist(pos, new PVector(p.width/2, p.height/2));
                if(distToCenter<75)colour = p.color(p.random(0,255),0,0);//Condições para as cores das partículas STOPPED
                if(distToCenter<150&&distToCenter>=75)colour = p.color(0,p.random(0,255),0);
                if(distToCenter<225&&distToCenter>=150)colour = p.color(0,0,p.random(0,255));
                if(distToCenter>=225)colour = p.color(p.random(0,255),p.random(0,255),p.random(0,255));
            }
            else{
                colour = p.color(p.random(0,255),p.random(0,255),p.random(0,255));//Condições para as cores das partículas WANDER
                num_wanders++;
            }
    }
    
    public void updateState(PApplet p, List<Walker> walkers){

        if(state == State.STOPPED)return;
        for (Walker w : walkers){
            if(w.state==State.STOPPED){
                float dist = PVector.dist(pos, w.pos);
                if(dist<=2*radius && p.random(0, 1)<=this.stick){//Verifica se o walker está no raio de um wallker parado e o stick
                    setState(p,State.STOPPED);
                    num_wanders--;
                    break;
                }
                
            }
        }
    }
    public void setStick(float stick){
        this.stick=stick;
    }

    public void wander(PApplet p){
        PVector step = PVector.random2D();
        pos.add(step);
        pos.lerp(new PVector(p.width/2, p.height/2),0.0002f);
        pos.x = PApplet.constrain(pos.x,0,p.width);
        pos.y = PApplet.constrain(pos.y,0,p.height);
    }

    public void display(PApplet p){
        p.fill(colour);
        p.circle(pos.x, pos.y, 2*radius);
    }

    

    public void setColorReset(PApplet p){
        colour=p.color(128);
        p.circle(0, 0, 0);
    }
    public static void reset(int wanders,int stopped){
        num_stopped=stopped;
        num_wanders=wanders;
    }
}
