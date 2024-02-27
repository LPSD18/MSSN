package ca;

import processing.core.PApplet;
import processing.core.PVector;
import tools.CustomRandomGenerator;
import tools.SubPlot;

public class CellularAutomata {
    
    protected int nrows;
    protected int ncols;
    protected int nStates;
    private int radiusNeigh;
    protected Cell[][] cells;
    private int[] colors;
    public float cellWidth; // pixels
    public float cellHeight; // pixels
    public float xmin, ymin;
    private String surviveRulesString;
    private String bornRulesString;
    private int[] surviveRules;
    private int[] bornRules;
    public SubPlot plt;


    public CellularAutomata(PApplet p,SubPlot plt,int nrows,int ncols, int nStates, int radiusNeigh){
        this.nrows=nrows;
        this.ncols=ncols;
        this.nStates=nStates;
        this.radiusNeigh=radiusNeigh;
        cells = new Cell[nrows][ncols];
        colors = new int[nStates];
        this.plt = plt;
        float[] bb = plt.getBoundingBox();
        xmin = bb[0];
        ymin = bb[1];
        cellWidth = bb[2]/ncols;
        cellHeight = bb[3]/nrows;
        createCells();
        setStateColors(p);
    }

    public CellularAutomata(PApplet p,SubPlot plt,int nrows,int ncols, int nStates, int radiusNeigh,String surviveRulesString,String bornRulesString){
        this.nrows=nrows;
        this.ncols=ncols;
        this.nStates=nStates;
        this.radiusNeigh=radiusNeigh;
        cells = new Cell[nrows][ncols];
        colors = new int[nStates];
        cellWidth = p.width/ncols;
        cellHeight = p.height/nrows;
        this.plt = plt;
        this.surviveRulesString = surviveRulesString;
        this.bornRulesString = bornRulesString;
        createCells();
        setStateColorsGoF(p);

    }

    public int[] getSurviveRules(){//Método para obter as survive rules da string e alterar para número
        String[] help = surviveRulesString.split("");
        surviveRules = new int[help.length];
        for(int i=0;i<surviveRules.length;i++){
            surviveRules[i]=Integer.parseInt(help[i]);
        }
        return surviveRules;
    }
    public int[] getBornRules(){//Método para obter as born rules da string e alterar para número
        String[] help = bornRulesString.split("");
        bornRules = new int[help.length];
        for(int i=0;i<bornRules.length;i++){
            bornRules[i]=Integer.parseInt(help[i]);
        }
        return bornRules;
    }

    public Cell[][] getCells(){
        return cells;
    }

    

    public float getCellWidth(){
        return cellWidth;
    }
    public float getCellHeight(){
        return cellHeight;
    }


    public void setStateColors(PApplet p){
        for(int i=0;i<nStates;i++){
            colors[i]=p.color(p.random(255),p.random(255),p.random(255));
        }
    }
    public void setStateColors(int[] colors){
        this.colors= colors;
    }
    public void setStateColorsGoF(PApplet p){
        colors[0]=p.color(0);
        colors[1]=p.color(p.random(255),p.random(255),p.random(255));
        
    }

    public int[] getStateColors(){
        return colors;
    }

    protected void createCells(){
        for(int i=0;i<nrows;i++){
            for(int j=0;j<ncols;j++){
                cells[i][j]=new Cell(this,i,j);
            }
        }
        setMooreNeighbors();
    }

    public void initCa(){
        for(int i=0;i<nrows;i++){
            for(int j=0;j<ncols;j++){
                cells[i][j].setState((int)((nStates)*Math.random()));
            }
        }
    }

    public void initRandomCustom(double[] pmf){
        CustomRandomGenerator crg = new CustomRandomGenerator(pmf);
        for(int i=0;i<nrows;i++){
            for(int j=0;j<ncols;j++){
                cells[i][j].setState(crg.getRandomClass());
            }
        }
    }

    public Cell world2Cell(double x, double y){
        float[] xy = plt.getPixelCoord(x,y);
        return pixel2Cell(xy[0],xy[1]);
    }
    
    public Cell pixel2Cell(float x, float y){
        int row =(int) ((y-ymin)/cellHeight);
        int col = (int) ((x-xmin)/cellWidth);
        if(row>=nrows) row = nrows-1;
        if(col>=ncols) col = ncols-1;
        if(row<0) row = 0;
        if(col<0) col = 0;
        return cells[row][col];
    }

    
    protected void setMooreNeighbors(){
        int NN = (int) Math.pow(2*radiusNeigh+1,2);

        for(int i=0;i<nrows;i++){
            for(int j=0;j<ncols;j++){
                Cell[] neigh = new Cell[NN];
                int n = 0;
                for(int ii=-radiusNeigh;ii<=radiusNeigh;ii++){
                    int row = (i+ii + nrows)%nrows;
                    for(int jj=-radiusNeigh;jj<=radiusNeigh;jj++){
                        int col = (j+jj+ncols)%ncols;
                        neigh[n++] = cells[row][col];
                    }
                }
                cells[i][j].setNeighbors(neigh);
            }
        }
    }

    public PVector getCenterCell(int row, int col){
        float x = (col+0.5f) * cellWidth;
        float y = (row+0.5f) * cellHeight;
        double[]w = plt.getWorldCoord(x, y);
        return new PVector((float)w[0],(float)w[1]);
    }

    public void display(PApplet p){
         for(int i=0;i<nrows;i++){
            for(int j=0;j<ncols;j++){
                cells[i][j].display(p);
            }
        }
    }



}
