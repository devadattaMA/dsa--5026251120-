public class LaptopRental extends Rental {
    private static final int RATE_PER_DAY = 40000;

    public static int getRatePerDay() {
        return RATE_PER_DAY;
    }

    public LaptopRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getdays();
        int units = getUnits();
        return days * RATE_PER_DAY + (units * 10000);
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
