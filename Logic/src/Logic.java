
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * by West2Coast Originally Started April - May 2016; Restarted August 2017
 *
 */
public class Logic {

    static Map<Integer, ArrayList<?>> mapRows = new TreeMap<Integer, ArrayList<?>>();
    static Map<Integer, ArrayList<?>> mapFaceLocations = new TreeMap<Integer, ArrayList<?>>();
    static Map<Integer, Integer> preFillMap = new HashMap<Integer, Integer>();
    private static Scanner input;
    private static int changeINDEX;

    public static void main(String[] args) {
       	fillMap();
		mapRows();
		mapFaceLocations();
		printRowsFaces();
		// printRowFaceLocations();
		swapRows2(4);
		printRowsFaces();
		// swapRows(6, 1);
		swapColumns(1, 2, 4);
    }

    public static void mapRows() {
        System.out.println("Enter a 150 character string: ");
        String sentence = "HewentsuchdaregoodmrfactThesmallownsevensavedmanageqnoofferSuspiciondidmrsnorfurnituresmallnessScalewholedownsoftenleavenoteatAnexpressionreasonablyit";
        input = new Scanner(System.in);
        // input.nextLine();
        int key = 0, location = 0, rows = 30;

        for (int x = 0; x < rows; x++) { // rows = 150 characters
            ArrayList<Character> rowValues = new ArrayList<>();
            for (int y = 0; y < 5; y++) {
                rowValues.add(sentence.charAt(location));
                location++;
            }
            mapRows.put(key++, rowValues);
        }
    }

    public static void fillMap() {
        preFillMap.put(0, 5);
        preFillMap.put(1, 6);
        preFillMap.put(2, 7);
        preFillMap.put(3, 8);
        preFillMap.put(4, 9);
        preFillMap.put(10, 15);
        preFillMap.put(11, 16);
        preFillMap.put(12, 17);
        preFillMap.put(13, 18);
        preFillMap.put(14, 19);

    }

    static int FaceRowTurn(int Face, int row) {
        int rowTrue = 0;
        // row %= 30;
        if (Face == 1) { // 1 or 2 or 3 or 4
            while (row >= 20 || row < 0) {
                if (row > 19) { // greater than 20 do divisible by 20
                    // **Encryption
                    row %= 20;
                } else if (row < 0) { // less than 0 add to 20 to get new number
                    // **Decryption
                    row = 20 + row;
                }
            }

            if ((row < 5) || ((row > 4) && (row < 10)) || ((row > 9) && (row < 15)) || ((row > 14) && (row < 20))) {
                rowTrue = 1;
                changeINDEX = row;

            } else {
                rowTrue = 0;
            }
        } else if (Face == 2) { // 5 or 6
            while (row > 29 || row < 0 || row < 5 || ((row > 9) && (row < 15))) { // greater
                // than
                if (row > 29) {
                    row %= 30;
                } else if ((row >= 0 && row < 5) || ((row > 9) && (row < 15))) {
                    row = preFillMap.get(row);
                } else if (row < 0) {
                    row = 30 + row;
                }
            }
            if (((row > 4) && (row < 10)) || ((row > 14) && (row < 20)) || ((row > 19) && (row < 25))
                    || ((row > 24) && (row < 30))) { // Note for checking if
                // it's rotating top or
                // bottom 5 is 20 top &
                // 24 bottom
                rowTrue = 1;
                changeINDEX = row;
            } else {
                rowTrue = 0;
            }
        }
        return rowTrue;
    }

    public static void mapFaceLocations() {
        int key = 1, location = 1, rows = 30;

        for (int x = 0; x < rows; x++) { // 30 rows = 150 characters
            ArrayList<Integer> faceLocations = new ArrayList();
            for (int y = 0; y < 5; y++) {
                faceLocations.add(location);
                location++;
            }
            mapFaceLocations.put(key++, faceLocations);
        }
    }

    public static void printRowsFaces() {
        int count = 0, faceNumber = 1;
        for (int index = 0; index < 30; index++) {

            if (count != 5) {
                System.out.println(mapRows.get(index));

            } else {
                System.out.println(faceNumber++ + "\n- - - - - - - - - - - - - - -");
                System.out.println(mapRows.get(index));
                count = 0;
            }
            count++;

        }
        System.out.println(faceNumber++ + "\n- - - - - - - - - - - - - - -");
    }

    public static void printRowFaceLocations() {
        int count = 0, faceNumber = 1;
        for (int index = 1; index <= 30; index++) {

            if (count != 5) {
                System.out.println(mapFaceLocations.get(index));

            } else {
                System.out.println(faceNumber++ + "\n- - - - - - - - - - - - - - -");
                System.out.println(mapFaceLocations.get(index));
                count = 0;
            }
            count++;

        }
        System.out.println(faceNumber++ + "\n- - - - - - - - - - - - - - -");
    }

    public static void face(boolean face, int index) {
        int row = 0;
        while (face != true) {
            row = FaceRowTurn(1, index);
            if (row == 1) {
                face = true;
            } else {
                face = false;
                index += 5;
            }

        }
        // changeINDEX = index;
    }

    public static void swapRows2(int index) {
        int newIndex = index;
        List<Character> tempStoreRowReplaced = new ArrayList<Character>(), rowToMove = new ArrayList<Character>();

        for (int x = 0; x < 5; x++) {
            boolean face = false;
            face(face, newIndex);
            index = changeINDEX;
            if (x == 0) {

                rowToMove = new ArrayList<Character>((Collection<? extends Character>) mapRows.get(index));
                face = false;
                newIndex = changeINDEX + 5;
                System.out.println(newIndex);
                face(face, newIndex);
                newIndex = changeINDEX;
                System.out.println(index + " " + newIndex);
                tempStoreRowReplaced = new ArrayList<Character>(
                        (Collection<? extends Character>) mapRows.get(newIndex));
                mapRows.replace(newIndex, (ArrayList<?>) rowToMove);
            } else {
                System.out.println(index + " " + newIndex);
                rowToMove = new ArrayList<Character>(tempStoreRowReplaced);
                newIndex += 5;
                face(face, newIndex);
                newIndex = changeINDEX;
                tempStoreRowReplaced.clear();
                tempStoreRowReplaced = new ArrayList<Character>(
                        (Collection<? extends Character>) mapRows.get(newIndex));
                mapRows.replace(newIndex, (ArrayList<?>) rowToMove);
            }
        }

    }

    public static void insert(int num1, ArrayList<Character> List) {
        mapRows.put(num1, List);
        System.out.println(mapRows.get(1));
        System.out.println(mapRows.get(2));
    }

    public static void swapColumns(int number1, int number2, int index) {
        ArrayList<Character> list = (ArrayList<Character>) mapRows.get(number1);
        ArrayList<Character> list2 = (ArrayList<Character>) mapRows.get(number2);
        Character a = list.get(index), b = list2.get(index), c = list.get(index), d = list.get(index),
                e = list.get(index);
        list.set(index, b);
        list2.set(index, a);
        // insert(number1, number2, list, list2);
    }

}
