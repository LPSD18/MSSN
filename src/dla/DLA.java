package dla;

import java.util.ArrayList;
import java.util.List;

import dla.Walker.State;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

public class DLA implements IProcessingApp{

    private List<Walker> walkers;
    private int NUM_WALKERS = 500;
    private int NUM_STEPS_PER_FRAME = 50;
    private boolean start=false;
    private int numero=0;
    private float stick;


    @Override
    public void setup(PApplet p) {
        p.background(128);
        walkers = new ArrayList<Walker>();
        Walker w = new Walker(p, new PVector(p.width/2,p.height/2));
        w.setStick(stick);
        walkers.add(w);
        
        

        for(Walker s : walkers){
                s.display(p);
            }
        for(int i =0;i<NUM_WALKERS;i++){
            w = new Walker(p);
            w.setStick(stick);
            walkers.add(w);
        }
    }

    @Override
    public void draw(PApplet p, float dt) {
        
        if(start==true){
            p.background(128);
            for(int i=0;i<NUM_STEPS_PER_FRAME;i++){
                for(Walker w : walkers){
                    if(w.getState()==State.WANDER){
                        w.wander(p);
                        w.updateState(p, walkers);
                    }
                }
            }
            for(Walker w : walkers){
                w.display(p);
            }
            //Exercicio 1 DLA
            //Com uma condição If verifica-se o número de wanders e caso seja menor que 100 adiciona-se um novo Walker num estado de Wander
            if(Walker.num_wanders<50){ 
                Walker w = new Walker(p);
                w.setStick(stick);
                walkers.add(w);
            }
        }
        
    }

    @Override
    public void mousePressed(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        
        if(p.keyPressed==true && p.key == 'S' || p.key=='s'){
            numero++;
            if(numero%2!=0){
                start=true;
            }
            else{ 
                start=false;
            }
        }
        else if (p.keyPressed==true&&p.key=='R'||p.key=='r'){
            p.clear();
            p.background(128);
            numero=0;
            start=false;
            walkers = new ArrayList<Walker>();
            Walker w = new Walker(p, new PVector(p.width/2,p.height/2));
            w.setStick(stick);
            walkers.add(w);
            for(Walker s : walkers){
                s.display(p);
            }
            for(int i =0;i<NUM_WALKERS;i++){
                w = new Walker(p);
                w.setStick(stick);
                walkers.add(w);
            }
            Walker.reset(NUM_WALKERS,1);
        }
    }

    public void setStick(float stick){
        this.stick=stick;
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
