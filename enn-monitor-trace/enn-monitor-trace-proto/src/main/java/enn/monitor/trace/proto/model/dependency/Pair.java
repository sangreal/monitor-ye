package enn.monitor.trace.proto.model.dependency;

/**
 * Created by weize on 18-1-3.
 */
public class Pair {
        private final EnnEndpoint left;
        private final EnnEndpoint right;
        private long minute;

    public static Pair of(EnnEndpoint left, EnnEndpoint right, long minute) {
        return new Pair(left, right, minute);
    }

    Pair(EnnEndpoint left, EnnEndpoint right, long minute) {
        if (left == null) {
            throw new NullPointerException("Null left");
        }
        this.left = left;
        if (right == null) {
            throw new NullPointerException("Null right");
        }
        this.right = right;
        this.minute = minute;
    }

    public EnnEndpoint left() {
            return left;
        }

    public EnnEndpoint right() {
            return right;
        }

    public long minute() {
            return minute;
        }

        @Override
        public String toString() {
            return "Pair{"
                    + "left=" + left + ", "
                    + "right=" + right
                    + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Pair) {
                Pair that = (Pair) o;
                return (this.left.equals(that.left()))
                        && (this.right.equals(that.right()))
                        && this.minute() == that.minute();
            }
            return false;
        }

        @Override
        public int hashCode() {
            int h = 1;
            h *= 1000003;
            h ^= this.left.hashCode();
            h *= 1000003;
            h ^= this.right.hashCode();
            return h;
        }
}
