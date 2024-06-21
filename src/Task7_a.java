import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Task7_a {

    public static void execute () {
        List<String> input = fileReader();
        List<CamelCard> test = arrangeInputs(input);
        int[][] values = getAllValues(test);
        mergeSort(values, values.length);
        int result = computeTotalWinnings(values);
        System.out.println(result);
    }

    public static List<String> fileReader() {
        Path path = Paths.get("C:\\Users\\marti\\OneDrive\\Desktop\\aoc_2023_day7_puzzle_input.txt");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("An IO Exception occurred!");
            return new ArrayList<>();
        }
    }

    public static List<CamelCard> arrangeInputs (List<String> input) {
        List<CamelCard> inputList = new ArrayList<>();
        for (String row : input) {
            char[] hand = row.substring(0, 5).toCharArray();
            int bid = Integer.parseInt(row.substring(6));
            CamelCard camelCard = new CamelCard(hand, bid);
            inputList.add(camelCard);
        }
        return inputList;
    }

    public static int[] getHandValue (CamelCard camelCard) {
        char[] hand = camelCard.hand;
        int[] valuePair = new int[2];
        int primaryValue = 0;
        int secondaryValue = 0;

        if (isFiveOfAKind(hand)) {
            primaryValue = 6;
        } else if (isFourOfAKind(hand)) {
            primaryValue = 5;
        } else if (isFullHouse(hand)) {
            primaryValue = 4;
        } else if (isThreeOfAKind(hand)) {
            primaryValue = 3;
        } else if (isTwoPair(hand)) {
            primaryValue = 2;
        } else if (isOnePair(hand)) {
            primaryValue = 1;
        }

        switch (hand[0]) {
            case 'A':
                secondaryValue = 1200000000;
                break;
            case 'K':
                secondaryValue = 1100000000;
                break;
            case 'Q':
                secondaryValue = 1000000000;
                break;
            case 'J':
                secondaryValue = 900000000;
                break;
            case 'T':
                secondaryValue = 800000000;
                break;
            case '9':
                secondaryValue = 700000000;
                break;
            case '8':
                secondaryValue = 600000000;
                break;
            case '7':
                secondaryValue = 500000000;
                break;
            case '6':
                secondaryValue = 400000000;
                break;
            case '5':
                secondaryValue = 300000000;
                break;
            case '4':
                secondaryValue = 200000000;
                break;
            case '3':
                secondaryValue = 100000000;
                break;
            default:
                break;
        }

        switch (hand[1]) {
            case 'A':
                secondaryValue += 12000000;
                break;
            case 'K':
                secondaryValue += 11000000;
                break;
            case 'Q':
                secondaryValue += 10000000;
                break;
            case 'J':
                secondaryValue += 9000000;
                break;
            case 'T':
                secondaryValue += 8000000;
                break;
            case '9':
                secondaryValue += 7000000;
                break;
            case '8':
                secondaryValue += 6000000;
                break;
            case '7':
                secondaryValue += 5000000;
                break;
            case '6':
                secondaryValue += 4000000;
                break;
            case '5':
                secondaryValue += 3000000;
                break;
            case '4':
                secondaryValue += 2000000;
                break;
            case '3':
                secondaryValue += 1000000;
                break;
            default:
                break;
        }

        switch (hand[2]) {
            case 'A':
                secondaryValue += 120000;
                break;
            case 'K':
                secondaryValue += 110000;
                break;
            case 'Q':
                secondaryValue += 100000;
                break;
            case 'J':
                secondaryValue += 90000;
                break;
            case 'T':
                secondaryValue += 80000;
                break;
            case '9':
                secondaryValue += 70000;
                break;
            case '8':
                secondaryValue += 60000;
                break;
            case '7':
                secondaryValue += 50000;
                break;
            case '6':
                secondaryValue += 40000;
                break;
            case '5':
                secondaryValue += 30000;
                break;
            case '4':
                secondaryValue += 20000;
                break;
            case '3':
                secondaryValue += 10000;
                break;
            default:
                break;
        }

        switch (hand[3]) {
            case 'A':
                secondaryValue += 1200;
                break;
            case 'K':
                secondaryValue += 1100;
                break;
            case 'Q':
                secondaryValue += 1000;
                break;
            case 'J':
                secondaryValue += 900;
                break;
            case 'T':
                secondaryValue += 800;
                break;
            case '9':
                secondaryValue += 700;
                break;
            case '8':
                secondaryValue += 600;
                break;
            case '7':
                secondaryValue += 500;
                break;
            case '6':
                secondaryValue += 400;
                break;
            case '5':
                secondaryValue += 300;
                break;
            case '4':
                secondaryValue += 200;
                break;
            case '3':
                secondaryValue += 100;
                break;
            default:
                break;
        }

        switch (hand[4]) {
            case 'A':
                secondaryValue += 12;
                break;
            case 'K':
                secondaryValue += 11;
                break;
            case 'Q':
                secondaryValue += 10;
                break;
            case 'J':
                secondaryValue += 9;
                break;
            case 'T':
                secondaryValue += 8;
                break;
            case '9':
                secondaryValue += 7;
                break;
            case '8':
                secondaryValue += 6;
                break;
            case '7':
                secondaryValue += 5;
                break;
            case '6':
                secondaryValue += 4;
                break;
            case '5':
                secondaryValue += 3;
                break;
            case '4':
                secondaryValue += 2;
                break;
            case '3':
                secondaryValue += 1;
                break;
            default:
                break;
        }

        valuePair[0] = primaryValue;
        valuePair[1] = secondaryValue;
        return valuePair;
    }

    public static boolean isFiveOfAKind (char[] hand) {
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i] != hand[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFourOfAKind (char[] hand) {
        if (hand[0] == hand[1]) {
            return (hand[0] == hand[2] && hand[0] == hand[3]) || (hand[0] == hand[2] && hand[0] == hand[4]) || (hand[0] == hand[3] && hand[0] == hand[4]);
        } else {
            return (hand[0] == hand[2] && hand[0] == hand[3] && hand[0] == hand[4]) || (hand[1] == hand[2] && hand[1] == hand[3] && hand[1] == hand[4]);
        }
    }

    public static boolean isFullHouse (char[] hand) {
        char first = hand[0];
        char second = '0';
        for (int i = 1; i < hand.length; i++) {
            if (hand[i] != first) {
                second = hand[i];
                break;
            }
        }

        int counter1 = 1;
        int counter2 = 0;

        for (int i = 1; i < hand.length; i++) {
            if (hand[i] == first) {
                counter1++;
            } else if (hand[i] == second) {
                counter2++;
            }
        }
        return (counter1 == 3 && counter2 == 2) || (counter1 == 2 && counter2 == 3);
    }

    public static boolean isThreeOfAKind (char[] hand) {
        char first = hand[0];
        char second = '0';
        char third = '0';

        int secondIndex = 0;
        for (int i = 1; i < hand.length; i++) {
            if (hand[i] != first) {
                second = hand[i];
                secondIndex = i;
                break;
            }
        }

        for (int i = secondIndex + 1; i < hand.length; i++) {
            if (hand[i] != first && hand[i] != second) {
                third = hand[i];
                break;
            }
        }

        int counter1 = 1;
        int counter2 = 0;
        int counter3 = 0;

        for (int i = 1; i < hand.length; i++) {
            if (hand[i] == first) {
                counter1++;
            } else if (hand[i] == second) {
                counter2++;
            } else if (hand[i] == third) {
                counter3++;
            }
        }
        return counter1 == 3 || counter2 == 3 || counter3 == 3;
    }

    public static boolean isTwoPair (char[] hand) {
        char first = hand[0];
        char second = '0';
        char third = '0';

        int secondIndex = 0;
        for (int i = 1; i < hand.length; i++) {
            if (hand[i] != first) {
                second = hand[i];
                secondIndex = i;
                break;
            }
        }

        for (int i = secondIndex + 1; i < hand.length; i++) {
            if (hand[i] != first && hand[i] != second) {
                third = hand[i];
                break;
            }
        }

        int counter1 = 1;
        int counter2 = 0;
        int counter3 = 0;

        for (int i = 1; i < hand.length; i++) {
            if (hand[i] == first) {
                counter1++;
            } else if (hand[i] == second) {
                counter2++;
            } else if (hand[i] == third) {
                counter3++;
            }
        }
        return (counter1 == 2 && counter2 == 2) || (counter1 == 2 && counter3 == 2) || (counter2 == 2 && counter3 == 2);
    }

    public static boolean isOnePair (char[] hand) {
        char first = hand[0];
        char second = '0';
        char third = '0';
        char fourth = '0';

        if (hand[1] == first) {
            return true;
        } else {
            second = hand[1];
        }

        if (hand[2] == first || hand[2] == second) {
            return true;
        } else {
            third = hand[2];
        }

        if (hand[3] == first || hand[3] == second || hand[3] == third) {
            return true;
        } else {
            fourth = hand[3];
        }

        return hand[4] == first || hand[4] == second || hand[4] == third || hand[4] == fourth;
    }

    public static int[][] getAllValues (List<CamelCard> camelCards) {
        int[][] values = new int[camelCards.size()][3];
        for (int i = 0; i < camelCards.size(); i++) {
            values[i][0] = getHandValue(camelCards.get(i))[0];
            values[i][1] = getHandValue(camelCards.get(i))[1];
            values[i][2] = camelCards.get(i).bid;
        }
        return values;
    }
    public static void mergeSort(int[][] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[][] l = new int[mid][3];
        int[][] r = new int[n - mid][3];

        for (int i = 0; i < mid; i++) {
            l[i][0] = a[i][0];
            l[i][1] = a[i][1];
            l[i][2] = a[i][2];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid][0] = a[i][0];
            r[i - mid][1] = a[i][1];
            r[i - mid][2] = a[i][2];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(
            int[][] a, int[][] l, int[][] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i][0] < r[j][0]) {
                a[k++] = l[i++];
            } else if (l[i][0] == r[j][0] && l[i][1] <= r[j][1]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static int computeTotalWinnings (int[][] sortedValues) {
        int totalWinnings = 0;
        for (int i = 0; i < sortedValues.length; i++) {
            totalWinnings += sortedValues[i][2] * (i + 1);
        }
        return totalWinnings;
    }

}