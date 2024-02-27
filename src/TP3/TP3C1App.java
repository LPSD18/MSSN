package TP3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fractals.Rule;
import fractals.Tree;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP3C1App implements IProcessingApp {

    private double[] window = {-15,15,0,15};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private List<Tree> forest;
    private Rule[] rules;
    private boolean start = false;
    private String axiom="";
    private float angle=0;
    private int niter=0;
    private float reflen=0f;


    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        forest = new ArrayList<Tree>();
    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(255);
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
        rules = new Rule[2];
        rules[0] = new Rule('F', "G[+F]-F");
        rules[1] = new Rule('G', "GG");
        Tree tree = new Tree("F", rules, pos, 1,PApplet.radians(22.5f), 6, 0.5f, 5, p);
        forest.add(tree);
    }

    @Override
    public void mouseReleased(PApplet p) {
        
    }

    @Override
    public void mouseDragged(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='r'||p.key=='R'){
            p.clear();
            p.background(255);
            forest = new ArrayList<Tree>();            
            
        }
    }
}
