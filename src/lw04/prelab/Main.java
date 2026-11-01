import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("dsa-5026251005/src/lw04/prelab/branches.txt");
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = scanner.nextInt();
            }

            int m = scanner.nextInt();
            int[] targets = new int[m];
            for (int j = 0; j < m; j++) {
                targets[j] = scanner.nextInt();
            }
            scanner.close();

            insertionSortDesc(branches);

            for (int val : branches) {
                System.out.print(val + " ");
            }
            System.out.println();

            for (int i = 0; i < targets.length; i++) {
                linearSearch(branches, targets[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File branches.txt tidak ditemukan");
        }
    }

   
    public static void insertionSortDesc(int[] arr) {
        for (int i = 1; i <= 3; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println(target + ": FOUND at index " + i);
                return;
            }
        }
        System.out.println(target + ": NOT FOUND");
    }
}

