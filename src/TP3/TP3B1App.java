package TP3;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

public class TP3B1App implements IProcessingApp{
    
    private PVector currentPoint;
    private List<PVector> initialPoints;
    private boolean start=false;
    

    private void drawInitialRandomPoint(PApplet p){
        PVector random = new PVector(p.random(p.width),p.random(p.height));
        p.point(random.x, random.y);
        currentPoint = random;
    }

    @Override
    public void setup(PApplet p) {
        System.out.println("Para dar inicio à simulação clicar na tecla s");
        System.out.println("Escolher os vertices com o botão esquerdo do rato");
        initialPoints = new ArrayList<PVector>();
        drawInitialRandomPoint(p);
        p.background(0);
    }
    @Override
    public void draw(PApplet p, float dt) {
        p.stroke(255);
        if(start){
            for(int i=0;i!=1000;i++){
                if(initialPoints.size()==3){
                    int r = (int) Math.floor(p.random(initialPoints.size()));
                    if(r==0){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(0)));
                    }
                    else if(r==1){
                        p.stroke(p.color(p.random(0),p.random(255),p.random(0)));
                    }
                    else if(r==2){
                        p.stroke(p.color(p.random(0),p.random(0),p.random(255)));
                    }
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.5f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.5f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if(initialPoints.size()==4){
                    int r = (int) Math.floor(p.random(initialPoints.size()));
                    if(r==0){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(0)));
                    }
                    else if(r==1){
                        p.stroke(p.color(p.random(0),p.random(255),p.random(0)));
                    }
                    else if(r==2){
                        p.stroke(p.color(p.random(0),p.random(0),p.random(255)));
                    }
                    else if(r==3){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(255)));
                    }
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.55f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.55f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==5) {
                    int r = (int) Math.floor(p.random(initialPoints.size()));
                    if(r==0){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(0)));
                    }
                    else if(r==1){
                        p.stroke(p.color(p.random(0),p.random(255),p.random(0)));
                    }
                    else if(r==2){
                        p.stroke(p.color(p.random(0),p.random(0),p.random(255)));
                    }
                    else if(r==3){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(255)));
                    }
                    else if(r==4){
                        p.stroke(p.color(p.random(255),p.random(255),p.random(0)));
                    }
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.618f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.618f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==6) {
                    int r = (int) Math.floor(p.random(initialPoints.size()));
                    if(r==0){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(0)));
                    }
                    else if(r==1){
                        p.stroke(p.color(p.random(0),p.random(255),p.random(0)));
                    }
                    else if(r==2){
                        p.stroke(p.color(p.random(0),p.random(0),p.random(255)));
                    }
                    else if(r==3){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(255)));
                    }
                    else if(r==4){
                        p.stroke(p.color(p.random(255),p.random(255),p.random(0)));
                    }
                    else if(r==5){
                        p.stroke(p.color(p.random(255),p.random(255),p.random(128)));
                    }
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.667f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.667f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==7) {
                    int r = (int) Math.floor(p.random(initialPoints.size()));
                    if(r==0){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(0)));
                    }
                    else if(r==1){
                        p.stroke(p.color(p.random(0),p.random(255),p.random(0)));
                    }
                    else if(r==2){
                        p.stroke(p.color(p.random(0),p.random(0),p.random(255)));
                    }
                    else if(r==3){
                        p.stroke(p.color(p.random(255),p.random(0),p.random(255)));
                    }
                    else if(r==4){
                        p.stroke(p.color(p.random(255),p.random(255),p.random(0)));
                    }
                    else if(r==5){
                        p.stroke(p.color(p.random(255),p.random(255),p.random(128)));
                    }
                    else if(r==6){
                        p.stroke(p.color(p.random(255),p.random(128),p.random(128)));
                    }
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.692f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.692f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
            }
        }
    }
    @Override
    public void mousePressed(PApplet p) {
        initialPoints.add(new PVector(p.mouseX,p.mouseY));
        p.point(p.mouseX, p.mouseY);
    }

    @Override
    public void mouseReleased(PApplet p) {
        
    }

    @Override
    public void mouseDragged(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        p.background(0);
        if(p.key=='s'){
            start=true;
        }
        if(p.key=='r'){
            start=false;
            this.initialPoints = new ArrayList<PVector>(); 
            p.clear();
            p.background(0);
            
        }
        
    }
}
