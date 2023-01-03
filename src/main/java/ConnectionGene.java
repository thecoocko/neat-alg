public class ConnectionGene {
    private float weight;
    private int inNode;
    private int outNode;
    private boolean expressed;
    private int innovation;

    public ConnectionGene(float weight, int inNode, int outNode, boolean expressed, int innovation) {
        this.weight = weight;
        this.inNode = inNode;
        this.outNode = outNode;
        this.expressed = expressed;
        this.innovation = innovation;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setInNode(int inNode) {
        this.inNode = inNode;
    }

    public void setOutNode(int outNode) {
        this.outNode = outNode;
    }

    public void setExpressed(boolean expressed) {
        this.expressed = expressed;
    }

    public void setInnovation(int innovation) {
        this.innovation = innovation;
    }

    public float getWeight() {
        return weight;
    }

    public int getInNode() {
        return inNode;
    }

    public int getOutNode() {
        return outNode;
    }

    public boolean isExpressed() {
        return expressed;
    }

    public int getInnovation() {
        return innovation;
    }
    public  void disable(){
        expressed = false;
    }
    public ConnectionGene copy(){
        return new ConnectionGene(weight,inNode,outNode,expressed,innovation);
    }
}
