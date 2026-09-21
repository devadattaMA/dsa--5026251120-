import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
List<Rental> rentals = new ArrayList<>();
        try (Scanner Rent = new Scanner(new File("rentals.txt"))) {
            int times = Rent.nextInt();
            for (int i = 0; i < times; i++) {
                String type = Rent.next();
                String id = Rent.next();
                int days = Rent.nextInt();
                int units = Rent.nextInt();

                if (type.equalsIgnoreCase("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days, units));
                } else if (type.equalsIgnoreCase("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days, units));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find rentals.txt: " + e.getMessage());
            return;
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}
