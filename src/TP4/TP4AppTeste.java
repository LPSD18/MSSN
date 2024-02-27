package TP4;

import java.util.ArrayList;
import java.util.List;

import aa.AvoidObstacle;
import aa.Eye;
import aa.Flee;
import aa.Seek;
import aa.Wander;
import ecosystem.Patch;
import ecosystem.PopulationAll;
import ecosystem.Predator;
import ecosystem.Prey;
import ecosystem.Terrain;
import ecosystem.WorldConstants;
import ecosystem.WorldConstants.PatchType;
import physics.PSControl;
import physics.ParticleSystem;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;
import tools.TimeGraph;

public class TP4AppTeste  implements IProcessingApp{

    private float[] viewport = {0,0,1f,1f};
    private SubPlot plt;
    private Terrain terrain;
    private PopulationAll population;
    private int start = 0;
    private List<PImage> images;
    private PImage grass,dirt,rock,lava;
    private PImage rato,aguia;

    //código teste
    private PSControl psc;
    private float[] VelParams = {PApplet.radians(360),PApplet.radians(360),0.1f,0.1f};
    private float[] lifetimeParams = {1,2};
    private float[] radiusParams = {0.05f,0.05f};
    private float flow = 10;
    private List<ParticleSystem> pss;


    // private float timeDuration = 60;
    // private float refPopulation = 150;
    // private float refMeanMaxSpeed = 1f;
    // private float refStdMaxSpeed = 0.2f;
    // private double[]winGraph1 = {0,timeDuration,0,2*refPopulation};
    // private double[]winGraph2 = {0,timeDuration,0,2*refMeanMaxSpeed};
    // private double[]winGraph3 = {0,timeDuration,0,2*refStdMaxSpeed};
    // private float[] viewgraph1 = {.71f,.04f,.28f,.28f};
    // private float[] viewgraph2 = {.71f,.37f,.28f,.28f};
    // private float[] viewgraph3 = {.71f,.70f,.28f,.28f};
    // private SubPlot pltGraph1, pltGraph2, pltGraph3;
    // private TimeGraph tg1, tg2, tg3;
    private float timer, updateGraphTime;
    private float intervalUpdate = 1;

    private int animal = 2;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(WorldConstants.WINDOW, viewport, p.width, p.height);
        // pltGraph1 = new SubPlot(winGraph1, viewgraph1, p.width, p.height);
        // pltGraph2 = new SubPlot(winGraph2, viewgraph2, p.width, p.height);
        // pltGraph3 = new SubPlot(winGraph3, viewgraph3, p.width, p.height);

        // tg1 = new TimeGraph(p,pltGraph1,p.color(255,0,0),refPopulation);
        // tg2 = new TimeGraph(p,pltGraph2,p.color(255,0,0),refMeanMaxSpeed);
        // tg3 = new TimeGraph(p,pltGraph3,p.color(255,0,0),refStdMaxSpeed);
        terrain = new Terrain(p, plt);
        terrain.setStateColors(getColors(p));
        terrain.initRandomCustom(WorldConstants.PATCH_TYPE_PROB);
        for(int i=0;i<2;i++)terrain.majorityRule();
        population = new PopulationAll(p, plt, terrain);
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
        pss = new ArrayList<ParticleSystem>();
        for(int i=0;i<WorldConstants.NROWS;i++){
            for(int j=0;j<WorldConstants.NCOLS;j++){
                if(terrain.getCells()[i][j].getState()==PatchType.OBSTACLE.ordinal()){
                    double[] ww = plt.getWorldCoord(terrain.xmin+terrain.getCells()[i][j].col*terrain.getCellWidth()+terrain.getCellWidth()/2
                    ,terrain.ymin+terrain.getCells()[i][j].row*terrain.getCellHeight()+terrain.getCellHeight()/2);
                    int color = p.color(128,128,128);
                    psc = new PSControl(VelParams, lifetimeParams, radiusParams, flow, color);
                    ParticleSystem ps = new ParticleSystem(new PVector((float)ww[0],(float)ww[1]), new PVector(), 1, 0.001f, psc);
                    pss.add(ps);
                }
            }
        }
        timer = 0;
        updateGraphTime = timer+intervalUpdate;
    }

    @Override
    public void draw(PApplet p, float dt) {
        
        timer+=dt;
        terrain.regenerate();
        population.update(dt, terrain);
        terrain.display(p,images);
        // terrain.display(p);
        population.display(p, plt);
        for(ParticleSystem ps : pss){
            ps.applyForce(new PVector());
            ps.move(dt);
            ps.display(p, plt);
        }
        // p.textSize(40);
        // p.image(aguia, 20, 20);
        // p.text("Nº águias : " + population.predators.size(), 100, 50);
        // p.image(rato, p.width/2-20, 20);
        // p.text("Nº ratos : " + population.preys.size(), p.width/2+50, 50);
        if(timer>updateGraphTime){
            
            System.out.println(String.format("Time = %ds", (int)timer));
            System.out.println("numPreys = " + population.preys.size());
            System.out.println("numPredators = " + population.predators.size());
            System.out.println("Preys MeanMaxSpeed  = " + population.getMeanMaxSpeed());
            System.out.println("Predators MeanMaxSpeed  = " + population.getMeanMaxSpeedPredators());
            System.out.println("mean Weight Wander = " + population.getMeanWeights()[0] + " mean Weight Avoid = " + population.getMeanWeights()[1] + " mean Weight Flee " + population.getMeanWeights()[2] );
            System.out.println("");
            // tg1.plot(timer,population.preys.size());
            // tg2.plot(timer,population.getMeanMaxSpeed());
            // tg3.plot(timer,population.getStdMaxSpeed());
            updateGraphTime = timer + intervalUpdate;
        }
    }

    @Override
    public void mousePressed(PApplet p) {
        if(animal==0){
            double[]ww = plt.getWorldCoord(p.mouseX, p.mouseY);
            Prey prey = new Prey(new PVector((float)ww[0],(float)ww[1]), WorldConstants.PREY_MASS,WorldConstants.PREY_SIZE,
            p.color(WorldConstants.PREY_COLOR[0],WorldConstants.PREY_COLOR[1],WorldConstants.PREY_COLOR[2]), p, plt);
            prey.addBehavior(new Wander(1));
            prey.addBehavior(new AvoidObstacle(0));
            prey.addBehavior(new Flee(0));
            Eye eye = new Eye(prey, population.todos);//codigo teste
            // Eye eye = new Eye(prey, terrain.getObstacles());//codigo não teste
            prey.setEye(eye);
            population.preys.add(prey);

        }
        if(animal==1&&population.preys.size()>0){
            double[]ww = plt.getWorldCoord(p.mouseX, p.mouseY);
            Predator predator = new Predator(new PVector((float)ww[0],(float)ww[1]), WorldConstants.PREDATOR_MASS, WorldConstants.PREDATOR_SIZE, 
            p.color(WorldConstants.PREDATOR_COLOR[0],WorldConstants.PREDATOR_COLOR[1],WorldConstants.PREDATOR_COLOR[2]), population.preys, p, plt);
            predator.addBehavior(new Seek(1));
            Eye eye = new Eye(predator, population.preys);
            predator.setEye(eye);
            population.predators.add(predator);
            population.todos.add(predator);
        }
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
            animal=0;
        }
        if(p.key=='A'||p.key=='a'){
            animal=1;
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
