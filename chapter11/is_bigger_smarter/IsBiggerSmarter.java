// UVa 10131

import java.util.*;

class Elephant {
    public int id;
    public int size;
    public int iq;
    Elephant(int id, int size, int iq) {
        this.id = id;
        this.size = size;
        this.iq = iq;
    }
    @Override
    public String toString() {
        return "[" + this.id + "] Size: " + this.size + "\tIQ: " + this.iq;
    }
}

class SortByWeightInc implements Comparator<Elephant> {
    public int compare(Elephant a, Elephant b) {
        return a.size - b.size;
    }
}

class Main {
    public static void main(String[] args) {
        Main ibs = new Main();
        ibs.run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        ArrayList<Elephant> elephants = new ArrayList<>();
        int count = 0;
        while (in.hasNextInt()) {
            count++;
            int size = in.nextInt();
            int iq = in.nextInt();
            elephants.add(new Elephant(count, size, iq));
        }
        Collections.sort(elephants, new SortByWeightInc());
        // System.out.println("~~~ SORTED ~~~");
        // for (Elephant elephant : elephants) {
        //     System.out.println(elephant);
        // }

        // Find LDS (Longest Decreasing Sequence)
        int numElephants = elephants.size();
        // lds[i] will give the longest decreasing sequence that ends with the elepant.id at index i
        ArrayList<ArrayList<Integer>> lds = new ArrayList<ArrayList<Integer>>(numElephants);
        // The minimum sequence will be the elephant itself
        for (int i = 0; i < numElephants; i++) {
            lds.add(i, new ArrayList<>());
        }

        lds.get(0).add(elephants.get(0).id);

        for (int i = 1; i < numElephants; i++) {
            for (int j = 0; j < i; j++) {
                // Decreasing IQ && Increasing size && the lds is larger than the current (0)
                if (elephants.get(i).iq < elephants.get(j).iq && elephants.get(i).size > elephants.get(j).size && lds.get(i).size() < lds.get(j).size()) {
                    // Set lds[i] = lds[j]
                    lds.get(i).clear();
                    for (int k = 0; k < lds.get(j).size(); k++) {
                        lds.get(i).add(lds.get(j).get(k));
                    }
                }
            }
            // Add elephants[i] to the end of lds[i]
            lds.get(i).add(elephants.get(i).id);
        }

        

        // System.out.println("LDS");
        // for (int i = 0; i < lds.size(); i++) {
        //     System.out.print("LDS[" + i + "]: ");
        //     for (int j = 0; j < lds.get(i).size(); j++) {
        //         System.out.print(lds.get(i).get(j) + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("\n");

        // Print out the longest sequence
        int max = 0;
        int index = -1;
        for (int i = 0; i < lds.size(); i++) {
            if (max < lds.get(i).size()) {
                max = lds.get(i).size();
                index = i;
            }
        }

        System.out.println(max);
        for (int i = 0; i < lds.get(index).size(); i++) {
            System.out.println(lds.get(index).get(i));
        }
    }
}