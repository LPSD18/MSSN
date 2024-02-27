package setup;

import java.util.Scanner;

import TP4.TP4App;
import ecosystem.WorldConstants;
import processing.core.PApplet;

public class ProcessingSetupTP4 extends PApplet{
    
    


    public static IProcessingApp app;
	private int lastUpdate;

	@Override
	public void settings() {
		
		size(1200,900);
		
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
		TP4App tp4 = new TP4App();
		// app = new TP4App();
		WorldConstants.INI_PREY_POPULATION = numRatos;
		WorldConstants.INI_PREDATOR_POP = numAguias;
		System.out.println("Prefere ver a aplicação com texturas ou sem?");
		System.out.println("0 -> Com texturas");
		System.out.println("1 -> Sem texturas");
		tp4.setType(scan.nextInt());
		app = tp4;
		System.out.println("Para iniciar/parar a simulação clicar na tecla S");
		PApplet.main(ProcessingSetupTP4.class);
		scan.close();
		
		
		
	}
}
