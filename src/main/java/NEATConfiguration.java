public class NEATConfiguration {

    public float C1 = 1.0f;
    public float C2 = 1.0f;
    public float C3 = 0.4f;
    public float DT = 3.0f;
    public float MUTATION_RATE = 0.8f;
    public float ADD_CONNECTION_RATE = 0.05f;
    public float ADD_NODE_RATE = 0.03f;

    private int populationSize;

    public NEATConfiguration(int popSize) {
        this.populationSize = popSize;
    }

    public int getPopulationSize() {
        return populationSize;
    }
}