// UVa 10158

import java.util.*;

class Country {
    public Set<Integer> friends;
    public Set<Integer> enemies;
    Country() {
        friends = new HashSet<>();
        enemies = new HashSet<>();
    }
}

public class War {
    public static void main(String[] args) {
        War war = new War();
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
            countries[i] = new Country();
            // Satisfy everyone is a friend of himself property
            countries[i].friends.add(i);
        }
        while (opCode != 0 || personA != 0 || personB != 0) {
            // System.out.println("\t\t[Read in values:" + opCode + " | " + personA + " | " + personB + "]");
            if (opCode == 1) {
                // Set friends
                setFriends(personA, personB, countries);
            } else if (opCode == 2) {
                // Set enemies
                setEnemies(personA, personB, countries);
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
            //     Iterator friends = countries[i].friends.iterator();
            //     System.out.print("Person " + i + " is friends with:\n\t");
            //     while (friends.hasNext()) {
            //         System.out.print((int)friends.next() + " ");
            //     }
            //     System.out.println();
            //     Iterator enemies = countries[i].enemies.iterator();
            //     System.out.print("Person " + i + " is enemies with:\n\t");
            //     while (enemies.hasNext()) {
            //         System.out.print((int)enemies.next() + " ");
            //     }
            //     System.out.println();
            // }

            opCode = in.nextInt();
            personA = in.nextInt();
            personB = in.nextInt();
        }
        // System.out.println("~~~ FINAL STANDING ~~~");
        // for (int i = 0; i < numPeople; i++) {
        //     Iterator friends = countries[i].friends.iterator();
        //     System.out.print("Person " + i + " is friends with:\n\t");
        //     while (friends.hasNext()) {
        //         System.out.print((int)friends.next() + " ");
        //     }
        //     System.out.println();
        //     Iterator enemies = countries[i].enemies.iterator();
        //     System.out.print("Person " + i + " is enemies with:\n\t");
        //     while (enemies.hasNext()) {
        //         System.out.print((int)enemies.next() + " ");
        //     }
        //     System.out.println();
        // }
    }

    public static void setFriends(int personA, int personB, Country countries[]) {
        // If not enemies:
        // Add all friends to the list from the new friend as well, in both ways.
        // Check all enemies of all friends, and add this new relation to ea. enemy

        // Need some trickle down friendship :)

        // System.out.println("\t\t[Setting friends: " + personA + " & " + personB + "]");

        if (!areEnemies(personA, personB, countries)) {

            // Satisfy friend of friend property

            Iterator bFriends = countries[personB].friends.iterator();
            while (bFriends.hasNext()) {
                // Set adds if it doesn't exist in personA's set
                int newFriend = (int)bFriends.next();
                countries[personA].friends.add(newFriend);
                countries[newFriend].friends.add(personA);
            }

            // Satisfy friendship is mutual property

            Iterator aFriends = countries[personA].friends.iterator();
            while (aFriends.hasNext()) {
                // Set adds if it doesn't exist in personA's set
                int newFriend = (int)aFriends.next();
                countries[personB].friends.add(newFriend);
                countries[newFriend].friends.add(personB);
            }

            // Satisfy enemy of a friend is an enemy

            Iterator bEnemies = countries[personB].enemies.iterator();
            while (bEnemies.hasNext()) {
                // Add all enemies of personB to personA's enemies
                // Satisfy hatred is mutual property
                // Satisfy enemy of a friend is an enemy
                int newEnemy = (int)bEnemies.next();
                countries[personA].enemies.add(newEnemy);
                countries[newEnemy].enemies.add(personA);
            }
            Iterator aEnemies = countries[personA].enemies.iterator();
            while (bEnemies.hasNext()) {
                // Add all enemies of personA to personB's enemies
                // Satisfy hatred is mutual property
                // Satisfy enemy of a friend is an enemy
                int newEnemy = (int)aEnemies.next();
                countries[personB].enemies.add(newEnemy);
                countries[newEnemy].enemies.add(personB);
            }


        } else {
            // System.out.println("\t\t[Failed setting " + personA + " & " + personB + " as friends. They are enemies.]");
            System.out.println("-1");
        }

    }

    public static void setEnemies(int personA, int personB, Country countries[]) {
        // If not friends:
        // Add all friends of new enemy to the list of enemies, in both ways
        // Check all enemies of the enemy and add them to friends, and add this person to their friends
        // System.out.println("\t\t[Setting enemies: " + personA + " & " + personB + "]");
        if (!areFriends(personA, personB, countries)) {
            // boolean interference = false;

            // Hatred is mutual
            countries[personA].enemies.add(personB);
            countries[personB].enemies.add(personA);

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

            Iterator bEnemies = countries[personB].enemies.iterator();
            while (bEnemies.hasNext()) {
                // Add the enemies of the enemy is a friend
                int newFriend = (int)bEnemies.next();
                setFriends(personA, newFriend, countries);
            }

            // Satisfy enemy of a friend

            Iterator aEnemies = countries[personA].enemies.iterator();
            while (aEnemies.hasNext()) {
                // Add the enemies of the enemy is a friend
                int newFriend = (int)aEnemies.next();
                setFriends(personB, newFriend, countries);
            }

            

            // Friend of my enemy is my enemy & hatred is mutual
            // Updated all friends with new enemies
            aEnemies = countries[personA].enemies.iterator();
            Iterator aFriends = countries[personA].friends.iterator();
            while (aEnemies.hasNext()) {
                int enemy = (int)aEnemies.next();
                while (aFriends.hasNext()) {
                    int friend = (int)aFriends.next();
                    countries[friend].enemies.add(enemy);
                }
            }

            bEnemies = countries[personB].enemies.iterator();
            Iterator bFriends = countries[personB].friends.iterator();
            while (bEnemies.hasNext()) {
                int enemy = (int)bEnemies.next();
                while (bFriends.hasNext()) {
                    int friend = (int)bFriends.next();
                    countries[friend].enemies.add(enemy);
                }
            }

            aFriends = countries[personA].friends.iterator();
            while (aFriends.hasNext()) {
                int newEnemy = (int)aFriends.next();
                // if (areFriends(personB, newEnemy, countries)) {
                   // Interference
                    // System.out.println("-1");
                    // break;
                // } else {
                    countries[personB].enemies.add(newEnemy);
                // }
            }
            

        } else {
            System.out.println("-1");
        }
    }

    public static boolean areFriends(int personA, int personB, Country countries[]) {
        if (countries[personA].friends.contains(personB)) {
            // System.out.println("\t\t[" + personA + " and " + personB + " are friends!]");
            return true;
        }
        // System.out.println("\t\t[" + personA + " and " + personB + " are not friends!]");
        return false;
    }

    public static boolean areEnemies(int personA, int personB, Country countries[]) {
        if (countries[personA].enemies.contains(personB)) {
            // System.out.println("\t\t[" + personA + " and " + personB + " are enemies!]");
            return true;
        }
        // System.out.println("\t\t[" + personA + " and " + personB + " are not enemies!]");
        return false;
    }
}