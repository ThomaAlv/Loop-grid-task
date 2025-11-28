public class Sequence {
    protected int[][] indices;
    protected int product;

    public Sequence(int[][] in, int prod) {
        product = prod;
        indices = in;
    }

    public boolean isLargerThan(Sequence other) {
        return this.product >= other.product;
    }
}