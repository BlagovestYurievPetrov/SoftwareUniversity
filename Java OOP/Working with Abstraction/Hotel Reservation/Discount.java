public enum Discount {
    VIP(0.8),
    SECONDVISIT(0.9),
    NONE(1.0);
    private final double discount;

    Discount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return this.discount;
    }
}
