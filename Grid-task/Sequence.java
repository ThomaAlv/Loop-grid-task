public class Sequence {
    protected int[][] indices;
    protected long product;

    public Sequence(int[][] in, long prod) {
        product = prod;
        indices = in;
    }

    /**
     * Checks whether or not this sequence has a higher product than a different sequence
     * @param other - the sequence to compare products with
     * @return - true if this.product >= other.product, false otherwise
     */
    public boolean isLargerThan(Sequence other) {
        return this.product >= other.product;
    }

    @Override
    public String toString() {
        //build formatted string for this sequence
        StringBuilder string = new StringBuilder();
        string.append("[");

        //append all coordinates in the sequence
        for (int[] coords : this.indices) {
            string.append(String.format("(%d, %d), ", coords[0]+1, coords[1]+1));
        }
        //delete trailing comma
        string.setLength(string.length()-2);

        string.append(String.format("]\nProduct of sequence: %d", this.product));
        return string.toString();
    }
}