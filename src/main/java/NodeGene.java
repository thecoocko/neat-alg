public class NodeGene {
    enum TYPE{
        INPUT,
        HIDDEN,
        OUTPUT
    }
    private TYPE type;
    private int id;

    public NodeGene(TYPE type, int id) {
        this.type = type;
        this.id = id;
    }

    public TYPE getType() {
        return type;
    }

    public int getId() {
        return id;
    }
    /*!!!!!Copy of GENE so we don't reused the same object!!!!!!!!*/
    public NodeGene copy(){
        return new NodeGene(type,id);
    }
}
