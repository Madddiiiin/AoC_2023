public class Task6_a {

    /*
    0: time, 1: recordDistance
     */
    public static final int[][] input = {{49, 298}, {78, 1185}, {79, 1066}, {80, 1181}};

    public static void execute() {
        int result = computeAllRaces();
        System.out.println(result);
    }

    public static int errorMargin (int time, int recordDistance) {
        int possibleSolutions = 0;
        for (int i = 0; i <= time; i++) {
            int distance = i * (time - i);
            if (distance > recordDistance) {
                possibleSolutions++;
            }
        }
        return possibleSolutions;
    }

    public static int computeAllRaces () {
        int result = 1;
        for (int[] race : input) {
            result *= errorMargin(race[0], race[1]);
        }
        return result;
    }

}