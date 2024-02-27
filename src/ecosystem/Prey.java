package ecosystem;

import java.util.List;

import physics.Body;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public class Prey extends Animal{

    private PApplet parent;
    private SubPlot plt;
    private PImage image;

    public Prey(PVector pos, float preyMass, float preySize, int color, PApplet parent, SubPlot plt) {
        super(pos, preyMass, preySize, color, parent, plt);
        this.parent = parent;
        this.plt = plt;
        energy = WorldConstants.INI_PREY_ENERGY;
        image = parent.loadImage("ecosystem//rato.jpg");
        image.resize(20, 20);
    }

    public Prey(Prey prey, boolean mutate, PApplet parent, SubPlot plt){
        super(prey, mutate, parent, plt);
        this.parent = parent;
        this.plt = plt;
        energy = WorldConstants.INI_PREY_ENERGY;
        image = parent.loadImage("ecosystem//rato.jpg");
        image.resize(20, 20);
    }

    @Override
    public Animal reproduce(boolean mutate) {
        Animal child = null;
        if(energy>WorldConstants.PREY_ENERGY_TO_REPRODUCE){
            energy -= WorldConstants.INI_PREY_ENERGY;
            child = new Prey(this, mutate, parent, plt);
            if(mutate) {
                child.mutateBehaviors();
                child.getDNA().mutate();
            }
        }
        return child;
    }

    @Override
    public void eat(Terrain terrain) {
        Patch patch = (Patch) terrain.world2Cell(pos.x, pos.y);
        if(patch.getState()==WorldConstants.PatchType.FOOD.ordinal()){
            energy+= WorldConstants.ENERGY_FROM_PLANT;
            patch.setFertile();
        }
    }

    @Override
    public void energy_consumption(float dt, Terrain terrain) {
        super.energy_consumption(dt, terrain);
    }

    public void setEaten(boolean eaten){
        energy = 0;
    }

    @Override
    public void display(PApplet p, SubPlot plt){
        p.pushMatrix();
        float[] pp = plt.getPixelCoord(pos.x, pos.y);
        p.image(image, pp[0], pp[1]);
        p.translate(pp[0], pp[1]);
        p.popMatrix();
    }
    //teste função
    public int checkForPred(List<Predator> predators){
        int index = 0;
        float dist = PVector.dist(pos, predators.get(0).getPos());
        for(int i=1;i<predators.size();i++){
            if(predators.get(i) instanceof Predator && PVector.dist(pos, predators.get(i).getPos())<dist){
                index = i;
                dist = PVector.dist(pos, predators.get(i).getPos());
                // System.out.println(index);
            }
        }
        return index;
    }

}
