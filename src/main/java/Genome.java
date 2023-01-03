import java.util.*;

public class Genome {
    private final float PROBABILITY_PERTURBING = 0.9f;
    Map<Integer,ConnectionGene> connections;
    Map<Integer,NodeGene> nodeGenes;

    int innovationNumber;
    //mutation

    public Genome(){
        connections = new HashMap<>();
        nodeGenes = new HashMap<>();
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

    public void addConnectionMutation(Random r, InnovationCounter counter, int maxAttempts){
        int tries = 0;
        boolean success = false;
        while (tries < maxAttempts && !success) {
            tries++;

            Integer[] nodeInnovationNumbers = new Integer[nodeGenes.keySet().size()];
            nodeGenes.keySet().toArray(nodeInnovationNumbers);
            Integer keyNode1 = nodeInnovationNumbers[r.nextInt(nodeInnovationNumbers.length)];
            Integer keyNode2 = nodeInnovationNumbers[r.nextInt(nodeInnovationNumbers.length)];

            NodeGene node1 = nodeGenes.get(keyNode1);
            NodeGene node2 = nodeGenes.get(keyNode2);
            float weight = r.nextFloat()*2f-1f;

            boolean reversed = false;
            if (node1.getType() == NodeGene.TYPE.HIDDEN && node2.getType() == NodeGene.TYPE.INPUT) {
                reversed = true;
            } else if (node1.getType() == NodeGene.TYPE.OUTPUT && node2.getType() == NodeGene.TYPE.HIDDEN) {
                reversed = true;
            } else if (node1.getType() == NodeGene.TYPE.OUTPUT && node2.getType() == NodeGene.TYPE.INPUT) {
                reversed = true;
            }
            if (reversed) {
                NodeGene tmp = node1;
                node1 = node2;
                node2 = tmp;
            }

            boolean connectionImpossible = false;
            if (node1.getType() == NodeGene.TYPE.INPUT && node2.getType() == NodeGene.TYPE.INPUT) {
                connectionImpossible = true;
            } else if (node1.getType() == NodeGene.TYPE.OUTPUT && node2.getType() == NodeGene.TYPE.OUTPUT) {
                connectionImpossible = true;
            } else if (node1 == node2) {
                connectionImpossible = true;
            }

            /* check for circular structures */
            List<Integer> needsChecking = new LinkedList<>(); 	// list of nodes that should have their connections checked
            List<Integer> nodeIDs = new LinkedList<>(); 			// list of nodes that requires output from node2
            for (Integer connID : connections.keySet()) {
                ConnectionGene gene = connections.get(connID);
                if (gene.getInNode() == node2.getId()) { // connection comes from node2
                    nodeIDs.add(gene.getOutNode());
                    needsChecking.add(gene.getOutNode());
                }
            }
            while (!needsChecking.isEmpty()) {
                int nodeID = needsChecking.get(0);
                for (Integer connID : connections.keySet()) {
                    ConnectionGene gene = connections.get(connID);
                    if (gene.getInNode() == nodeID) { // connection comes from the needsChecking node
                        nodeIDs.add(gene.getOutNode());
                        needsChecking.add(gene.getOutNode());
                    }
                }
                needsChecking.remove(0);
            }
            for (Integer i : nodeIDs) { // loop through dependent nodes
                if (i == node1.getId()) { // if we make it here, then node1 calculation is dependent on node2 calculation, creating a deadlock
                    connectionImpossible = true;
                }
            }

            boolean connectionExists = false;
            for (ConnectionGene con : connections.values()) {
                if (con.getInNode() == node1.getId() && con.getOutNode() == node2.getId()) { // existing connection
                    connectionExists = true;
                    break;
                } else if (con.getInNode() == node2.getId() && con.getOutNode() == node1.getId()) { // existing reverse connection
                    connectionExists = true;
                    break;
                }
            }

            if (connectionExists || connectionImpossible) {
                continue;
            }

            ConnectionGene newCon = new ConnectionGene(weight,node1.getId(), node2.getId(), true, counter.getCounter());
            connections.put(newCon.getInnovation(), newCon);
            success = true;
        }
    }
    public void mutation(Random r) {
        for(ConnectionGene con : connections.values()) {
            if (r.nextFloat() < PROBABILITY_PERTURBING) { 			// uniformly perturbing weights
                con.setWeight(con.getWeight()*(r.nextFloat()*4f-2f)); // scale weight by between -2 and 2
            } else { 												// assigning new weight
                con.setWeight(r.nextFloat()*4f-2f);	// assign new weight between -2 and 2
            }
        }
    }

    public void addNodeMutation(Random r,InnovationCounter connectionInnovation, InnovationCounter nodeInnovation){//

        ConnectionGene c = connections.get(r.nextInt(connections.size()));

        NodeGene inNode = nodeGenes.get(c.getInNode());
        NodeGene outNode = nodeGenes.get(c.getOutNode());
        c.disable();
        NodeGene newNode = new NodeGene(NodeGene.TYPE.HIDDEN, nodeInnovation.getCounter());
        ConnectionGene inNew = new ConnectionGene(1f,inNode.getId(),newNode.getId(),true,connectionInnovation.getCounter());
        ConnectionGene outNew = new ConnectionGene(c.getWeight(),newNode.getId(),outNode.getId(),true,connectionInnovation.getCounter());

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
