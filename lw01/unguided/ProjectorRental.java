
public class ProjectorRental extends Rental {

    private static final int RATE_FIRST_3_DAYS = 60000;
    private static final int RATE_AFTER_3_DAYS = 45000;
    private static final int SETUP_FEE = 20000;

    public ProjectorRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int DayCharge;
        int days = getdays();
        if (days <= 3) {
            DayCharge = days * RATE_FIRST_3_DAYS;
        } else {
            int extradays = days - 3;
            DayCharge = (3 * RATE_FIRST_3_DAYS) + (extradays * RATE_AFTER_3_DAYS);
        }
        return (DayCharge + SETUP_FEE) * getUnits();
    }

    @Override
    public String label() {
        return "projector";
    }
}
