package de.lyio.excercise1;

import static de.lyio.FloatUtils.Runden;
import static java.lang.Math.*;

public class Integral {

    private final int steps;

    private Integral(int steps) {
        this.steps = steps + 1;
    }

    public static double integral(int n, int precision) {
        double i0 = Runden(log(5.0 / 6.0), precision);
        System.out.println("I0: " + i0);

        return new Integral(n).integralRecursive(i0, 1, precision);
    }

    private double integralRecursive(double iN_1, int n, int t) {
        if (n == steps) {
            return iN_1;
        } else {
            double iN = Runden(-5 * iN_1 + 1.0/n, t);
            System.out.println(n + ": " + iN);

            return integralRecursive(iN, n + 1, t);
        }
    }
}
