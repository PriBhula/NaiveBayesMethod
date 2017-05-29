import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Algorithm {
    public static ArrayList<Instance> testingSet;
    public static ArrayList<Instance> trainingSet;

    public static ArrayList<Boolean> classified = new ArrayList<Boolean>();

    public static Utils utils = new Utils();

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        File trainingFile = new File(args[0]);
        File testingFile = new File(args[1]);
        trainingSet = utils.readData(trainingFile, true);
        testingSet = utils.readData(testingFile, false);
        Classifier classifier = new Classifier(trainingSet);
        int count = 1;

        for(Instance i : testingSet) {
            System.out.println("Classifying email " +count+ "...");
            boolean spam = classifier.classify(i);
            classified.add(spam);
            count++;
            System.out.println("----------");
        }
        utils.makeOutputFile(testingFile, classified);
    }
}