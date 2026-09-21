public abstract class Rental implements Chargeable {
    private String id;
    private int days;
    private int units;

    protected Rental(String id, int days, int units) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be a positive integer.");
        }
        if (units <= 0) {
            throw new IllegalArgumentException("units must be a positive integer.");
        }
        this.id = id;
        this.days = days;
        this.units = units;
    }
    public String getId() {
        return id;
    }
    public int getdays() {
        return days;
    }
    public int getUnits() {
        return units;
    }
    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be a positive integer.");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
