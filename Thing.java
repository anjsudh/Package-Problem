/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Any thing that can be addded to the pckage
 * @author anjana-2492
 */
public class Thing implements Comparable<Thing>{

    public final int indexNumber;
    public final float weight;
    public final int cost;

    public Thing(int indexNumber, float weight, int cost) {
        this.indexNumber = indexNumber;
        this.weight = weight;
        this.cost = cost;
    }
    
    public Thing(String str) {
        String[] inputs = str.substring(1, str.length()-1).split(",");
        this.indexNumber = Integer.valueOf(inputs[0]);
        this.weight = Float.valueOf(inputs[1]);
        this.cost = Integer.valueOf(inputs[2].substring(1));
    }

    /**
     * Comparator logic will sort in reverse oder
     * @param anotherThing
     * @return 
     */
    @Override
    public int compareTo(Thing anotherThing) {
        float diffValue = (((float)anotherThing.cost/anotherThing.weight) - ((float)this.cost/this.weight));
        return  new Double((diffValue>=0)?Math.ceil(diffValue):Math.floor(diffValue)).intValue();
    }
    
    public String toString(){
        return ("("+indexNumber+","+(cost/weight)+","+")");
    }
}
