// UVa 10249

import java.util.*;


class Table {
    public int id;
    public int capacity;
    static int count = 0;
    Table(int capacity) {
        count++;
        this.id = count;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "[" + this.id + "]: " + this.capacity;
    }
}

class SortByCapacityDesc implements Comparator<Table> {
    public int compare(Table a, Table b) {
        return b.capacity - a.capacity;
    }
}

public class Main {
    public static void main (String[] args) {
        Main gd = new Main();
        gd.start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        int numTeams, numTables;
        boolean run = true;
        while (run) {
            Table.count = 0;
            numTeams = in.nextInt();
            numTables = in.nextInt();
            if (numTeams != 0 || numTables != 0) {
                int numMembers[] = new int[numTeams];
                Table tables[] = new Table[numTables];
                int maxNum = -1;
                for (int i = 0; i < numTeams; i++) {
                    numMembers[i] = in.nextInt();
                    if (numMembers[i] > maxNum) {
                        maxNum = numMembers[i];
                    }
                }
                ArrayList<Integer[]> teamToTable = new ArrayList<Integer[]>();
                boolean validArrangement = true;
                if (maxNum > numTables) {
                    validArrangement = false;
                } else {
                    for (int i = 0; i < numTables; i++) {
                        int capacity = in.nextInt();
                        tables[i] = new Table(capacity);
                    }
    
                    // for (int i = 0; i < numTables; i++) {
                    //     System.out.println(tables[i]);
                    // }
                    // System.out.println();
    
                    // Sort table array by capactiy
                    Arrays.sort(tables, new SortByCapacityDesc());
    
                    // for (int i = 0; i < numTables; i++) {
                    //     System.out.println(tables[i]);
                    // }
    
                    
                    // System.out.println(teamToTable.size());
                    // For each member of each team, assign them a seat at a different table starting at index 0 and incrementing
                    for (int teamNum = 0; teamNum < numTeams && validArrangement; teamNum++) {
                        teamToTable.add(new Integer[numMembers[teamNum]]);
                        // System.out.println("Team " + (teamNum + 1) + " of " + numTeams + "\n");
                        for (int tableNum = 0; tableNum < numMembers[teamNum] && validArrangement; tableNum++) {
                            if (tables[tableNum].capacity > 0) {
                                // System.out.println("Adding " + tables[tableNum].id);
                                teamToTable.get(teamNum)[tableNum] = tables[tableNum].id;
                                tables[tableNum].capacity--;
                            } else {
                                validArrangement = false;
                            }
                        }
                    }
                }

                

                // System.out.println(teamToTable.size());
                if (validArrangement) {
                    System.out.println("1");
                    for (int i = 0; i < teamToTable.size(); i++) {
                        for (int j = 0; j < numMembers[i]; j++) {
                            System.out.print(teamToTable.get(i)[j] + " ");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("0");
                }

                // Find if there exists a valid seating arrangement of the team members
                // Yes -> print table number for each of the members of team i

            } else {
                run = false;
            }
        }
        
    }
}