package Group3.JobsMadeEasy.util;


import java.util.concurrent.ThreadLocalRandom;

public class GenerateIdUtil {

    private static GenerateIdUtil obj = null;

    public static GenerateIdUtil Object() {
        if (obj == null) {
            obj = new GenerateIdUtil();
        }
        return obj;
    }

    public int generateRandomId() {
        return ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    }
}
