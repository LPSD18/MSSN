package fractals;

import processing.core.PApplet;
import tools.Complex;
import tools.SubPlot;

public class JuliaSet {
    
    private int niter;
    private int x0, y0;
    private int dimx, dimy;
    private float real,imag;

    public JuliaSet(int niter, SubPlot plt){
        this.niter = niter;
        float[] bb = plt.getBoundingBox();
        x0 = (int) bb[0];
        y0 = (int) bb[1];
        dimx = (int) bb[2];
        dimy = (int) bb[3];
        real=0;
        imag=0;
    }

    public void setComplex(float real,float imag){
        this.real=real;
        this.imag=imag;
    }

    public void display(PApplet p, SubPlot plt){
        p.loadPixels();
        for(int xx = x0; xx<x0 + dimx;xx++){
            for(int yy = y0; yy<y0+dimy;yy++){
                double[] cc = plt.getWorldCoord(xx, yy);
                Complex c = new Complex(cc);
                Complex x = new Complex(real,imag);
                
                int i = 0;
                for(i=0;i<niter;i++){
                    c.mult(c).add(x);
                    if(c.norm()>2){
                        break;
                    }
                }
                p.pixels[yy*p.width+xx] = (i == niter) ? p.color(0,0,255) : p.color((i % 16)*16);
            }
        }
        p.updatePixels();
    }


}
