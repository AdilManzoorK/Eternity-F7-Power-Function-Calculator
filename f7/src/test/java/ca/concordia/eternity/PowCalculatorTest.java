package ca.concordia.eternity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PowCalculatorTest {

    private static final double EPS = 1e-9;

    @Test public void twoPowFive_intExponent() {
        assertEquals(32.0, PowCalculator.pow(2, 5), EPS);
    }

    @Test public void twoPowMinusThree_negativeIntExponent() {
        assertEquals(0.125, PowCalculator.pow(2, -3), EPS);
    }

    @Test public void onePowBig_isOne() {
        assertEquals(1.0, PowCalculator.pow(1, 12345), EPS);
    }

    @Test public void zeroPowPositive_isZero() {
        assertEquals(0.0, PowCalculator.pow(0, 5), EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroPowZero_throws() {
        PowCalculator.pow(0, 0);
    }

    @Test public void positiveRealExponent_seriesPath() {
        double actual = PowCalculator.pow(5, 1.5);
        double expected = Math.pow(5, 1.5);
        assertEquals(expected, actual, 1e-9);
    }

    @Test public void negativeBase_integerExponent_ok() {
        assertEquals(-8.0, PowCalculator.pow(-2, 3), EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeBase_nonIntegerExponent_throws() {
        PowCalculator.pow(-2, 0.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rangeGuard_throws() {
        PowCalculator.pow(1e7, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nanGuard_throws() {
        PowCalculator.pow(Double.NaN, 2);
    }
}
