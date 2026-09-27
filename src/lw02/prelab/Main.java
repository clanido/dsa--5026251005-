package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
       
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String name = parts[0];
            String type = parts[1];
            String amount = parts[2];

            transactionList.add(new String[]{name, type, amount});

            boolean exists = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }

         
            if (!exists) {
                customerList.add(new String[]{name, "0"});
            }
        }
        scanner.close();

      
        Queue<String[]> transactionQueue = new LinkedList<>();
        while (!transactionList.isEmpty()) {
            transactionQueue.add(transactionList.removeFirst());
        }

       
        Stack<String[]> failedTransactions = new Stack<>();

      
        while (!transactionQueue.isEmpty()) {
            String[] currentTx = transactionQueue.poll();
            String name = currentTx[0];
            String type = currentTx[1];
            int amount = Integer.parseInt(currentTx[2]);

            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    int currentBalance = Integer.parseInt(cust[1]);

                    if (type.equalsIgnoreCase("DEPOSIT")) {
                        currentBalance += amount;
                        cust[1] = String.valueOf(currentBalance);
                    } else if (type.equalsIgnoreCase("WITHDRAW")) {
                        if (amount > currentBalance) {
                           
                            failedTransactions.push(currentTx);
                        } else {
                            currentBalance -= amount;
                            cust[1] = String.valueOf(currentBalance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTx = failedTransactions.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}