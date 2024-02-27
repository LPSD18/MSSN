package TP3;

import java.util.Scanner;

import fractals.JuliaSet;
import fractals.MandelbrotSet;
import processing.core.PApplet;
import setup.IProcessingApp;
import tools.SubPlot;

public class TP3D1App implements IProcessingApp{

    private double[] window = {-2,2,-2,2};
    private float[] viewport = {0,0,1f,1f};
    private SubPlot plt;
    private JuliaSet js;
    private int mx0,my0,mx1,my1;

    @Override
    public void setup(PApplet p) {
        plt = new SubPlot(window, viewport, p.width, p.height);
        js = new JuliaSet(300, plt);
        Scanner scan = new Scanner(System.in);
        boolean condition=false;
        while (!condition) {
            float real = -1;
            float imag = -1;
            System.out.println("Introduza o valor real do número complexo");
            while (real==-1) {
                real=scan.nextFloat();
            }
            System.out.println("Introduza o valor imaginário do número complexo");
            imag = scan.nextFloat();
            js.setComplex(real, imag);
            condition=true;

        }
    }

    @Override
    public void draw(PApplet p, float dt) {
        js.display(p, plt);
        displayNewWindow(p);
    }

    private void displayNewWindow(PApplet p){
        p.pushStyle();
        p.noFill();
        p.strokeWeight(3);
        p.stroke(255);
        p.rect(mx0, my0, mx1-mx0, my1-my0);
        p.popStyle();
    }

    @Override
    public void mousePressed(PApplet p) {
        mx0 = mx1 = p.mouseX;
        my0 = my1 = p.mouseY;
    }

    @Override
    public void mouseReleased(PApplet p) {
        double[] xy0 = plt.getWorldCoord(mx0,my0);
        double[] xy1 = plt.getWorldCoord(p.mouseX, p.mouseY);
        double xmin = Math.min(xy0[0],xy1[0]);
        double xmax = Math.max(xy0[0],xy1[0]);
        double ymin = Math.min(xy0[1],xy1[1]);
        double ymax = Math.max(xy0[1],xy1[1]);
        double[] window = {xmin,xmax,ymin,ymax};
        plt = new SubPlot(window, viewport, p.width, p.height);
        mx0 = my0 = mx1 = my1 = 0;
    }

    @Override
    public void mouseDragged(PApplet p) {
        mx1 = p.mouseX;
        my1 = p.mouseY;
    }

    @Override
    public void keyPressed(PApplet p) {
        if(p.key=='r'||p.key=='R'){
            Scanner scan = new Scanner(System.in);
            boolean condition=false;
            while (!condition) {
                float real = -1;
                float imag = -1;
                System.out.println("Introduza o valor real do número complexo");
                while (real==-1) {
                    real=scan.nextFloat();
                }
                System.out.println("Introduza o valor imaginário do número complexo");
                imag = scan.nextFloat();
                js.setComplex(real, imag);
                condition=true;

            }
        }
    }
    
}
