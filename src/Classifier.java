import java.util.ArrayList;

public class Classifier {

    ArrayList<Instance> instances;
    final int TOTAL_NUM_INSTANCES = 200;

    public Classifier(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    public boolean classify(Instance testInstance) {
        double trueProb = 1.0;
        double falseProb = 1.0;

        ArrayList<Instance> trueList = new ArrayList<Instance>();
        ArrayList<Instance> falseList = new ArrayList<Instance>();

        for(Instance trainingInstance : instances){
            if(trainingInstance.get(12) == 1){
                trueList.add(trainingInstance);
            }
            else {
                falseList.add(trainingInstance);
            }
        }

        trueProb = (double) trueList.size() / TOTAL_NUM_INSTANCES;
        falseProb = 1-trueProb;

        ArrayList <Double> totalProbs = calculateProbs(instances);
        ArrayList <Double> trueProbs = calculateProbs(trueList);
        ArrayList <Double> falseProbs = calculateProbs(falseList);

        double naiveBayesDenominator = 1.0;

        for (int i = 0; i < testInstance.features.size(); i++){

            if(testInstance.get(i) == 1) {

                System.out.println(testInstance.get(i)+"test instance feature in true if");
                trueProb = trueProb * trueProbs.get(i);
                falseProb = falseProb * falseProbs.get(i);
                naiveBayesDenominator = naiveBayesDenominator * totalProbs.get(i);
            }
            else {

                System.out.println(testInstance.get(i)+"test instance feature in false if");
                trueProb = trueProb * (1.0 - trueProbs.get(i));
                falseProb = falseProb * (1.0 - falseProbs.get(i));
                naiveBayesDenominator = naiveBayesDenominator * (1.0 - totalProbs.get(i));
            }

        }
        System.out.println(trueProb + "true probability");
        System.out.println(trueProb + "false probability");


        trueProb = trueProb/naiveBayesDenominator;
        falseProb = falseProb/naiveBayesDenominator;

        System.out.println(trueProb + "true probability/denom");
        System.out.println(trueProb + "false probability/denom");

        return trueProb > falseProb;
    }

    private ArrayList<Double> calculateProbs(ArrayList<Instance> listOfInstances){

        ArrayList<Double> tempProb = new ArrayList<Double>();
        int[] countOfFeature = new int[12];

        for(Instance i : listOfInstances){
            for(int j = 0 ;j < 12; j++ ){
                countOfFeature[j] += i.get(j);
            }
        }

        for(int i = 0; i < countOfFeature.length; i++){
            double value = (double)countOfFeature[i] / (double) listOfInstances.size();
            tempProb.add(value);
        }

        return tempProb;
    }
}