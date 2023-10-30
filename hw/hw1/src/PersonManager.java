import java.io.IOException;
import java.util.Scanner;

/**
 * Runs the interaction between the user and the PersonDataManager class.
 * Inputs must first be entered as specified by the menu.
 * Depending on the menu selection, the user may have to input more data.
 * If any data input is invalid, the program will return back to the main menu.
 */
public class PersonManager {
    private static PersonDataManager personDataManager;
    private static Scanner scan;

    /**
     * Prints the menu to stdout
     */
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("\t(I) - Import from File");
        System.out.println("\t(A) - Add Person");
        System.out.println("\t(R) - Remove Person");
        System.out.println("\t(G) - Get Information on Person");
        System.out.println("\t(P) - Print Table");
        System.out.println("\t(S) - Save to File");
        System.out.println("\t(Q) - Quit");
        System.out.println("--------------------------------------------");
    }

    /**
     * Prints an error message for invalid input.
     */
    private static void printInvalidInput() {
        System.out.println("The input you entered is incorrect. Please try again!");
    }

    /**
     * Handles the "Import" menu action
     * Returns early if there was an error with the inputted CSV location.
     */
    private static void handleImport() {
        System.out.print("Please enter a location: ");
        String location = scan.next().strip();
        try {
            Person[] people = PersonDataManager.buildFromFile(location);
            personDataManager = new PersonDataManager();
            personDataManager.setPeople(people);
            System.out.println("Person data loaded successfully!");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Add" menu action
     * Returns early if there was an invalid input.
     */
    private static void handleAdd() {
        String name, gender, age, height, weight;

        // Accepts input for the person's name
        System.out.print("Please enter the name of the person: ");
        name = scan.next().strip();
        if(PersonDataManager.isInvalidName(name)) {
            printInvalidInput();
            return;
        }

        // Accepts input for the person's age
        System.out.print("Please enter the age: ");
        age = scan.next().strip();
        if(PersonDataManager.isInvalidInt(age)) {
            printInvalidInput();
            return;
        }

        // Accepts input for the person's gender
        System.out.print("Please enter the gender (M or F): ");
        gender = scan.next().strip();
        if(PersonDataManager.isInvalidGender(gender)) {
            printInvalidInput();
            return;
        }

        // Accepts input for the person's height
        System.out.print("Please enter the height (in inches): ");
        height = scan.next().strip();
        if(PersonDataManager.isInvalidDouble(height)) {
            printInvalidInput();
            return;
        }

        // Accepts input for the person's weight
        System.out.print("Please enter the weight (in lbs): ");
        weight = scan.next().strip();
        if(PersonDataManager.isInvalidDouble(weight)) {
            printInvalidInput();
            return;
        }

        try {
            personDataManager.addPerson(new Person(Integer.parseInt(age), Double.parseDouble(height), Double.parseDouble(weight), name, gender));
        } catch(PersonAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Remove" menu action.
     * Returns early if the specified person does not exist within {@link PersonManager#personDataManager}
     */
    private static void handleRemove() {
        System.out.print("Please enter the name of the person: ");
        String removeName = scan.next().strip();
        if(PersonDataManager.isInvalidName(removeName)) {
            printInvalidInput();
            return;
        }

        try {
            personDataManager.removePerson(removeName);
            System.out.println(removeName + " has been removed!");
        } catch(PersonDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Get" menu action.
     * Returns early if the specified person does not exist within {@link PersonManager#personDataManager}
     */
    private static void handleGet() {
        System.out.print("Please enter the name of the person: ");
        String getName = scan.next().strip();
        if(PersonDataManager.isInvalidName(getName)) {
            printInvalidInput();
            return;
        }

        try {
            personDataManager.getPerson(getName);
        } catch(PersonDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the "Save" menu action.
     * Returns early if there was an error with saving to that specific location
     */
    private static void handleSave() {
        System.out.print("Please select name for the file: ");
        String fname = scan.next();
        try {
            personDataManager.saveToFile(fname);
        } catch(IOException e) {
            System.out.println("File " + fname + " is inaccessible.");
        }
    }

    /**
     * Runs the default interaction menu
     * @return Whether the program should quit, specified by "Q" user input
     */
    private static boolean runMenu() {
        printMenu();
        System.out.print("Please select an option: ");
        String lastInput = scan.next().strip();
        switch(lastInput) {
            case "I":
                handleImport();
                break;
            case "A":
                handleAdd();
                break;
            case "R":
                handleRemove();
                break;
            case "G":
                handleGet();
                break;
            case "P":
                personDataManager.printTable();
                break;
            case "S":
                handleSave();
                break;
            case "Q":
                return false;
            default:
                System.out.println("Invalid Input");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        personDataManager = new PersonDataManager();
        scan = new Scanner(System.in);
        System.out.println("Starting...");
        boolean isRunning = true;
        // Continues to call runMenu() until "Q" has been inputted
        while(isRunning) {
            isRunning = runMenu();
        }
        System.out.println("Sorry to see you go!");
    }
}
