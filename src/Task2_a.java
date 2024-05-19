import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2_a {

    public static void execute () {
        List<String> lines = fileReader();
        int idSum = 0;
        for (String line : lines) {
            String[] colors = getColors(line);
            if (isRedPossible(colors) && isGreenPossible(colors) && isBluePossible(colors)) {
                idSum += getId(line);
            }
        }
        System.out.println(idSum);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2023_day2_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static int getId (String game) {
        String id = game.substring(5, game.indexOf(':'));
        return Integer.parseInt(id);
    }

    public static String[] getColors (String game) {
        String colors = game.substring(game.indexOf(':') + 2);
        return colors.split(" ");
    }

    public static boolean isRedPossible (String[] colors) {
        int reference = 12;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("red") || colors[i].equals("red,") || colors[i].equals("red;")) {
                if (Integer.parseInt(colors[i - 1]) > reference) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isGreenPossible (String[] colors) {
        int reference = 13;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("green") || colors[i].equals("green,") || colors[i].equals("green;")) {
                if (Integer.parseInt(colors[i - 1]) > reference) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBluePossible (String[] colors) {
        int reference = 14;
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals("blue") || colors[i].equals("blue,") || colors[i].equals("blue;")) {
                if (Integer.parseInt(colors[i - 1]) > reference) {
                    return false;
                }
            }
        }
        return true;
    }

}
