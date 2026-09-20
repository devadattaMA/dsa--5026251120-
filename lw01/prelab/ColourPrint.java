public class ColourPrint extends PrintJob {
    private static final int RATE_FIRST_10_PAGES = 1500;
    private static final int RATE_AFTER_10_PAGES = 1000;
    private static final int SETUP_FEE = 2000;
    private static final int DISCOUNT_THRESHOLD = 10;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pageCharge;
        if (pages <= DISCOUNT_THRESHOLD) {
            pageCharge = pages * RATE_FIRST_10_PAGES;
        } else {
            int extraPages = pages - DISCOUNT_THRESHOLD;
            pageCharge = (DISCOUNT_THRESHOLD * RATE_FIRST_10_PAGES) + (extraPages * RATE_AFTER_10_PAGES);
        }
        return pageCharge + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
