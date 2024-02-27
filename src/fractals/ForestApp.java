package fractals;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class ForestApp implements IProcessingApp{

    private double[] window = {-15,15,0,15};
    private float[] viewport = {0f,0f,1f,1f};
    private SubPlot plt;
    private List<Tree> forest;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        forest = new ArrayList<Tree>();
    }

    @Override
    public void draw(PApplet p, float dt) {
        float[] bb = plt.getBoundingBox();
        p.rect(bb[0],bb[1],bb[2],bb[3]);
        for(Tree tree : forest){
            tree.grow(dt); 
            tree.display(p, plt);
        }
    }

    @Override
    public void mousePressed(PApplet p) {
        double[] w = plt.getWorldCoord(p.mouseX, p.mouseY);
        PVector pos = new PVector((float)w[0],(float)w[1]);
        // if(p.random(1)<0.5f){
        //     Rule[] rules = new Rule[1];
        //     rules[0] = new Rule('F', "FF+[+F-F-F]-[-F+F+F]");
        //     Tree tree = new Tree("F", rules, pos, 0.4f, PApplet.radians(22.5f),3,0.5f,5, p);
        //     forest.add(tree);
        // }
        // else{
        //     Rule[] rules = new Rule[2];
        //     // rules[0] = new Rule('X', "F+[[X]-X]-F[-FX]+X");
        //     // rules[1] = new Rule('F',"FF");
        //     rules[0] = new Rule('F', "F+G");
        //     rules[1] = new Rule('G', "F-G");
        //     Tree tree = new Tree("X", rules, pos, 0.4f, PApplet.radians(22.5f),3,0.5f,5, p);
        //     forest.add(tree);
        // }
        Rule[] rules = new Rule[2];
        rules[0] = new Rule('F', "F-G+F+G-F");
        rules[1] = new Rule('G',"GG");
        Tree tree = new Tree("F", rules, pos, 1f, PApplet.radians(120f),6,0.5f,5, p);
        forest.add(tree);
        // Rule[] rules = new Rule[2];
        // rules[0] = new Rule('F', "F+G");
        // rules[1] = new Rule('G', "F-G");
        // Tree tree = new Tree("X", rules, pos, 0.4f, PApplet.radians(90f),3,0.5f,5, p);
        // forest.add(tree);
        // System.out.println("gbvf");
    }

    @Override
    public void keyPressed(PApplet p) {
        
    }

    @Override
    public void mouseReleased(PApplet p) {
        
    }

    @Override
    public void mouseDragged(PApplet p) {
        
    }
    
}
