package ecosystem;

import java.util.List;

import physics.Body;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public class Predator extends Animal{
    private PApplet parent;
    private SubPlot plt;
    private List<Body> preys;
    private PImage image;

    public Predator(PVector pos, float preyMass, float preySize, int color, List<Body> preys, PApplet parent, SubPlot plt) {
        super(pos, preyMass, preySize, color, parent, plt);
        this.parent = parent;
        this.plt = plt;
        energy = WorldConstants.INI_PREDATOR_ENERGY;
        this.preys = preys;
        image = parent.loadImage("ecosystem//aguia.jpg");
        image.resize(40, 40);
        
    }

    protected Predator(Animal a, boolean mutate, List<Body> preys, PApplet p, SubPlot plt) {
        super(a, mutate, p, plt);
        this.parent = p;
        this.plt = plt;
        energy = WorldConstants.INI_PREDATOR_ENERGY;
        this.preys = preys;
        image = parent.loadImage("ecosystem//aguia.jpg");
        image.resize(40, 40);
        
    }

    public int checkForPrey(List<Body> preys){
        int index = 0;
        float dist = PVector.dist(pos, preys.get(0).getPos());
        for(int i=1;i<preys.size();i++){
            if(PVector.dist(pos, preys.get(i).getPos())<dist){
                index = i;
                dist = PVector.dist(pos, preys.get(i).getPos());
            }
        }
        return index;
    }

    

    @Override
    public Animal reproduce(boolean mutate) {
        Animal child = null;
        if(energy> WorldConstants.PREDATOR_ENERGY_TO_REPRODUCE){
            energy-= WorldConstants.INI_PREDATOR_ENERGY;
            child = new Predator(this, mutate, preys, parent, plt);
            if(mutate){
                child.mutateBehaviors();
                child.getDNA().mutate();
            }
        }
        return child;
    }

    @Override
    public void eat(Terrain terrain) {
        for(int i=0;i<preys.size();i++){
            if(PVector.dist(pos, preys.get(i).getPos())<0.5f){
                ((Prey) preys.get(i)).setEaten(true);
                energy+= WorldConstants.ENERGY_FROM_PREY;
            }
        }
    }
    @Override
    public void energy_consumption(float dt, Terrain terrain){
        energy-=dt*3.5f;
    }

    @Override
    public void display(PApplet p, SubPlot plt){
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(pos.x, pos.y);
        p.image(image, pp[0], pp[1]);
        p.translate(pp[0], pp[1]);
        p.popMatrix();
    }
    
}
