package setup;

import java.util.Scanner;

import TP2.TP2CApp;
import TP3.TP3BApp;
import TP4.TP4AppTeste;
import aa.BoidApp;
import aa.FlockTestApp;
import aa.ReynoldsTestApp;
import ca.TestCA;
import ca.TestGoF;
import ca.TestMajority;
import ca.TestMajorityCA;
import chaos.Chaos;
import dla.DLA;
import ecosystem.TestEcosystemApp;
import ecosystem.TestObstaclesApp;
import ecosystem.TestTerrainApp;
import ecosystem.WorldConstants;
import fractals.ForestApp;
import fractals.LSystemApp;
import fractals.LSystemApp2;
import fractals.MandelbrotApp;
import physics.ControlGUIApp;
import physics.FallingBodyApp;
import physics.ParticleSystemApp;
import physics.SolarSystemApp;
import processing.core.PApplet;

public class ProcessingSetup extends PApplet {

	public static IProcessingApp app;
	private int lastUpdate;

	@Override
	public void settings() {
		
		size(1000,800);
		
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

	
	public static void main(String[]args) {
		// app = new TestCA();
		// app = new TestMajorityCA();
		// app = new TestTerrainApp();
		// app = new TestEcosystemApp();
		// app = new TestObstaclesApp();
		// app = new ControlGUIApp();
		// app = new SolarSystemApp();
		// app = new ParticleSystemApp();
		// app = new FallingBodyApp();
		// app = new BoidApp();
		// app = new FlockTestApp();
		// app = new ReynoldsTestApp();
		// app = new TP2C1App();
		// app = new LSystemApp();
		// app = new LSystemApp2();
		// app = new ForestApp();
		// app = new MandelbrotApp();
		// app = new Chaos();
		Scanner scan = new Scanner(System.in);
		System.out.println("Bem vindo à simulação de um ecossistema entre ratos e águias");
		System.out.println("Os ratos irão comer relva e evitar os obstáculos existentes");
		System.out.println("As áquias irão perseguir os ratos para se alimentar");
		System.out.println("Quando tiverem a energia necessária ambos os animais irão se reproduzir");
		System.out.println("Quantos ratos deseja que exista no inicio da simulação?");
		System.out.print("Número de ratos -> ");
		int numRatos = 0;
		while (numRatos==0) {
			numRatos = scan.nextInt();
		}
		System.out.println("Quantas águias deseja que exista no inicio da simulação?");
		System.out.print("Número de águias ->");
		int numAguias = scan.nextInt();
		WorldConstants.INI_PREY_POPULATION = numRatos;
		WorldConstants.INI_PREDATOR_POP = numAguias;
		app = new TP4AppTeste();
		PApplet.main(ProcessingSetup.class);
		
		
		
		
	}
	
}
