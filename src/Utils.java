import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public ArrayList<Instance> readData(File file, boolean training) throws FileNotFoundException {
        ArrayList<Instance> list = new ArrayList<Instance>();
        if (training){
            try {
                
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    ArrayList<Integer> currentLine = new ArrayList<Integer>();
                    for (int i = 0; i < 13; i++) {
                        currentLine.add(sc.nextInt());
                    }
                    sc.nextLine();
                    list.add(new Instance(currentLine));
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Training Data LOADED\n");
        }
        else{
            try {
                Scanner sc = new Scanner(file);

                while(sc.hasNextLine()) {
                    ArrayList<Integer> currLine = new ArrayList<Integer>();
                    for(int i=0; i < 12; i++) {
                        currLine.add(sc.nextInt());
                    }
                    sc.nextLine();
                    list.add(new Instance(currLine));
                }
                sc.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Testing Data LOADED\n");

        }
        return list;
    }

    public static void makeOutputFile(File file, ArrayList<Boolean> classified) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("sampleoutput.txt", "UTF-8");
        Scanner sc = new Scanner(file);
        int count = 0;
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
                    if(classified.get(count)==true){
                        line = line + ("     "+1);
                    }
                    else{
                        line = line + ("     "+0);
                    }

            writer.println(line);
            count++;
        }
        writer.flush();
        writer.close();
        sc.close();
        System.out.println("Output file created");
    }
}