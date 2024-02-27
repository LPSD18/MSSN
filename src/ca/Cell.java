package ca;

import processing.core.PApplet;

public class Cell {
    
    public int row,col;
    protected int state;
    private Cell[] neighbors;
    public CellularAutomata ca;
    private boolean alive; 
    private int stateChange;

    public Cell(CellularAutomata ca,int row,int col){
        this.ca=ca;
        this.row=row;
        this.col=col;
        this.state = 0;
        this.neighbors = null;
        this.alive=false;
    }

    

    public void setNeighbors(Cell[] neigh){
        
        this.neighbors = neigh;
    }
    public Cell[] getNeighbors(){
        return this.neighbors;
    }

    public boolean getAlive(){
        return this.alive;
    }

    public void Majority(){//Método da regra da maioria
        int[] bigger = new int[ca.getStateColors().length]; //array com o tamanho do número de estados existentes
        stateChange=0; // 
        for(int i=0;i<bigger.length;i++){//introduzir valores iniciais ao array
            bigger[i]=0;
        }
        for(int i=0;i<neighbors.length;i++){//get de número de vizinhos com cada estado
            bigger[neighbors[i].getState()]++;
        }
        for(int i=0;i<bigger.length;i++){//Vai ver que estado é o que tem mais células existentes
            if(bigger[stateChange]<bigger[i])stateChange=i;
        } 
    }
    public int getStateChange(){
        return this.stateChange;
    }

    public boolean GameOfLife(PApplet p,int[]survive,int[]born){
        int help1=0;
        if(this.getState()==1){//Célula central viva - ver se existe o número correto de células vizinhas vivas para sobreviver
            for(int i=0;i<neighbors.length;i++){//buscar número de vizinhos vivos
                if(neighbors[i]!=this){
                    if(neighbors[i].getState()==(1))help1++;// Ver se o vizinho atual está vivo ou não
                }
            }
            for(int i=0;i<survive.length;i++){
                if(survive[i]==help1){// se o número de vizinhos vivos for uma das survive rules atribui true à instancia que se retorna
                    alive=true;
                    break;
                }
                else{
                    alive=false;
                }
            }
        }
        else if(this.getState()==0){//Célula central morta - ver se existe o número correto de vizinhos vivos para nascer
            for(int i=0;i<neighbors.length;i++){//buscar número de vizinhos vivos
                if(neighbors[i]!=this){
                    if(neighbors[i].getState()==1)help1++;
                }
            }
            for(int i=0;i<born.length;i++){
                if(born[i]==help1){// se o número de vizinhos vivos for uma das survive rules atribui true à instancia que se retorna
                    alive=true;
                    break;
                }
                else{
                    alive=false;
                }
            }
        }
        return alive;//return da instancia que indica se a célula deve estar viva ou não

    }

    public void setState(int state){
        this.state=state;
    }

    public int getState(){
        return this.state;
    }

    public void display(PApplet p){
        p.pushStyle();
        p.noStroke(); //não ter as linhas à volta
        p.fill(ca.getStateColors()[state]);
        p.rect(ca.xmin+col*ca.getCellWidth(),ca.ymin+row*ca.getCellHeight(),ca.getCellWidth(),ca.getCellHeight());
        p.popStyle();
    }

}
