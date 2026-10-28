import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("branches.txt");
            Scanner scanner = new Scanner(inputFile);

            int N = scanner.nextInt();
            int[] branches = new int[N];
            for (int i = 0; i < N; i++) {
                branches[i] = scanner.nextInt();
            }

            int M = scanner.nextInt();
            int[] targets = new int[M];
            for (int i = 0; i < M; i++) {
                targets[i] = scanner.nextInt();
            }

            scanner.close();

            insertionSort3Passes(branches);

            // Print array state after 3 passes
            for (int i = 0; i < branches.length; i++) {
                System.out.print(branches[i] + (i == branches.length - 1 ? "" : " "));
            }
            System.out.println();

            // Search for targets in original order using Linear Search starting from index 0
            for (int target : targets) {
                int index = linearSearch(branches, target);
                if (index != -1) {
                    System.out.println(target + ": FOUND at index " + index);
                } else {
                    System.out.println(target + ": NOT FOUND");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File branches.txt not found.");
        }
    }

    public static void insertionSort3Passes(int[] A) {
        int passes = 3;
        for (int i = 1; i <= passes && i < A.length; i++) {
            int key = A[i];
            int j = i - 1;

            // Shift elements that are smaller than key to the right for descending order
            while (j >= 0 && A[j] < key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    public static int linearSearch(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }
}