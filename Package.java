
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Package problem - A package with a capacity that contains many things
 * @author anjana
 */
public class Package {

    private ArrayList<Thing> things;
    private final int capacity;

    public Package(int capacity) {
        this.capacity = capacity;
        things = new ArrayList();
    }
    
    //slight modification over greedy technique. We try to calculate possible solutions after adding 
    private final void add(Thing[] things) {
        int maxCost = 0;
        float maxWeight = capacity;
        for (Thing thing:things) {
            int currentCost = 0;
            float currentWeight = 0;
            int packed = 0;
            ArrayList<Thing> pack = new ArrayList<Thing>();
            
            //add current element as first element,
            if ((currentWeight + thing.weight) <= capacity) {
                pack.add(thing);
                currentCost += thing.cost;
                currentWeight += thing.weight;
                packed++;
            }

            //Add every other elements later
            for (Thing anotherThing:things) {
                if (thing!=anotherThing) {
                    if ((currentWeight + anotherThing.weight) <= capacity) {
                        pack.add(anotherThing);
                        currentCost += anotherThing.cost;
                        currentWeight += anotherThing.weight;
                        packed++;
                    }
                }
            }

            //cost is more or same cost but weight is less
            if (packed > 0 && (currentCost > maxCost || (currentCost == maxCost && currentWeight < maxWeight))) {
                maxCost = currentCost;
                maxWeight = currentWeight;
                this.things = pack;
            }
        }
        Collections.sort(this.things); //finally sort collection by id
    }

    public String toString() {
        Iterator<Thing> iterator = things.iterator();
        StringBuilder builder = new StringBuilder();
        if (iterator.hasNext()) {
            builder.append(iterator.next().indexNumber);
        } else {
            return "-";
        }
        while (iterator.hasNext()) {
            builder.append("," + iterator.next().indexNumber);
        }
        return builder.toString();
    }

    public static void main(String args[]) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
            String line = reader.readLine();

            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                Package pkg = new Package(Integer.valueOf(tokenizer.nextToken()));
                tokenizer.nextToken();//skipping the :
                PriorityQueue<Thing> stock = new PriorityQueue<Thing>();
                while (tokenizer.hasMoreTokens()) {
                    stock.add(new Thing(tokenizer.nextToken()));
                }
                pkg.add(stock.toArray(new Thing[]{}));
                System.out.println(pkg.toString());
                line = reader.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Unable to read from file");
        }

    }
}
