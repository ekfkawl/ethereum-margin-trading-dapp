package ekfkawl.dapp.global.utils;

import java.math.BigInteger;

public class MathUtil {

    public static BigInteger toPercentValue(long entry, long current, double x, BigInteger value) {
        BigInteger res = value;

        double per = Math.abs(entry - current) / (double)entry * x * 100;

        if (current < entry && per >= 100.0) {
            return new BigInteger("0");
        }

        BigInteger mul = res.multiply(BigInteger.valueOf((long)per)).divide(new BigInteger("100"));
        return res.add(mul.multiply((double)current >= entry ? new BigInteger("1") : new BigInteger("-1")));
    }
}
