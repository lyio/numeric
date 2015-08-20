package de.lyio;

import static java.lang.Math.*;

public final class FloatUtils {
    private FloatUtils(){}

    public static double Runden(double x, int t){
        int foo = (int)ceil((log((abs(x)))/log(10)));
        double exp = pow(10.0, t - foo);
        return round(x * exp) / exp;
    }
}
