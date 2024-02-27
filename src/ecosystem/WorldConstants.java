package ecosystem;

public class WorldConstants {

    //World
    public final static double[] WINDOW = {-10,10,-10,10};

    //Terrain
    public final static int NROWS = 60;
    public final static int NCOLS = 80;
    public static enum PatchType {
        EMPTY, OBSTACLE, FERTILE, FOOD
    }
    public final static double[] PATCH_TYPE_PROB = {0.2f,0.2f,0.1f,0.5f};
    public final static int NSTATES = PatchType.values().length;
    public static int[][] TERRAIN_COLORS = {{227,203,165},{255,37,0},{234,208,168},{124,252,0}};
    public final static float[] REGENERATION_TIME = {5.f,10.f}; //Seconds

    //Prey population
    public static int INI_PREY_POPULATION = 60;
    public static final int[] PREY_COLOR = {80,100,220};
    public static final float PREY_MASS = 1f;
    public static final float PREY_SIZE = .2f;
    public static final float INI_PREY_ENERGY = 10f;
    public static final float ENERGY_FROM_PLANT = 4f;
    public static final float PREY_ENERGY_TO_REPRODUCE = 25f;
    
    //Predator population
    public static int INI_PREDATOR_POP = 5;
    public static final int[] PREDATOR_COLOR = {0,0,0};
    public static final float PREDATOR_MASS = 1f;
    public static final float PREDATOR_SIZE = 0.4f;
    public static final float INI_PREDATOR_ENERGY = 20f;
    public static final float ENERGY_FROM_PREY = 10f;
    public static final float PREDATOR_ENERGY_TO_REPRODUCE = 40f;

}
