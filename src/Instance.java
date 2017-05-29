import java.util.ArrayList;

public class Instance {

    ArrayList<Integer> features = new ArrayList<Integer>();

    public Instance(ArrayList<Integer> features) {
        this.features = features;
    }

    public Integer get(int value) {
        return features.get(value);
    }
}