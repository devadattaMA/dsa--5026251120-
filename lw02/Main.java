
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        // 1. Read and store transactions
        LinkedList<String[]> transactions = new LinkedList<>();
        // 2. Create customer data
        LinkedList<String[]> customers = new LinkedList<>();

        try {
            Scanner sc = new Scanner(new File("transactions.txt"));

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                transactions.add(parts);

                String name = parts[0];

                // Add customer only the first time their name appears
                boolean alreadyExists = false;
                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        alreadyExists = true;
                        break;
                    }
                }
                if (!alreadyExists) {
                    customers.add(new String[]{name, "0"});
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt not found.");
            return;
        }

        // 3. Process transactions using Queue
        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        // 4. Store failed transactions using Stack
        Stack<String[]> failedTransactions = new Stack<>();

        while (!queue.isEmpty()) {
            String[] transaction = queue.poll();
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            // Find the corresponding customer
            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                continue;
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    // Insufficient balance -> failed transaction
                    failedTransactions.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        // 5. Display final balances and failed transactions
        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}
