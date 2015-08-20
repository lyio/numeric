package de.lyio.exercise6;

import static java.lang.Math.*;

public class NumDiff {

    public static void main(String[] args) {
        NumDiff numDiff = new NumDiff();

        double h2 = 0.0;
        double h4 = 0.0;
        double h2Prev = 0.0;
        double h4Prev = 0.0;

        Function f = x -> 4 + 3 * cos(x) + -2 * sin(2 * x) + sin(5 * x);
        Function df = x -> -3 * sin(x) + -4 * cos(2 * x) + 5* cos(5 * x);

        double exact = df.f(5.0);
        System.out.println("Exact: " + exact);
        for (int i = 1; i < 14; i++) {
            double h = pow(10, -1 * i);
            h2 = numDiff.h2(f, 5.0, h);
            h4 = numDiff.h4(f, 5.0, h);

            System.out.println(String.format("h = %s: h2 = %s, h4 = %s, error2: %s | error4: %s", h, h2, h4, exact - h2, exact -h4));

            System.out.println(
                    String.format("Extrapolated: \n h2: %s \n h4: %s",
                            extrapolateRichardson(h2, h2Prev, 2, 0.1),
                            extrapolateRichardson(h4, h4Prev, 4, 0.1)));

            h2Prev = h2;
            h4Prev = h4;
        }

        double h2_1 = numDiff.h2(f, 5.0, pow(10, -11));
        double h2_2 = numDiff.h2(f, 5.0, pow(10, -12));

        double h4_1 = numDiff.h4(f, 5.0, pow(10, -11));
        double h4_2 = numDiff.h4(f, 5.0, pow(10, -12));

        System.out.println(extrapolate(h2_1, h2_2, 2.0, 0.1));
        System.out.println(extrapolate(h4_1, h4_2, 4.0, 0.1));
    }

    public double h2(Function f, double x, double h) {
        return (f.f(x + h) - f.f(x-h))/(2*h);
    }

    public double h4(Function f, double x, double h) {
        return (f.f(x - 2*h) - 8*f.f(x-h) + 8*f.f(x + h) - f.f(x + 2*h))/(12*h);
    }

    public static double extrapolate(double x1, double x2, double alpha, double beta) {
        return (x2 - pow(beta, alpha) * x1) / (1 - pow(beta, alpha));
    }



    public static double extrapolateRichardson(double ug, double uu, int p, double h) {
        return ug + ((uu - ug) /(1 - pow(h, p)));
    }
}
