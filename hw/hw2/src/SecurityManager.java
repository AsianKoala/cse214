import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Runs the interaction between the user and the {@link SecurityCheck} class
 */
public class SecurityManager {
    private static SecurityCheck securityCheck;
    private static Scanner s;

    // Prints the menu to the user in a nice, readable format
    private static void printMenu() {
        System.out.println();
        securityCheck.printLineCounts();
        System.out.println();
        System.out.println("Menu:");
        System.out.println("\t(A) - Add Person");
        System.out.println("\t(N) - Next Person");
        System.out.println("\t(R) - Remove Lines");
        System.out.println("\t(L) - Add Lines");
        System.out.println("\t(P) - Print All Lines");
        System.out.println("\t(Q) - Quit");
        System.out.println();
        System.out.print("Please select an option: ");
    }

    /**
     * Uses regex to determine if a given string is an invalid number
     * This is used when validating given seat numbers
     * @param str   The queried string
     * @return      Whether or not the string is a valid integer
     */
    private static boolean isInvalidInt(String str) {
        return !Pattern.matches("[0-9]+", str);
    }

    /**
     * Handles the "Add Person" menu action
     * Returns early if there was an invalid operation
     */
    private static void handleAdd() {
        System.out.print("Please enter a name: ");
        String name = s.next();
        System.out.print("Please enter a seat number: ");
        String seatStr = s.next();

        // If the input string is invalid, return early
        if(isInvalidInt(seatStr)) {
            System.out.println("The input you entered is incorrect: Input is not a positive integer. Please try again!");
            return;
        }
        int seat = Integer.parseInt(seatStr);
        try {
            securityCheck.addPerson(name, seat);
        } catch(TakenSeatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Remove Next Person" menu action
     * Returns early of there was an invalid operation
     */
    private static void handleNext() {
        try {
            Person front = securityCheck.removeNextAttendee();
            int lineNumber = securityCheck.getLastRemovedPersonLine();
            System.out.println("Loading...");
            System.out.println(front.getName() + " from seat " + front.getSeatNumber() + " removed from line " + lineNumber + "!");
        } catch(AllLinesEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Remove Lines" menu action
     * Returns early of there was an invalid operation
     */
    private static void handleRemove() {
        System.out.print("Lines to remove: ");
        String input = s.next();
        String[] numbers = input.split(",");
        int[] parsed = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            parsed[i] = Integer.parseInt(numbers[i]);
        }
        try {
            securityCheck.removeLines(parsed);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Add Lines" menu action
     * Returns early of there was an invalid operation
     */
    private static void handleLines() {
        System.out.print("Add how many more lines?: ");
        String input = s.next();
        if(isInvalidInt(input)) {
            System.out.println("The input you entered is incorrect: Input is not a positive integer. Please try again!");
            return;
        }
        int newLines = Integer.parseInt(input);
        int oldLines = securityCheck.getLineCount();
        try {
            securityCheck.addNewLines(newLines);

            // Print the proper success message
            // Ensures proper grammar is used, so a somewhat longer implementation
            if(newLines == 1) {
                System.out.println("Line " + (oldLines + 1) + " introduced!");
            } else if(newLines == 2) {
                System.out.println("Lines " + (oldLines + 1) + " and " + (oldLines + 2) + " introduced!");
            } else {
                System.out.print("Lines ");
                for(int i = 1; i <= newLines; i++) {
                    int currLine = oldLines + i;
                    System.out.print(currLine);
                    if(i + 1 <= newLines) {
                        System.out.print(", ");
                    }
                }
                System.out.print(" introduced!\n");
            }
        } catch(InvalidLineCountException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Simple prints the {@link SecurityCheck} in tabular format
     */
    private static void handlePrint() {
        securityCheck.printAllLines();
    }

    /**
     * The "main" function that runs the interaction between the user and the program
     * @return  If the user has input a "quit"
     */
    private static boolean runMenu() {
        printMenu();
        String input = s.next().trim();
        switch(input) {
            case "A":
                handleAdd();
                break;
            case "N":
                handleNext();
                break;
            case "R":
                handleRemove();
                break;
            case "L":
                handleLines();
                break;
            case "P":
                handlePrint();
                break;
            case "Q":
                return false;
            default:
                System.out.println("The input you entered is incorrect. Please try again!");
        }
        return true;
    }

    public static void main(String[] args) {
        securityCheck = new SecurityCheck();
        s = new Scanner(System.in).useDelimiter("\\n");
        System.out.println("Starting...");
        boolean isRunning = true;
        while(isRunning) {
            isRunning = runMenu();
        }
        System.out.println("Sorry to see you go!");
    }
}
