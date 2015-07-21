package de.lyio;


import de.lyio.excercise1.Integral;

import static de.lyio.FloatUtils.Runden;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        out.println("Runden:");
        out.println(Runden(1.0 / 17, 4));

        out.println("Rekursives Integral: ");
        out.println(Integral.integral(20, 12));

        out.println("Rundungsfehler: ");

        int t = 6;
        double a = Runden(0.23371258e-4, t);
        double b = Runden(0.33678429e2, t);
        double c = Runden(-0.33677811e2, t);

        double bPlusC = (a + Runden(b + c, t));
        out.println(bPlusC);
        double aPlusB = (Runden(a + b, t) + c);
        out.println(aPlusB);
    }
}
