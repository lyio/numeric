package de.lyio;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public final class FloatUtils {
    private FloatUtils(){}

    public static double Runden(double x, int t){
        double exp = pow(10.0, t);
        return round(x * exp) / exp;
    }
}
