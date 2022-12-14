import java.util.Random;



public class TestCrossover {

    public static Genome setUpFather() {
        Genome father = new Genome();
        for (int i = 0; i < 3; i++) {
            NodeGene node = new NodeGene(NodeGene.TYPE.INPUT,i+1);
            father.addNodeGene(node);
        }
        father.addNodeGene(new NodeGene(NodeGene.TYPE.OUTPUT,4));
        father.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN,5));

        father.addConnectionGene(new ConnectionGene(1f,1,4,true,1));
        father.addConnectionGene(new ConnectionGene(1f,2,4,false,2));
        father.addConnectionGene(new ConnectionGene(1f,3,4,true,3));
        father.addConnectionGene(new ConnectionGene(1f,2,4,true,4));
        father.addConnectionGene(new ConnectionGene(1f,5,4,true,5));
        father.addConnectionGene(new ConnectionGene(1f,1,4,true,6));

        Genome mother = new Genome();
        for (int i = 0; i < 3; i++) {
            NodeGene node = new NodeGene(NodeGene.TYPE.INPUT,i+1);
            mother.addNodeGene(node);
        }

        mother.addNodeGene(new NodeGene(NodeGene.TYPE.OUTPUT,4));
        mother.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN,5));
        mother.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN,6));

        mother.addConnectionGene(new ConnectionGene(1f,1,4,true,1));
        mother.addConnectionGene(new ConnectionGene(1f,2,4,false,2));
        mother.addConnectionGene(new ConnectionGene(1f,3,4,true,3));
        mother.addConnectionGene(new ConnectionGene(1f,2,4,true,4));
        mother.addConnectionGene(new ConnectionGene(1f,5,4,true,5));
        mother.addConnectionGene(new ConnectionGene(1f,6,4,true,6));
        mother.addConnectionGene(new ConnectionGene(1f,4,4,true,7));
        mother.addConnectionGene(new ConnectionGene(1f,5,4,true,9));
        mother.addConnectionGene(new ConnectionGene(1f,6,4,true,10));

        return father;
    }

    public static Genome setUpMother() {

        Genome mother = new Genome();
        for (int i = 0; i < 3; i++) {
            NodeGene node = new NodeGene(NodeGene.TYPE.INPUT,i+1);
            mother.addNodeGene(node);
        }

        mother.addNodeGene(new NodeGene(NodeGene.TYPE.OUTPUT,4));
        mother.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN,5));
        mother.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN,6));

        mother.addConnectionGene(new ConnectionGene(1f,1,4,true,1));
        mother.addConnectionGene(new ConnectionGene(1f,2,4,false,2));
        mother.addConnectionGene(new ConnectionGene(1f,3,4,true,3));
        mother.addConnectionGene(new ConnectionGene(1f,2,4,true,4));
        mother.addConnectionGene(new ConnectionGene(1f,5,4,true,5));
        mother.addConnectionGene(new ConnectionGene(1f,6,4,true,6));
        mother.addConnectionGene(new ConnectionGene(1f,4,4,true,7));
        mother.addConnectionGene(new ConnectionGene(1f,5,4,true,9));
        mother.addConnectionGene(new ConnectionGene(1f,6,4,true,10));

        return mother;
    }

    public static void main(String[] args) {
        //?????? ???? ???????????? ???????????? ?????????? ???????????? ???? ?? ???????? ???????????????????????? ???????????? ???? ??????????
       Genome child = Genome.crossover(setUpMother(),setUpFather(),new Random());
       child.getGenes().forEach((k,v)->System.out.println("KEY: "+k+"\tVALUE: "+v.getWeight()+"\tIN NODE: "+v.getInNode()+"\tOUT NODE: "+v.getOutNode()));


    }
}
