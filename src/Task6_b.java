public class Task6_b {

    /*
    0: time, 1: recordDistance
     */
    public static final long[] input = {49787980, 298118510661181L};

    public static void execute() {
        long result = errorMargin(input[0], input[1]);
        System.out.println(result);
    }

    public static long errorMargin (long time, long recordDistance) {
        long possibleSolutions = 0;
        for (int i = 0; i <= time; i++) {
            long distance = i * (time - i);
            if (distance > recordDistance) {
                possibleSolutions++;
            }
        }
        return possibleSolutions;
    }

}
