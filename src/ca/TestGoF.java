package ca;

import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class TestGoF implements IProcessingApp{


    //State=0 célula morta
    //State=1 célula viva
    

    private int nrows=100;
    private int ncols=100;
    private int nStates = 2;
    private int radiusNeigh=1;
    private CellularAutomata ca;
    private int numero=0;
    private boolean start=false;
    private String survive,born;
    public int vazio;
    public int frame;
    private SubPlot plt;
    private double[] window = {0,-10,0,10};
    private float[] viewport = {0.3f,0.3f,0.5f,0.5f};
    
    public void setCA(String SurviveRules,String BornRules){
        survive=SurviveRules;
        born =BornRules;
    }

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        ca = new CellularAutomata(p,plt,nrows,ncols,nStates,radiusNeigh,survive,born);
        if(vazio==1)
            ca.initCa();
    

    }

    @Override
    public void draw(PApplet p, float dt) {
        if(start){
            if(frame%5==0){
                for(int i=0;i<ca.getCells().length;i++){//Ciclo para verificar se a célula deve estar viva ou morta 
                    for(int j=0;j<ca.getCells()[i].length;j++){
                        ca.getCells()[i][j].GameOfLife(p,ca.getSurviveRules(), ca.getBornRules());
                    }
                }
                for(int i=0;i<ca.getCells().length;i++){//ciclo para alterar o estado da célula para o proximo frame
                    for(int j=0;j<ca.getCells()[i].length;j++){
                        if(ca.getCells()[i][j].getAlive()){
                            ca.getCells()[i][j].setState(1);
                        }
                        else{
                            ca.getCells()[i][j].setState(0);
                        }
                    }
                }
                
            }
            frame++;
        }
        ca.display(p);
    }

    @Override
    public void mousePressed(PApplet p) {
        Cell cell = ca.pixel2Cell(p.mouseX, p.mouseY);
        if(cell.getState()==1)
        cell.setState(0);
        else cell.setState(1);

    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.keyPressed&&p.key=='S'||p.key=='s'){//iniciar o jogo da vida
            numero++;
            if(numero%2!=0){
                start=true;
            }
            else{
                start=false;
            }
        }
        if(p.keyPressed&&p.key=='R'||p.key=='r'){//Reniciar o autómato celular
            start=false;
            ca = new CellularAutomata(p,plt,nrows,ncols,nStates,radiusNeigh,survive,born);
            if(vazio==1)
            ca.initCa();
            numero=0;
        }
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