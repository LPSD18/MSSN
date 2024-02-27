package tools;

public class Histogram {
    
    int[] hist;// array de inteiros com contadores que vai contar quantas células estão em cada estado
    int nbins;//o número de contadores é o numero de bins - dimensão do hist

    public Histogram(int[] data, int nbins){
        this.nbins = nbins;
        hist = new int[nbins];
        for(int i=0;i<data.length;i++){
            hist[data[i]]++;
        }
    }

    public int[] getDistribution(){
        return hist;
    }

    public int getMode(int preference){
        int maxVal = 0;
        int mode = 0;
        for(int i=0;i<nbins;i++){
            if(hist[i]>maxVal){
                maxVal=hist[i];
                mode=i;
            }
        } 
        if(hist[preference]==hist[mode]){
            return preference;//se for empate return da preferencia
        }
        return mode;
    }

    public void display(){
        for(int i=0;i<nbins;i++){
            System.out.println(hist[i]);
        }
        System.out.println();
    }

}
