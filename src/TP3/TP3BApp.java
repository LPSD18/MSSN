package TP3;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;

public class TP3BApp implements IProcessingApp{
    
    private PVector currentPoint;
    private List<PVector> initialPoints;
    private boolean start=false;

    private void initShapeTriangle(PApplet p){
        initialPoints = new ArrayList<PVector>();
        initialPoints.add(new PVector(p.width-150,p.height-50));
        initialPoints.add(new PVector(150,p.height-50));
        initialPoints.add(new PVector(p.width/2,25));
        for(PVector point : initialPoints){
            p.stroke(255);
            p.point(point.x,point.y);
        }
    }

    private void initShapeSquare(PApplet p){
        initialPoints = new ArrayList<PVector>();
        initialPoints.add((new PVector(100,100)));
        initialPoints.add(new PVector(100,p.height-100));
        initialPoints.add(new PVector(p.width-100,100));
        initialPoints.add(new PVector(p.width-100,p.height-100));
        for(PVector point : initialPoints){
            p.stroke(0);
            p.point(point.x, point.y);
        }
    }

    private void initShapePentagon(PApplet p){
        initialPoints = new ArrayList<PVector>();
        
        initialPoints.add(new PVector(0,100));
        initialPoints.add(new PVector(p.width-150,p.height-50));
        initialPoints.add(new PVector(150,p.height-50));
        initialPoints.add(new PVector(p.width,100));
        initialPoints.add(new PVector(p.width/2,25));
        
        for(PVector point : initialPoints){
            p.stroke(p.color(p.random(255)),p.color(p.random(255)),p.color(p.random(255)));;
            p.point(point.x, point.y);
        }
    }

    private void initShapeHexagon(PApplet p){
        initialPoints = new ArrayList<PVector>();
        initialPoints.add(new PVector(p.width/2,25));
        initialPoints.add(new PVector(p.width/2,p.height-25));
        initialPoints.add((new PVector(100,200)));
        initialPoints.add(new PVector(100,p.height-200));
        initialPoints.add(new PVector(p.width-100,200));
        initialPoints.add(new PVector(p.width-100,p.height-200));
        for(PVector point : initialPoints){
            p.stroke(p.color(p.random(255)),p.color(p.random(255)),p.color(p.random(255)));;
            p.point(point.x, point.y);
        }
    }

    private void initShapeHeptagon(PApplet p){
        p.stroke(255);
        p.strokeWeight(2);
        initialPoints = new ArrayList<PVector>();
        initialPoints.add(new PVector(p.width/2,50));
        initialPoints.add(new PVector(p.width/4,100));
        initialPoints.add(new PVector(3*p.width/4,100));
        initialPoints.add((new PVector(p.width/5,3*p.height/5)));
        initialPoints.add(new PVector(4*p.width/5,3*p.height/5));
        initialPoints.add(new PVector(3*p.width/8,p.height-50));
        initialPoints.add(new PVector(5*p.width/8,p.height-50));
        for(PVector point : initialPoints){
            p.stroke(p.color(p.random(255)),p.color(p.random(255)),p.color(p.random(255)));;
            p.point(point.x, point.y);
        }
        

    }

    private void drawInitialRandomPoint(PApplet p){
        PVector random = new PVector(p.random(p.width),p.random(p.height));
        p.point(random.x, random.y);
        currentPoint = random;
    }

    @Override
    public void setup(PApplet p) {
        System.out.println("Para dar inicio à simulação clicar na tecla s");
        System.out.println("Para aumentar o número de vértices clicer na tecla +");
        System.out.println("Para diminuir o número de vértices clicer na tecla -");
        initShapeTriangle(p);
        drawInitialRandomPoint(p);
        p.background(0);
    }
    @Override
    public void draw(PApplet p, float dt) {
        p.stroke(255);
        if(start){
            for(int i=0;i!=1000;i++){
                p.stroke(p.color(p.random(255),p.random(255),p.random(255)));
                int r = (int) Math.floor(p.random(initialPoints.size()));
                if(initialPoints.size()==3){
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.5f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.5f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if(initialPoints.size()==4){
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.55f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.55f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==5) {
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.618f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.618f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==6) {
                    float x = PApplet.lerp(currentPoint.x, initialPoints.get(r).x, 0.667f);
                    float y = PApplet.lerp(currentPoint.y, initialPoints.get(r).y, 0.667f);
                    p.point(x,y);
                    currentPoint = new PVector(x,y);
                }
                if (initialPoints.size()==7) {
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
        if(p.key=='+'){
            if(initialPoints.size()==3){
                initShapeSquare(p);
            }
            else if(initialPoints.size()==4){
                initShapePentagon(p);
            }
            else if(initialPoints.size()==5){
                initShapeHexagon(p);
            }
            else if(initialPoints.size()==6){
                initShapeHeptagon(p);
            }
        }
        if(p.key=='-'){
            if(initialPoints.size()==7){
                initShapeHexagon(p);
            }
            else if(initialPoints.size()==6){
                initShapePentagon(p);
            }
            else if(initialPoints.size()==5){
                initShapeSquare(p);
            }
            else if(initialPoints.size()==4){
                initShapeTriangle(p);
            }
        }
    }

}

