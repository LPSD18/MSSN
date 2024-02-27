package setup;

import java.util.Scanner;

import TP2.TP2CApp;
import TP2.TP2DApp;
import TP2.TP2E1App;
import TP2.TP2E2App;
import TP2.TP2E4App;
import processing.core.PApplet;

public class ProcessingSetupTP2 extends PApplet {

	public static IProcessingApp app;
	private int lastUpdate;

	@Override
	public void settings() {
		
		size(800,600);
		
	}
	
	@Override
	public void setup() {
		
		app.setup(this);
		lastUpdate = millis();
	}
	
	@Override
	public void draw() {
		int now = millis();
		float dt = (now-lastUpdate)/1000f;
		lastUpdate = now;
		app.draw(this,dt);
		
	}

	@Override
	public void mousePressed(){
		app.mousePressed(this);
		
	}
	@Override
	public void keyPressed(){
		app.keyPressed(this);
	}

	
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Escolha a aplicação desejada:");
		System.out.println("1-Solar System");
		System.out.println("2-AA comportamentos individuais");
		System.out.println("3-Flock, predador e presa");
		System.out.println("4-Flock, boids explosivos");
		System.out.println("5-Flock, Debugging");
		while (true) {
			int input = scan.nextInt();
			if(input==1){
				app = new TP2CApp();
				break;
			}
			else if(input==2){
				app = new TP2DApp();
				break;
			}
			else if(input==3){
				app = new TP2E1App();
				break;
			}
			else if(input==4){
				app = new TP2E2App();
				break;
			}
			else if(input==5){
				app = new TP2E4App();
				break;
			}
		}
		PApplet.main(ProcessingSetupTP2.class);		
	}
	
}
