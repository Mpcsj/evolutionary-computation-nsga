package trabalhos.common.utils;

import java.util.Random;

public class MathUtils {
    public static Random random = new Random(System.currentTimeMillis());

    public static double getNextGaussian(){
        return random.nextGaussian();
    }
    public static double getNextDouble(){
        return random.nextDouble();
    }
}
