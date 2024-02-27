package ecosystem;

import physics.Body;
import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class TestObstaclesApp implements IProcessingApp{

    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private Terrain terrain;
    private Population population;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(WorldConstants.WINDOW, viewport, p.width,p.height);
        terrain = new Terrain(p, plt);
        terrain.setStateColors(getColors(p));
        terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
        for(int i=0;i<2;i++)terrain.majorityRule();
        population = new Population(p, plt, terrain);
    }

    @Override
    public void draw(PApplet p, float dt) {
        terrain.regenerate();
        population.update(dt, terrain);
        terrain.display(p);
        population.display(p, plt);
        for(Animal b : population.allAnimals){
            b.getEye().display(p, plt);
        }
        
    }

    @Override
    public void mousePressed(PApplet p) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(PApplet p) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(PApplet p) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(PApplet p) {
        // TODO Auto-generated method stub
    }

    private int[] getColors(PApplet p){
        int[] colors = new int[WorldConstants.NSTATES];
        for(int i=0;i<WorldConstants.NSTATES;i++){
            colors[i] = p.color(WorldConstants.TERRAIN_COLORS[i][0],
            WorldConstants.TERRAIN_COLORS[i][1],
            WorldConstants.TERRAIN_COLORS[i][2]);
        }
        return colors;  
    }
    
}
