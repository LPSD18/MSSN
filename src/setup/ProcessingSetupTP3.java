package setup;

import java.util.Scanner;

import TP3.TP3B1App;
import TP3.TP3BApp;
import TP3.TP3C1App;
import TP3.TP3CApp;
import TP3.TP3D1App;
import TP3.TP3DApp;
import processing.core.PApplet;

public class ProcessingSetupTP3 extends PApplet{

    public static IProcessingApp app;
    private int lastUpdate;

    @Override
    public void settings(){
        size(800, 600);
    }

    @Override
    public void setup(){
        app.setup(this);
        lastUpdate = millis();
    }

    @Override
    public void draw(){
        int now = millis();
        float dt = (now - lastUpdate)/1000f;
        lastUpdate = now;
        app.draw(this, dt);
    }

    @Override
    public void mousePressed(){
        app.mousePressed(this);
    }

    @Override
	public void mouseReleased(){
		app.mouseReleased(this);
	}

	@Override
	public void mouseDragged(){
		app.mouseDragged(this);
	}

	@Override
	public void keyPressed(){
		app.keyPressed(this);
	}

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Escolha a aplicação desejada");
        System.out.println("1- Jogo do Caos com pontos pré-definidos");
        System.out.println("2- Jogo do Caos com pontos escolhidos interativamente");
        System.out.println("3- LSystem interativo");
        System.out.println("4- LSystem de árvore de frutos");
        System.out.println("5-Conjunto de Mandelbrot");
        System.out.println("6- Conjunto de Julia");
        while (true) {
            int input = scan.nextInt();
            if(input==1){
                app = new TP3BApp();
                break;
            }
            if(input==2){
                app = new TP3B1App();
                break;
            }
            if(input==3){
                TP3CApp c = new TP3CApp();
                c.startRules();
                app = c;
                break;
            }
            if(input==4){
                app = new TP3C1App();
                break;
            }
            if(input==5){
                app = new TP3DApp(); 
                break;
            }
            if(input==6){
                app = new TP3D1App();
                break;
            }
            
        }
        
        PApplet.main(ProcessingSetupTP3.class);
    }
    
}
