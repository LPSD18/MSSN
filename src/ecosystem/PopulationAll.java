package ecosystem;

import java.util.ArrayList;
import java.util.List;

import aa.Align;
import aa.AvoidObstacle;
import aa.Boid;
import aa.Cohesion;
import aa.Eye;
import aa.Flee;
import aa.Pursuit;
import aa.Seek;
import aa.Separate;
import aa.Wander;
import physics.Body;
import processing.core.PApplet;
import processing.core.PVector;
import tools.SubPlot;

public class PopulationAll {
    
    public List<Body> preys;
    public List<Predator> predators;
    public List<Body> todos;//teste
    private double[] window;
    private Terrain terrain;
    private boolean mutate = true;

    public PopulationAll(PApplet p, SubPlot plt, Terrain terrain){
        window = plt.getWindow();
        preys = new ArrayList<Body>();
        predators = new ArrayList<Predator>();
        this.terrain = terrain;
        List<Body> obstacles = terrain.getObstacles();
        //teste
        todos = new ArrayList<Body>();
        for(Body pred : predators){
            todos.add(pred);
        }
        for(Body obs : obstacles){
            todos.add(obs);
        }
        //até aqui

        for(int i=0;i<WorldConstants.INI_PREY_POPULATION;i++){
            PVector pos = new PVector(p.random((float)window[0],(float)window[1]), p.random((float)window[2],(float)window[3]));
            int color = p.color(WorldConstants.PREY_COLOR[0],WorldConstants.PREY_COLOR[1],WorldConstants.PREY_COLOR[2]);
            Prey a = new Prey(pos, WorldConstants.PREY_MASS, WorldConstants.PREY_SIZE, color, p, plt);
            a.addBehavior(new Wander(1));
            a.addBehavior(new AvoidObstacle(0));
            a.addBehavior(new Flee(0));
            a.addBehavior(new Separate(1));
            a.addBehavior(new Cohesion(1));
            a.addBehavior(new Align(1));
            
            // Eye eye = new Eye(a, todos);//codigo teste
            Eye eye = new Eye(a, obstacles);//codigo não teste
            a.setEye(eye);
            preys.add(a);
        }

        for(int i=0;i<WorldConstants.INI_PREDATOR_POP;i++){
            PVector pos = new PVector(p.random((float)window[0],(float)window[1]), p.random((float)window[2],(float)window[3]));
            int color = p.color(WorldConstants.PREDATOR_COLOR[0],WorldConstants.PREDATOR_COLOR[1],WorldConstants.PREDATOR_COLOR[2]);
            Predator a = new Predator(pos, WorldConstants.PREDATOR_MASS, WorldConstants.PREDATOR_SIZE, color, preys, p, plt);
            a.addBehavior(new Seek(1f));
            // a.addBehavior(new Wander(1));
            Eye eye = new Eye(a, preys);
            a.setEye(eye);
            predators.add(a);
            todos.add(a);//teste
        }
    }

    public void update(float dt, Terrain terrain){
        move(terrain,dt);
        if(preys.size()>1){
            for(int i=0;i<predators.size();i++){
                int nPrey = predators.get(i).checkForPrey(preys);
                predators.get(i).getEye().setTarget(preys.get(nPrey));
            }
        }
        //teste
        for(int i=0;i<preys.size();i++){
            int nPred = ((Prey) preys.get(i)).checkForPred(predators);
            ((Boid) preys.get(i)).getEye().setTarget(predators.get(nPred));
        }
        //ate aqui
        eat(terrain);
        energy_consumption(dt, terrain);
        reproduce(mutate);
        die();
        
    }

    public void move(Terrain terrain,float dt){
        for(Body a : preys){
            ((Prey) a).applyBehaviors(dt);
        }
        for(Predator a : predators){
            a.applyBehaviors(dt);
        }
    }

    public void eat(Terrain terrain){
        for(Body a : preys){
            ((Prey) a).eat(terrain);
        }
        for(Predator a : predators){
            a.eat(terrain);
        }
    }

    public void energy_consumption(float dt, Terrain terrain) {
        for(Body a : preys){
            ((Prey) a).energy_consumption(dt, terrain);
        }
        for(Predator a : predators){
            a.energy_consumption(dt, terrain);
        }
    }

    public void reproduce(boolean mutate){
        for(int i = preys.size()-1;i>=0;i--){
            Prey a = (Prey) preys.get(i);
            Prey child = (Prey) a.reproduce(mutate);
            if(child!=null){
                Eye eye = new Eye(child, terrain.getObstacles());
                child.setEye(eye);
                preys.add(child);
            }
        }
        for(int i = predators.size()-1;i>=0;i--){
            Predator a = predators.get(i);
            Predator child = (Predator) a.reproduce(mutate);
            if(child!=null){
                Eye eye = new Eye(child, preys);
                child.setEye(eye);
                predators.add(child);
                todos.add(child);
            }
        }
    }

    public void die(){
        for(int i = preys.size()-1;i>=0;i--){
            Prey a = (Prey) preys.get(i);
            if(a.die()){
                preys.remove(i);
            }
        }
        for(int i = predators.size()-1;i>=0;i--){
            Predator a = predators.get(i);
            if(a.die()){
                predators.remove(i);
            }
        }
    }

    public void display(PApplet p, SubPlot plt){
        for(Body a : preys){
            a.display(p, plt);
        }
        for(Predator a : predators){
            a.display(p, plt);
        }
    }
    //código teste para baixo
    public float getMeanMaxSpeed(){
        float sum = 0;
        for(Body a : preys){
            sum += ((Boid) a).getDNA().maxSpeed;
        }
        return sum/preys.size();
    }

    public float getMeanMaxSpeedPredators(){
        float sum = 0;
        for(Body a : predators){
            sum += ((Boid) a).getDNA().maxSpeed;
        }
        return sum/predators.size();
    }

    public float getStdMaxSpeed(){
        float mean = getMeanMaxSpeed();
        float sum = 0;
        for(Body a : preys){
            sum += Math.pow(((Boid) a).getDNA().maxSpeed - mean , 2);
        }
        return (float)Math.sqrt(sum/preys.size());
    }

    public float getStdMaxSpeedPredators(){
        float mean = getMeanMaxSpeed();
        float sum = 0;
        for(Body a : predators){
            sum += Math.pow(((Boid) a).getDNA().maxSpeed - mean , 2);
        }
        return (float)Math.sqrt(sum/predators.size());
    }


    public float[] getMeanWeights(){
        float[] sums = new float[3];
        for(Body a : preys){
            sums[0] += ((Boid) a).getBehaviors().get(0).getWeight();
            sums[1] += ((Boid) a).getBehaviors().get(1).getWeight();
            sums[2] += ((Boid) a).getBehaviors().get(2).getWeight();
        }
        sums[0] /= preys.size();
        sums[1] /= preys.size();
        sums[2] /= preys.size();
        return sums;
    }

}
