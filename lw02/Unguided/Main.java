
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static class Booktitle {

        String title;
        int stock;

        Booktitle(String title, int stock) {
            this.title = title;
            this.stock = stock;
        }
    }

    public static void main(String[] args) {
        LinkedList<Booktitle> stock = new LinkedList<>();
        stock.add(new Booktitle("Kalkulus", 2));
        stock.add(new Booktitle("Fisika", 1));
        stock.add(new Booktitle("Statistika", 2));

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();
        LinkedList<String[]> successfulRequests = new LinkedList<>();
        Map<String, Integer> borrowerCount = new HashMap<>();

        try (Scanner sc = new Scanner(new File("Borrowing.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    queue.add(parts);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Borrowing.txt not found.");
            return;
        }

        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String title = request[1];

            Booktitle book = findBook(stock, title);
            int count = borrowerCount.getOrDefault(name, 0);

            if (count >= 2) {
                failedTransactions.push(request);
                continue;
            }

            if (book == null || book.stock <= 0) {
                failedTransactions.push(request);
                continue;
            }

            borrowerCount.put(name, count + 1);
            book.stock--;
            successfulRequests.add(request);
        }

        System.out.println("=== Successfully Processed Requests ===");
        System.out.println();
        for (String[] request : successfulRequests) {
            System.out.println(request[0] + " " + request[1]);
            System.out.println();
        }

        System.out.println("=== Remaining Book Stock ===");
        System.out.println();
        for (Booktitle book : stock) {
            System.out.println(book.title + " : " + book.stock);
            System.out.println();
        }

        System.out.println("=== Failed Requests ===");
        System.out.println();
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1]);
            System.out.println();
        }
    }

    private static Booktitle findBook(LinkedList<Booktitle> stock, String title) {
        for (Booktitle book : stock) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
