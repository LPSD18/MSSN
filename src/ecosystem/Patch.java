package ecosystem;

import java.util.ArrayList;
import java.util.List;

import ca.MajorityCell;
import ecosystem.WorldConstants.PatchType;
import physics.PSControl;
import physics.ParticleSystem;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import tools.SubPlot;

public class Patch extends MajorityCell {

    private long eatenTime;
    private int timeToGrow;

    

    public Patch(Terrain terrain, int row, int col, int timeToGrow){
        super(terrain, row, col);
        this.timeToGrow = timeToGrow;
        eatenTime = System.currentTimeMillis();     
        
    }

    public void setFertile(){
        //codigo teste limpar if depois
        if(Math.random()<0.98f)
        state = PatchType.FERTILE.ordinal();
        else state = PatchType.EMPTY.ordinal();//comentar isto 
        eatenTime = System.currentTimeMillis();
    }

    public void regenerate(){
        if(state == PatchType.FERTILE.ordinal()&& System.currentTimeMillis()>(eatenTime+timeToGrow))
            state = PatchType.FOOD.ordinal();
        if(state == PatchType.EMPTY.ordinal()&&System.currentTimeMillis()>((eatenTime+timeToGrow))&&Math.random()>0.98f)
            state = PatchType.FOOD.ordinal();
    }

    // @Override
    public void display(PApplet p, List<PImage> images){
        p.pushStyle();
        if(state == PatchType.FOOD.ordinal()){
            p.image(images.get(3), ca.xmin+col*ca.cellWidth, row * ca.cellHeight);
        }
        else if(state == PatchType.EMPTY.ordinal()){
            p.image(images.get(0), ca.xmin+col*ca.cellWidth, row * ca.cellHeight);
        }
        else if(state == PatchType.FERTILE.ordinal()){
            p.image(images.get(2), ca.xmin+col*ca.cellWidth, row * ca.cellHeight);
        }
        else if(state == PatchType.OBSTACLE.ordinal()){
            p.image(images.get(1), ca.xmin+col*ca.cellWidth, row * ca.cellHeight);
            
        }
        
        p.popStyle();
    }
    
}
