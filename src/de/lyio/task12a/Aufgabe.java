package de.lyio.task12a;

import de.lyio.exercise6.Function;
import de.lyio.exercise6.NumDiff;

import static de.lyio.FloatUtils.Runden;
import static java.lang.Math.*;

public class Aufgabe {
    public static void main(String[] args) {
        int mantissa = extractMantissa(args);
        double at = extractAt(args);
        NumDiff numDiff = new NumDiff();
        Function f = x -> sqrt(pow(sin(x), 2) + log(2 + pow(x, 2)));
        Function df = x -> (((2 * x / (pow(x, 2) + 2) + (2 * sin(x) * cos(x))) / (2 * sqrt(log(pow(x, 2) + 2) + pow(sin(x), 2)))));

        double exact = df.f(at);
        System.out.format("Exakt: %s%n", exact);
        System.out.format("Mantissenlänge %d: %s%n%n", mantissa, Runden(exact, mantissa));

        differentiate(mantissa, numDiff, f, exact, 2, at);
        differentiate(mantissa, numDiff, f, exact, 4, at);
    }

    private static double extractAt(String[] args) {
        if (args != null && args.length > 1) {
            return Double.parseDouble(args[1].trim());
        } else {
            System.out.println("Keine Stelle angegeben, Standardwert: 1.0");
            return 1.0;
        }
    }

    private static void differentiate(int mantissa, NumDiff numDiff, Function f, double exact, int level, double x0) {
        double h2;
        double previous = 0.0;

        double best = 0.0;
        double lowestError = 1.0;

        for (int i = 1; i < 14; i++) {
            double h = pow(10, -1 * i);
            h2 = level == 2 ? numDiff.h2(f, x0, h) : numDiff.h4(f, x0, h);
            double error = abs(exact - h2);
            if (error < lowestError) {
                lowestError = error;
                best = h;
            }
            System.out.format("h = %7s: h = %14s, error: %22s, extrapolated: %14s%n", h, Runden(h2, mantissa), error, Runden(NumDiff.extrapolate(previous, h2, level, 0.1), mantissa));
            previous = h2;
        }

        System.out.format("Kleinster Fehler bei h=%s mit Ergebnis=%s%n%n",
                best,
                Runden(level == 2 ? numDiff.h2(f, x0, best) : numDiff.h4(f, x0, best), mantissa));
        System.out.println("----------------------------------------------\n");
    }

    private static int extractMantissa(String[] args) {
        if (args != null && args.length > 0) {
            return Integer.parseInt(args[0].trim());
        } else {
            System.out.println("Keine Mantissenlänge angegeben, Standardwert: 12");
            return 12;
        }
    }
}
