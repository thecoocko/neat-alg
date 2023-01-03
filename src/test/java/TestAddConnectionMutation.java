import java.util.Random;

public class TestAddConnectionMutation {
    public static void main(String[] args) {
        Random r = new Random(1333L);
        Genome  genome = new Genome();
        InnovationCounter counter = new InnovationCounter();

        NodeGene in1 = new NodeGene(NodeGene.TYPE.INPUT,0);
        NodeGene in2 = new NodeGene(NodeGene.TYPE.INPUT,1);
        NodeGene out = new NodeGene(NodeGene.TYPE.OUTPUT,2);


        genome.addNodeGene(in1);
        genome.addNodeGene(in2);
        genome.addNodeGene(out);



        System.out.println("\nNODES BEFORE\n");
        genome.getNodes().forEach((k,v)->System.out.println(("KEY: "+k+"\tID: "+v.getId())));
        System.out.println("\nGENES BEFORE\n");
        genome.getGenes().forEach((k,v)->System.out.println(("KEY: "+k+"\tWEIGHT: "+v.getWeight()+"\tIN: "+v.getInNode()+"\tOUT: "+v.getOutNode())));

        genome.addConnectionMutation(r,counter,10);
        System.out.println("\nNODES AFTER\n");
        genome.getNodes().forEach((k,v)->System.out.println(("KEY: "+k+"\tID: "+v.getId())));
        System.out.println("\nGENES AFTER\n");
        genome.getGenes().forEach((k,v)->System.out.println(("KEY: "+k+"\tWEIGHT: "+v.getWeight()+"\tIN: "+v.getInNode()+"\tOUT: "+v.getOutNode())));
    }

}
