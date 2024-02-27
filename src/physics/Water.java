package physics;

import processing.core.PApplet;
import tools.SubPlot;

public class Water extends Fluid{

    private float waterWeight;
    private int color;

    public Water(float waterWeight,int color) {
        super(1000.0f);
        this.waterWeight = waterWeight;
        this.color=color;
    }

    public boolean isInside(Mover m){
        return(m.getPos().y<=waterWeight);
    }
    public void display(PApplet p,SubPlot plt){
        p.pushStyle();
        p.noStroke();
        p.fill(color);
        float[]pp = plt.getBox(0,0,plt.getWindow()[1],waterWeight);
        p.rect(pp[0], pp[1], pp[2], pp[3]);
        p.popStyle();
    }
    
}