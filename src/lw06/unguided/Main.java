package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

         // Write your main code here

        String firstLine = sc.nextLine();
        String[] numbers = firstLine.split(" ");

        BinarySearchTree bst = new BinarySearchTree();

        for (int i = 0; i < numbers.length; i++) {
            int value = Integer.parseInt(numbers[i]);
            bst.insert(value);
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder  : ");
        bst.inOrder();

        System.out.print("Postorder: ");
        bst.postOrder();

        int target = sc.nextInt();
        System.out.println("Search " + target + ": " + bst.search(target));
        System.out.println("Minimum  : " + bst.findMin());
        System.out.println("Maximum  : " + bst.findMax());

        sc.close();
    }
}