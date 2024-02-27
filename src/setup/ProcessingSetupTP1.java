package setup;

import java.util.Scanner;
import ca.TestCA;
import ca.TestGoF;
import ca.TestMajority;
import dla.DLA;
import processing.core.PApplet;

public class ProcessingSetupTP1 extends PApplet {

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
		System.out.println("Escolha a aplicação desejada");
		System.out.println("1 - Game of Life 2 - DLA 3 - Majority");
		while (true) {
			int input = scan.nextInt();
			if(input==1){
				TestGoF GoF = new TestGoF();
				String survive="";
				String born="";
				System.out.println("Introduza as regras desejadas");
				System.out.print("Survive Rules ->  ");
				while(survive.length()==0)
				survive = scan.nextLine();
				System.out.print("Born rules ->  ");
				while(born.length()==0)
				born = scan.nextLine();
				GoF.setCA(survive,born);
				System.out.println("Deseja iniciar com a grelha a vazio ou random?");
				System.out.println("0-Vazio 1-Random");
				GoF.vazio=scan.nextInt();
				app = GoF;
				System.out.println("Para iniciar/parar o jogo clicar na tecla S");
				System.out.println("Para fazer reset ao jogo clicar na tecla R");
				break;
			}
			else if(input==2){
				DLA DLA = new DLA();
				System.out.println("Introduza o stickness desejado");
				float stick=scan.nextFloat();
				DLA.setStick(stick);
				app = DLA;
				System.out.println("Para iniciar/parar o jogo clicar na tecla S");
				System.out.println("Para fazer reset ao jogo clicar na tecla R");
				break;
			}
			else if(input==3){
				app = new TestMajority();
				System.out.println("Para iniciar/parar o jogo clicar na tecla S");
				System.out.println("Para fazer reset ao jogo clicar na tecla R");
				break;
			}

		
		}
		PApplet.main(ProcessingSetupTP1.class);
		scan.close();
		// app = new TestCA();
		
		
		
		
	}
	
}
