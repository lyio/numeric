package de.lyio.task12a;

import de.lyio.exercise6.Function;
import de.lyio.exercise6.NumDiff;

import static de.lyio.FloatUtils.Runden;
import static java.lang.Math.*;

public class Aufgabe {
    public static void main(String[] args) {
        int mantissa = extractMantissa(args);
        NumDiff numDiff = new NumDiff();
        Function f = x -> sqrt(pow(sin(x), 2) + log(2 + pow(x, 2)));
        Function df = x -> (((2 * x / (pow(x, 2) + 2) + (2 * sin(x) * cos(x))) / (2 * sqrt(log(pow(x, 2) + 2) + pow(sin(x), 2)))));

        double exact = df.f(1.0);
        System.out.format("Exakt: %s%n", exact);
        System.out.format("Mantissenlänge %d: %s%n%n", mantissa, Runden(exact, mantissa));
        double h2;
        double h4;

        double best = 0.0;
        double lowestError = 1.0;

        for (int i = 1; i < 14; i++) {
            double h = pow(10, -1 * i);
            h2 = numDiff.h2(f, 1.0, h);
            double error = abs(exact - h2);
            if (error < lowestError) {
                lowestError = error;
                best = h;
            }
            System.out.format("h = %7s: h2 = %14s, error2: %s%n", h, Runden(h2, mantissa), error);
        }

        System.out.format("Kleinster Fehler bei h=%s mit Ergebnis=%s%n%n", best, Runden(numDiff.h2(f, 1.0, best), mantissa));
        System.out.println("----------------------------------------------\n");

        // resetting lowest error and best h
        best = 0.0;
        lowestError = 1.0;

        for (int i = 1; i < 14; i++) {
            double h = pow(10, -1 * i);
            h4 = Runden(numDiff.h4(f, 1.0, h), mantissa);
            double error = abs(exact - h4);
            if (error < lowestError) {
                lowestError = error;
                best = h;
            }
            System.out.format("h = %7s: h4 = %14s, error4: %s%n", h, Runden(h4, mantissa), error);
        }

        System.out.format("Kleinster Fehler bei h=%s mit Ergebnis=%s%n%n", best, Runden(numDiff.h4(f, 1.0, best), mantissa));
    }

    public static int extractMantissa(String[] args) {
        if (args != null && args.length > 0) {
            return Integer.parseInt(args[0].trim());
        } else {
            System.out.println("Keine Mantissenlänge angegeben, Standardwert: 12");
            return 12;
        }
    }
}
