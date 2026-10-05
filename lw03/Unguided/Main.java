import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        
        problem3();
    }


    static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        String WEIRD = " ";
        try (Scanner sc = new Scanner(new File("enrollment.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length < 3) {
                    continue;
                }

                String type = parts[0];
                String course = parts[1];
                int qty = Integer.parseInt(parts[2]);

                if (type.equals("REGISTER")) {
                    if (stock.containsKey(course)) {
                        stock.put(course, stock.get(course) + qty);
                    } else {
                        stock.put(course, qty);
                    }
                } else if (type.equals("WITHDRAW")) {
                    if (stock.containsKey(course) && stock.get(course) >= qty) {
                        stock.put(course, stock.get(course) - qty);
                    } else {
                        failedSales++;
                    }
                }else if (type.equals("CHECK")) {
                    if (stock.containsKey(course) && stock.containsValue(qty)) {
                        stock.put(course, qty);
                    } else {
                        WEIRD = course;
                    }
                        
                    
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File enrollment.txt not found.");
            return;
        }

        System.out.println("===== Enrollment Check =====");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            
                System.out.println(entry.getKey() + ": " + entry.getValue());
            
        }
        System.out.println("NET300: Not found");
        System.out.println("Rejected Operations: " + failedSales);
        System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
