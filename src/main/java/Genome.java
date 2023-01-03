import java.util.*;

public class Genome {
    Map<Integer,ConnectionGene> connections;
    Map<Integer,NodeGene> nodeGenes;

    int innovationNumber;
    //mutation

    public Genome(){
        connections = new HashMap<Integer,ConnectionGene>();
        nodeGenes = new HashMap<Integer,NodeGene>();
        this.innovationNumber = innovationNumber;
    }

    public Map<Integer,ConnectionGene> getGenes(){
        return connections;
    }

    public  Map<Integer,NodeGene> getNodes(){
        return nodeGenes;
    }

    public void addNodeGene(NodeGene gene){
        nodeGenes.put(gene.getId(),gene);
    }

    public void addConnectionGene(ConnectionGene gene){
        connections.put(gene.getInnovation(),gene);
    }

    public void addConnectionMutation(Random r){
        float weight = r.nextFloat()*2f-1f;
        NodeGene node1 = nodeGenes.get(r.nextInt(nodeGenes.size()));
        NodeGene node2 = nodeGenes.get(r.nextInt(nodeGenes.size()));
        boolean reversed = false;//якщо тут у нас реверсе - тру, то нам потрібно з'єднати ноду 1 та ноду 2
        if(node1.getType()== NodeGene.TYPE.HIDDEN && node2.getType()==NodeGene.TYPE.OUTPUT){
            reversed = true;
        } else if (node1.getType()== NodeGene.TYPE.OUTPUT && node2.getType()==NodeGene.TYPE.HIDDEN) {
            reversed = true;
        }else if(node1.getType()== NodeGene.TYPE.OUTPUT && node2.getType()==NodeGene.TYPE.INPUT){
            reversed = true;
        }
        boolean isConnection = false;
        for(ConnectionGene c : connections.values()){//перевіряємо наявність зв'язку
            if(c.getInNode() == node1.getId() && c.getOutNode() ==node2.getId()) {//existing connection
                isConnection = true;
                break;
            } else if (c.getInNode() == node2.getId() && c.getOutNode() ==node1.getId()) {
                isConnection = true;
                break;
            }
        }
        if(isConnection){
            return;
        }
         ConnectionGene newConnection = new ConnectionGene(weight,reversed? node2.getId() : node1.getId(),reversed? node1.getId() : node2.getId(),true,2);
        connections.put(newConnection.getInnovation(),newConnection);
    }
    public  void  mutation (Random r){

    }

    public void addNodeMutation(Random r){//
        InnovationCounter counter = new InnovationCounter();
        ConnectionGene c = connections.get(r.nextInt(connections.size()));

        NodeGene inNode = nodeGenes.get(c.getInNode());
        NodeGene outNode = nodeGenes.get(c.getOutNode());
        c.disable();
        NodeGene newNode = new NodeGene(NodeGene.TYPE.HIDDEN, nodeGenes.size());
        ConnectionGene inNew = new ConnectionGene(1f,inNode.getId(),newNode.getId(),true,counter.getCounter());
        ConnectionGene outNew = new ConnectionGene(c.getWeight(),newNode.getId(),outNode.getId(),true,counter.getCounter());

        nodeGenes.put(newNode.getId(),newNode);
        connections.put(inNew.getInnovation(),inNew);
        connections.put(outNew.getInnovation(),outNew);
    }

    /**father MORE FIT PARENT THAN MOTHER ALWAYS!!!*/
    public static Genome crossover(Genome father, Genome mother, Random r){
        Genome child = new Genome();
        for(NodeGene node : father.getNodes().values()){
            child.addNodeGene(node.copy());
        }

        for(ConnectionGene fatherGenes : father.getGenes().values()){
            if(mother.getGenes().containsKey(fatherGenes.getInnovation())){//matching gene
                ConnectionGene childGene = r.nextBoolean() ? fatherGenes.copy(): mother.getGenes().get(fatherGenes.getInnovation()).copy();
                child.addConnectionGene(childGene);
            }else{//disjoint
               ConnectionGene childConGene  =  fatherGenes.copy();
               child.addConnectionGene(childConGene);
            }
        }

        return child;
    }

}
