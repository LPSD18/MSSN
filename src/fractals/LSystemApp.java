package fractals;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class LSystemApp implements IProcessingApp {

    private LSystem lsys;
    private Turtle turtle;
    private PVector startingPos = new PVector();
    private double[] window = {-15,15,0,15};
    private float[] viewport = {0.1f,0.1f,0.8f,0.8f};
    private SubPlot plt;

    @Override
    public void setup(PApplet p) {
        Rule[] rules = new Rule[2];
        rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
        rules[1] = new Rule('F',"FF");
        lsys = new LSystem("X",rules);
        turtle = new Turtle(5, PApplet.radians(22.5f));
        plt = new SubPlot(window,viewport,p.width,p.height);
    }

    @Override
    public void draw(PApplet p, float dt) {
        float[] bb = plt.getBoundingBox();
        p.rect(bb[0], bb[1], bb[2], bb[3]);
        turtle.setPose(startingPos, PApplet.radians(90), plt, p);
        turtle.render(lsys, plt, p);
        
    }

    @Override
    public void mousePressed(PApplet p) {
        System.out.println(lsys.getSequence());
        lsys.nextGeneration();
        turtle.scaling(0.5f);
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
