package ca;

import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class TestMajority implements IProcessingApp{

    private int nrows=100;
    private int ncols=100;
    private int nStates=20;
    private int radiusNeigh=4;
    private CellularAutomata ca;
    private int numero;
    private boolean start;
    private int frames=0;
    private SubPlot plt;
    private double[] window = {0,-10,0,10};
    private float[] viewport = {0.3f,0.3f,0.5f,0.5f};


    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        ca = new CellularAutomata(p,plt,nrows,ncols,nStates,radiusNeigh);
        ca.initCa();
    }

    @Override
    public void draw(PApplet p, float dt) {
        if(start){
            if(frames%5==0){//Ciclo para realizar o processo de verificação da regra da maioria
                for(int i=0;i<ca.getCells().length;i++){
                    for(int j=0;j<ca.getCells()[i].length;j++){
                        ca.getCells()[i][j].Majority();
                    }
                }
                for(int i=0;i<ca.getCells().length;i++){//ciclo para atualizar o estado da célula para o próximo frame
                    for(int j=0;j<ca.getCells()[i].length;j++){
                        ca.getCells()[i][j].setState(ca.getCells()[i][j].getStateChange());
                    }
                }
            }
            frames++;
        }
        ca.display(p);
        
    }

    @Override
    public void mousePressed(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.keyPressed&&p.key=='S'||p.key=='s'){
            numero++;
            if(numero%2!=0){
                System.out.println(numero);
                start=true;
            }
            else{
                start=false;
            }
        }
        else if(p.keyPressed&&p.key=='R'||p.key=='r'){
            numero=0;
            start=false;
            ca = new CellularAutomata(p,plt,nrows,ncols,nStates,radiusNeigh);
            ca.initCa();
            frames=0;
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
