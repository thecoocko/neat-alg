//import java.text.DecimalFormat;
//
//public class TestXORNN {
//
//    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat();
//        df.setMaximumFractionDigits(9);
//
//        float[][] input = {		{	1f,		1f,		0.5f},
//                {	0f,		0f,		0.5f},
//                {	1f,		0f,		0.5f},
//                {	0f,		1f,		0.5f}};
//
//        float[] correctResult = {0f, 0f, 1f, 1f};
//
//        InnovationCounter nodeInn = new InnovationCounter();
//        InnovationCounter connInn = new InnovationCounter();
//
//        Genome genome = new Genome();
//        genome.addNodeGene(new NodeGene(NodeGene.TYPE.INPUT.INPUT, nodeInn.getCounter()));				// node id is 0
//        genome.addNodeGene(new NodeGene(NodeGene.TYPE.INPUT, nodeInn.getCounter()));				// node id is 1
//        genome.addNodeGene(new NodeGene(NodeGene.TYPE.INPUT, nodeInn.getCounter()));				// node id is 2,  bias
//        genome.addNodeGene(new NodeGene(NodeGene.TYPE.HIDDEN, nodeInn.getCounter()));				// node id is 3
//        genome.addNodeGene(new NodeGene(NodeGene.TYPE.OUTPUT, nodeInn.getCounter()));				// node id is 4
//
//        genome.addConnectionGene(new ConnectionGene(0,3,1f,true, connInn.getInnovation()));	// conn id is 0
//        genome.addConnectionGene(new ConnectionGene(1,3,1f,true, connInn.getInnovation()));	// conn id is 1
//        genome.addConnectionGene(new ConnectionGene(2,3,1f,true, connInn.getInnovation()));	// conn id is 1
//        genome.addConnectionGene(new ConnectionGene(0,4,1f,true, connInn.getInnovation()));	// conn id is 0
//        genome.addConnectionGene(new ConnectionGene(1,4,1f,true, connInn.getInnovation()));	// conn id is 1
//        genome.addConnectionGene(new ConnectionGene(2,4,1f,true, connInn.getInnovation()));	// conn id is 1
//        genome.addConnectionGene(new ConnectionGene(3,4,1f,true, connInn.getInnovation()));	// conn id is 1
//
//        NEATConfiguration conf = new NEATConfiguration(300);
//        conf.DT=10f;
//        conf.ADD_NODE_RATE = 0.02f;
//        conf.ADD_CONNECTION_RATE = 0.02f;

//        Evaluator eval = new Evaluator(conf, genome, nodeInn, connInn) {
//            @Override
//            protected float evaluateGenome(Genome genome) {
//                float totalDistance = 0f;
//                NeuralNetwork net = new NeuralNetwork(genome);
//
//                //System.out.println("Testing neural network...");
//
//                for (int i = 0; i < input.length; i++) {
//                    float[] inputs = {input[i][0], input[i][1], input[i][2]};
//                    //System.out.print("Giving input: "+Arrays.toString(inputs));
//                    float[] outputs = null;
//                    try {
//                        outputs = net.calculate(inputs);
//                    } catch (Exception e) {
//                        System.out.println("Priting failed network to output/xor_1/fail.png");
////                        LegacyGenomePrinter.printGenome(genome, "output/xor_1/fail.png");
////                        LegacyGenomePrinter.printGenomeText(genome);
////                        e.printStackTrace();
//                        System.exit(1);
//                    }
//
//                    if (outputs == null) {
//                        System.out.println("Priting failed network to output/xor_1/fail.png");
////                        LegacyGenomePrinter.printGenome(genome, "output/xor_1/fail.png");
////                        LegacyGenomePrinter.printGenomeText(genome);
//                        System.exit(1);
//                    }
//                    //System.out.print(" Correct: "+correctResult[i]+"\n");
//                    float guess = outputs[0];
//                    float distance = Math.abs(correctResult[i]-guess);
//                    totalDistance += distance;
//                }
//                return (4f-totalDistance);
//            }
//        };
//
//        for (int i = 0; i < 10000; i++) {
//            eval.evaluate();
//            System.out.print("Generation: "+i);
//            System.out.print("\tHighest fitness: "+df.format(eval.getHighestFitness()));
//            System.out.print("\tAmount of species: "+eval.getSpeciesAmount());
//            System.out.print("\tConnections in best performer: "+eval.getFittestGenome().getConnectionGenes().values().size());
//            System.out.print("\tGuesses: ");
//
//            NeuralNetwork net = new NeuralNetwork(eval.getFittestGenome());
//
//            for (int k = 0; k < input.length; k++) {
//                float[] inputs = {input[k][0], input[k][1], input[k][2]};
//                float[] outputs = null;
//                try {
//                    outputs = net.calculate(inputs);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.exit(1);
//                }
//
//                float guess = outputs[0];
//                System.out.print(df.format(guess)+", ");
//            }
//
//            System.out.print("\n");
////			LegacyGenomePrinter.printGenome(eval.getFittestGenome(), "output/xor_1/"+i+".png");
//        }
//    }
//
//}
