import java.util.Random;

public class TestNodeMutation {
    public static void main(String[] args) {
        Random r = new Random();
        Genome  genome = new Genome();
        InnovationCounter nodeInnovator = new InnovationCounter();
        InnovationCounter connInnovator = new InnovationCounter();

        NodeGene in1 = new NodeGene(NodeGene.TYPE.INPUT,nodeInnovator.getCounter());
        NodeGene in2 = new NodeGene(NodeGene.TYPE.INPUT,nodeInnovator.getCounter());
        NodeGene out = new NodeGene(NodeGene.TYPE.OUTPUT,nodeInnovator.getCounter());

        genome.addNodeGene(in1);
        genome.addNodeGene(in2);
        genome.addNodeGene(out);

        ConnectionGene c1 = new ConnectionGene(0.5f,0,2,true, connInnovator.getCounter());
        ConnectionGene c2 = new ConnectionGene(0.75f,1,2,true, connInnovator.getCounter());



        genome.addConnectionGene(c1);
        genome.addConnectionGene(c2);

        genome.addNodeMutation(r,connInnovator,nodeInnovator);
        System.out.println("\nNODES\n");
        genome.getNodes().forEach((k,v)->System.out.println(("KEY: "+k+"\tID: "+v.getId())));
        System.out.println("\nGENES\n");
        genome.getGenes().forEach((k,v)->System.out.println(("KEY: "+k+"\tWEIGHT: "+v.getWeight()+"\tIN: "+v.getInNode()+"\tOUT: "+v.getOutNode())));


    }
}

