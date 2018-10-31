// UVa 10158

import java.util.*;

class Country {
    public boolean friends[];
    public boolean enemies[];
    public int size;
    Country(int size) {
        this.size = size;
        this.friends = new boolean[size];
        this.enemies = new boolean[size];
    }
}

public class Main {
    public static void main(String[] args) {
        Main war = new Main();
        war.start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        int numPeople = in.nextInt();
        int opCode = in.nextInt();
        int personA = in.nextInt();
        int personB = in.nextInt();
        
        Country countries[] = new Country[numPeople];
        for (int i = 0; i < numPeople; i++) {
            countries[i] = new Country(numPeople);
            // Satisfy everyone is a friend of himself property
            countries[i].friends[i] = true;
        }
        while (opCode != 0 || personA != 0 || personB != 0) {
            // System.out.println("\t\t[Read in values:" + opCode + " | " + personA + " | " + personB + "]");
            if (opCode == 1) {
                // Set friends
                boolean res = setFriends(personA, personB, countries);
                if (!res) {
                    System.out.println("-1");
                }
            } else if (opCode == 2) {
                // Set enemies
                boolean res = setEnemies(personA, personB, countries);
                if (!res) {
                    System.out.println("-1");
                }
            } else if (opCode == 3) {
                // Are friends?
                boolean res = areFriends(personA, personB, countries);
                if (res) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            } else if (opCode == 4) {
                // Are enemies?
                boolean res = areEnemies(personA, personB, countries);
                if (res) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            }

            // for (int i = 0; i < numPeople; i++) {
            //     System.out.print("Person " + i + " is friends with:\n\t");
            //     for (int j = 0; j < numPeople; j++) {
            //         if (countries[i].friends[j]) {
            //             System.out.print(j + " ");
            //         }
            //     }
            //     System.out.println();

            //     System.out.print("Person " + i + " is enemies with:\n\t");
            //     for (int j = 0; j < numPeople; j++) {
            //         if (countries[i].enemies[j]) {
            //             System.out.print(j + " ");
            //         }
            //     }
            //     System.out.println();
            // }

            opCode = in.nextInt();
            personA = in.nextInt();
            personB = in.nextInt();
        }
        // System.out.println("~~~ FINAL STANDING ~~~");
        // for (int i = 0; i < numPeople; i++) {
        //     System.out.print("Person " + i + " is friends with:\n\t");
        //         for (int j = 0; j < numPeople; j++) {
        //             if (countries[i].friends[j]) {
        //                 System.out.print(j + " ");
        //             }
        //         }
        //         System.out.println();

        //         System.out.print("Person " + i + " is enemies with:\n\t");
        //         for (int j = 0; j < numPeople; j++) {
        //             if (countries[i].enemies[j]) {
        //                 System.out.print(j + " ");
        //             }
        //         }
        //         System.out.println();
        // }
    }

    public static boolean setFriends(int personA, int personB, Country countries[]) {
        // If not enemies:
        // Add all friends to the list from the new friend as well, in both ways.
        // Check all enemies of all friends, and add this new relation to ea. enemy

        // Need some trickle down friendship :)

        // System.out.println("\t\t[Setting friends: " + personA + " & " + personB + "]");

        if (!areEnemies(personA, personB, countries)) {

            // Satisfy friend of friend property

            for (int i = 0; i < countries[personB].size; i++) {
                if (countries[personB].friends[i]) {
                    countries[personA].friends[i] = true;
                    countries[i].friends[personA] = true;
                }
            }

            // Satisfy friendship is mutual property

            for (int i = 0; i < countries[personA].size; i++) {
                if (countries[personA].friends[i]) {
                    countries[personB].friends[i] = true;
                    countries[i].friends[personB] = true;
                }
            }

            // Satisfy enemy of a friend is an enemy

            // Add enemies of B to the enemies of A, keeping hatred mutual
            for (int i = 0; i < countries[personB].size; i++) {
                if (countries[personB].enemies[i]) {
                    countries[personA].enemies[i] = true;
                    countries[i].enemies[personA] = true;
                }
            }
            // Add enemies of A to the enemies of B, keeping hatred mutual
            for (int i = 0; i < countries[personA].size; i++) {
                if (countries[personA].enemies[i]) {
                    countries[personB].enemies[i] = true;
                    countries[i].enemies[personB] = true;
                }
            }


        } else {
            // System.out.println("\t\t[Failed setting " + personA + " & " + personB + " as friends. They are enemies.]");
            return false;
        }

        return true;

    }

    public static boolean setEnemies(int personA, int personB, Country countries[]) {
        // If not friends:
        // Add all friends of new enemy to the list of enemies, in both ways
        // Check all enemies of the enemy and add them to friends, and add this person to their friends
        // System.out.println("\t\t[Setting enemies: " + personA + " & " + personB + "]");
        if (!areFriends(personA, personB, countries)) {
            boolean interference = false;

            // Hatred is mutual
            countries[personA].enemies[personB] = true;
            countries[personB].enemies[personA] = true;

            // All friends of A need to be enemies of B
            // set enemies for all 

            /**
             * Setting ea. enemy of your enemy as a friend satisfies:
             *  Go through each of the newFriend's enemies & add them to personA's enemies
             *  Go through each of the personA's enemies & add them to newFriend's enemies
             *  The above 2 make enemy of a friend is an enemy hold & the common enemy makes 2 people friends properties
             * 
             */
            
            // Satisfy enemy of the enemy is a friend

            for (int i = 0; i < countries[personB].size; i++) {
                if (countries[personB].enemies[i]) {
                    boolean success = setFriends(personA, i, countries);
                    if (!success) {
                        interference = true;
                    }
                }
            }

            // Satisfy enemy of a friend

            for (int i = 0; i < countries[personA].size; i++) {
                if (countries[personA].enemies[i]) {
                    boolean success = setFriends(personB, i, countries);
                    if (!success) {
                        interference = true;
                    }
                }
            }

            

            // Friend of my enemy is my enemy & hatred is mutual
            // Update all friends with new enemies
            for (int i = 0; i < countries[personA].size; i++) {
                if (countries[personA].enemies[i]) {
                    for (int j = 0; j < countries[personB].size; j++) {
                        if (countries[personA].friends[j]) {
                            countries[j].enemies[i] = true;
                        }
                    }
                }
                
            }

            // Update all friends with new enemies
            for (int i = 0; i < countries[personB].size; i++) {
                if (countries[personB].enemies[i]) {
                    for (int j = 0; j < countries[personA].size; j++) {
                        if (countries[personB].friends[j]) {
                            countries[j].enemies[i] = true;
                        }
                    }
                }
                
            }

            for (int i = 0; i < countries[personA].size; i++) {
                if (countries[personA].friends[i]) {
                    countries[personB].enemies[i] = true;
                }
            }

            if (interference) {
                System.out.println("-1");
            }
            

        } else {
            // System.out.println("Smaller msg");
            return false;
        }
        return true;
    }

    public static boolean areFriends(int personA, int personB, Country countries[]) {
        if (countries[personA].friends[personB]) {
            // System.out.println("\t\t[" + personA + " and " + personB + " are friends!]");
            return true;
        }
        // System.out.println("\t\t[" + personA + " and " + personB + " are not friends!]");
        return false;
    }

    public static boolean areEnemies(int personA, int personB, Country countries[]) {
        if (countries[personA].enemies[personB]) {
            // System.out.println("\t\t[" + personA + " and " + personB + " are enemies!]");
            return true;
        }
        // System.out.println("\t\t[" + personA + " and " + personB + " are not enemies!]");
        return false;
    }
}