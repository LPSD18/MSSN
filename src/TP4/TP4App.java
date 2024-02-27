package TP4;

import java.util.ArrayList;
import java.util.List;


import ecosystem.PopulationAll;
import ecosystem.Terrain;
import ecosystem.WorldConstants;
import processing.core.PApplet;
import processing.core.PImage;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP4App implements IProcessingApp{

    private float[] viewport1 = {0.f,0f,1f,0.9f};
    private float[] viewport0 = {0.f,0f,1f,1f};
    private SubPlot plt;
    private Terrain terrain;
    private PopulationAll population;
    private int start = 0;
    private int type = 1;
    private List<PImage> images;
    private PImage grass,dirt,rock,lava;
    private PImage rato, aguia;
    private boolean change = false;


    @Override
    public void setup(PApplet p) {
        
        plt = new SubPlot(WorldConstants.WINDOW, viewport1, p.width, p.height);
        terrain = new Terrain(p, plt);
        terrain.setStateColors(getColors(p));
        terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
        for(int i=0;i<2;i++)terrain.majorityRule();
        population = new PopulationAll(p,plt,terrain);
        
        grass = p.loadImage("TP4//grass.png");
        grass.resize(40, 40);
        dirt = p.loadImage("TP4//dirt.jpg");
        dirt.resize(40, 40);
        rock = p.loadImage("TP4//rock.jpeg");
        rock.resize(40, 40);
        lava = p.loadImage("TP4//lava.jpg");
        lava.resize(40, 40);
        images = new ArrayList<PImage>();
        images.add(rock);
        images.add(lava);
        images.add(dirt);
        images.add(grass);
        rato = p.loadImage("TP4//rato.jpg");
        rato.resize(60, 60);
        aguia = p.loadImage("TP4//aguia.jpg");
        aguia.resize(60, 60);
    }

    public void setType(int num){
        type = num;
        
    }

    @Override
    public void draw(PApplet p, float dt) {
        if(type==0&&start%2!=0){
            if(!change){
                plt = new SubPlot(WorldConstants.WINDOW, viewport0, p.width, p.height);
                terrain = new Terrain(p, plt);
                terrain.setStateColors(getColors(p));
                terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
                for(int i=0;i<2;i++)terrain.majorityRule();
                population = new PopulationAll(p,plt,terrain);
                change=true;
            }
            terrain.regenerate();
            population.update(dt, terrain);
            terrain.display(p,images);
            population.display(p, plt);
            System.out.println("Número de águias : " + population.predators.size());
            System.out.println("Número de ratos : " + population.preys.size());
            p.textSize(40);
            p.image(aguia, 20, 20);
            p.text("X"+population.predators.size(), 100, 50);
            p.image(rato, 200, 20);
            p.text("X"+population.preys.size(), 280, 50);
        }
        else if(type==1&&start%2!=0){
            p.background(0);
            terrain.regenerate();
            population.update(dt, terrain);
            terrain.display(p);
            population.display(p, plt);
            // float[] bb = plt.getBoundingBox();
            // p.stroke(0);
            // p.rect(bb[0], bb[1], bb[2], bb[3]);
            
            p.textSize(40);
            p.image(aguia, 20, 20);
            p.text("Nº águias : " + population.predators.size(), 100, 50);
            p.image(rato, p.width/2+20, 20);
            p.text("Nº ratos : " + population.preys.size(), p.width/2+100, 50);
        }
        
    }

    @Override
    public void mousePressed(PApplet p) {
        
    }

    @Override
    public void mouseReleased(PApplet p) {
        
    }

    @Override
    public void mouseDragged(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='S'||p.key=='s'){
            start++;
        }
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
