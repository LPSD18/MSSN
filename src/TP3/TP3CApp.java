package TP3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fractals.Rule;
import fractals.Tree;
import processing.core.PApplet;
import processing.core.PVector;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP3CApp implements IProcessingApp {

    private double[] window = {-15,15,0,15};
    private float[] viewport = {0,0,1,1};
    private SubPlot plt;
    private List<Tree> forest;
    private Rule[] rules;
    private String axiom="";
    private float angle=0;
    private int niter=0;
    private float reflen=0f;

    public void startRules(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Bem vindo à simulação de um Sistema de Lindenmayer");
        System.out.println("Introduza o número de regras desejadas");
        int numRules = scan.nextInt();
        rules = new Rule[numRules];
        for(int i=0;i<numRules;i++){
            System.out.println("Introduza o simbolo desta regra:");
            char symbol =' ';
            String help = "";
            while (help.length()==0) {
                help = scan.nextLine();
            }
            System.out.println("Introduza agora a String desta regra");
            symbol = help.charAt(0);
            String string = scan.nextLine();
            rules[i] = new Rule(symbol, string);
        }
        System.out.println("Agora introduza o axioma inicial");
        axiom = scan.nextLine();
        System.out.println("Agora o angulo");
        angle = scan.nextFloat();
        System.out.println("Introduza o número de iterações desejado");
        niter = scan.nextInt();
        System.out.println("Introduza o tamanho desejado");
        reflen = scan.nextFloat();
    }

    private void updateRules(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduza o número de regras desejadas");
        int rulesNum=0;
        while (rulesNum==0) {
            rulesNum = scan.nextInt();
        }
        rules = new Rule[rulesNum];
        for(int i=0;i<rulesNum;i++){
            System.out.println("Introduza o simbolo desta regra:");
            char symbol =' ';
            String help = "";
            while (help.length()==0) {
                help = scan.nextLine();
            }
            System.out.println("Introduza agora a String desta regra");
            symbol = help.charAt(0);
            String string = scan.nextLine();
            rules[i] = new Rule(symbol, string);
        }
        System.out.println("Agora introduza o axioma inicial");
        axiom = scan.nextLine();
        System.out.println("Agora o angulo");
        angle = scan.nextFloat();
        System.out.println("Introduza o número de iterações desejado");
        niter = scan.nextInt();
        System.out.println("Introduza o tamanho desejado");
        reflen = scan.nextFloat();
    }

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        forest = new ArrayList<Tree>();
        // startRules();
        System.out.println("Para iniciar clicar no canvas com o botão esquerdo do rato");
        System.out.println("Para introduzir um novo tipo de LSystem clicar na tecla r");
    }

    @Override
    public void draw(PApplet p, float dt) {
        p.background(255);
        float[] bb = plt.getBoundingBox();
        p.rect(bb[0],bb[1],bb[2],bb[3]);
        for(Tree tree : forest){
            tree.grow(dt);
            tree.display(p, plt);
        }
        
        
    }

    @Override
    public void mousePressed(PApplet p) {
        double[] w = plt.getWorldCoord(p.mouseX, p.mouseY);
        PVector pos = new PVector((float)w[0],(float)w[1]);
        Tree tree = new Tree(axiom, rules, pos, reflen,PApplet.radians(angle), niter, 0.5f, 5, p);
        forest.add(tree);
    }

    @Override
    public void mouseReleased(PApplet p) {
        
    }

    @Override
    public void mouseDragged(PApplet p) {
        
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='r'||p.key=='R'){
            updateRules();
        }
    }
    
}
