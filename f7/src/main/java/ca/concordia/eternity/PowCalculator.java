package ca.concordia.eternity;

/** Pure math for x^y with domain checks and series expansions. */
final class PowCalculator {
    private static final double MAX_ABS = 1e6;
    private static final int LN_TERMS = 101;
    private static final double EXP_EPS = 1e-15;

    private PowCalculator() {
    }

    static double pow(double x, double y) {
        if (x == 0.0 && y == 0.0) {
            throw new IllegalArgumentException("ERR_DOMAIN: 0^0 undefined");
        }
        if (Math.abs(x) > MAX_ABS || Math.abs(y) > MAX_ABS) {
            throw new IllegalArgumentException("ERR_RANGE: |x|,|y| ≤ 10^6");
        }
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new IllegalArgumentException("ERR_NUM: NaN");
        }
        if (x < 0 && y != Math.floor(y)) {
            throw new IllegalArgumentException("ERR_DOMAIN: negative base needs integer exponent");
        }
        if (y == Math.floor(y)) {
            return intPow(x, (long) y);
        }
        if (x <= 0) {
            throw new IllegalArgumentException("ERR_DOMAIN: non-positive base with real exponent");
        }
        double ln = ln(x);
        return exp(y * ln);
    }

    private static double intPow(double base, long exp) {
        double res = 1.0;
        long e = Math.abs(exp);
        double b = base;
        while (e > 0) {
            if ((e & 1) == 1) {
                res *= b;
            }
            b *= b;
            e >>= 1;
        }
        return exp < 0 ? 1.0 / res : res;
    }

    private static double ln(double x) {
        double z = (x - 1) / (x + 1);
        double z2 = z * z;
        double term = z;
        double sum = 0.0;
        for (int n = 1; n <= LN_TERMS; n += 2) {
            sum += term / n;
            term *= z2;
        }
        return 2 * sum;
    }

    private static double exp(double t) {
        double term = 1.0;
        double sum = 1.0;
        for (int n = 1; n < 25; n++) {
            term *= t / n;
            sum += term;
            if (Math.abs(term) < EXP_EPS) {
                break;
            }
        }
        return sum;
    }
}
