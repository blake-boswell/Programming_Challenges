import java.util.*;

class Pair {
    public float x;
    public float y;
    Pair(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return "(" + this.x + ","  + this.y + ")";
    }
}

public class Freckles {
    public static void main(String[] args) {
        Freckles freckles = new Freckles();
        freckles.run();
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        int numTestCases = in.nextInt();
        int currentCase = 1;
        while (currentCase <= numTestCases) {
            int numFreckles = in.nextInt();
            Pair freckles[] = new Pair[numFreckles];
            for (int i = 0; i < numFreckles; i++) {
                float x = in.nextFloat();
                float y = in.nextFloat();
                freckles[i] = new Pair(x, y);
            }

            print(freckles);
            currentCase++;
        }
    }

    public void print(Pair freckles[]) {
        for (int i = 0; i < freckles.length; i++) {
            System.out.println("[" + i + "]\t" + freckles[i]);
        }
    }
}