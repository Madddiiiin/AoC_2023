import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Task3_a {

    public static void execute () {
        List<String> list = valueSelection(stringToChar(fileReader()));
        int result = adder(stringToInt(list));
        System.out.println(result);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("aoc_2023_day3_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static List<char[]> stringToChar (List<String> input) {
        List<char[]> output = new ArrayList<>();
        for (String current : input) {
            char[] characters = current.toCharArray();
            output.add(characters);
        }
        return output;
    }

    private static final HashSet<Character> symbols = new HashSet<>();

    static {
        symbols.add('#');
        symbols.add('$');
        symbols.add('%');
        symbols.add('&');
        symbols.add('*');
        symbols.add('+');
        symbols.add('-');
        symbols.add('/');
        symbols.add('=');
        symbols.add('@');
    }

    public static boolean isSymbol (Character character) {
        return symbols.contains(character);
    }

    public static boolean hasSymbolInPeriphery (List<char[]> characters, int row, int column) {
        if (
                        (row > 0 && isSymbol(characters.get(row - 1)[column]))                                                                              //top
                        || (row > 0 && column > 0 && isSymbol(characters.get(row - 1)[column - 1]))                                                         //top left
                        || (row > 0 && column < characters.get(row - 1).length - 1 && isSymbol(characters.get(row - 1)[column + 1]))                        //top right

                        || (column > 0 && isSymbol(characters.get(row)[column - 1]))                                                                        //left
                        || (column < characters.get(row).length - 1 && isSymbol(characters.get(row)[column + 1]))                                           //right

                        || (row < characters.size() - 1 && isSymbol(characters.get(row + 1)[column]))                                                       //bottom
                        || (row < characters.size() - 1 && column > 0 && isSymbol(characters.get(row + 1)[column - 1]))                                     //bottom left
                        || (row < characters.size() - 1 && column < characters.get(row + 1).length - 1 && isSymbol(characters.get(row + 1)[column + 1]))    //bottom right
        ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber (char character) {
        return character > 47 && character < 58;
    }

    public static List<String> valueSelection (List<char[]> characters) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            for (int j = 0; j < characters.get(i).length; j++) {
                if (isNumber(characters.get(i)[j])) {
                    String newValue = "";
                    if (hasSymbolInPeriphery(characters, i, j)) {
                        newValue = String.valueOf(characters.get(i)[j]);
                        if (j < characters.get(i).length - 1 && isNumber(characters.get(i)[j + 1])) {
                            newValue = newValue + characters.get(i)[j + 1];
                            if (j < characters.get(i).length - 2 && isNumber(characters.get(i)[j + 2])) {
                                newValue = newValue + characters.get(i)[j + 2];
                                j++;
                            }
                            j++;
                        }
                    } else if (j < characters.get(i).length - 1 && isNumber(characters.get(i)[j + 1]) && hasSymbolInPeriphery(characters, i, j + 1)) {
                        newValue = characters.get(i)[j] + String.valueOf(characters.get(i)[j + 1]);
                        if (j < characters.get(i).length - 2 && isNumber(characters.get(i)[j + 2])) {
                            newValue = newValue + characters.get(i)[j + 2];
                            j++;
                        }
                        j++;
                    } else if (j < characters.get(i).length - 2 && isNumber(characters.get(i)[j + 1]) && isNumber(characters.get(i)[j + 2]) && hasSymbolInPeriphery(characters, i, j + 2)) {
                        newValue = characters.get(i)[j] + String.valueOf(characters.get(i)[j + 1]) + characters.get(i)[j + 2];
                        j += 2;
                    }

                    if (!newValue.isEmpty()) {
                        values.add(newValue);
                    }
                }
            }
        }
        return values;
    }

    public static List<Integer> stringToInt (List<String> list) {
        List<Integer> output = new ArrayList<>();
        for (String s : list) {
            output.add(Integer.valueOf(s));
        }
        return output;
    }

    public static int adder (List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

}