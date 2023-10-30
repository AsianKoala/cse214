import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The {@link PersonDataManager} class exists for the user to easily write and read from CSV files
 * containing biological data about people. It supports numerous actions on the Person array, such as adding new people,
 * removing people, and printing a person's biological information.
 * The {@link PersonDataManager} class also can write the current array to a new CSV file.
 */
class PersonDataManager {
    // In case the user wishes to make data records before CSV is imported, we initialize the array with size 0
    private Person[] people = new Person[0];
    private int numPeople = 0;

    /**
     * Updates the length of the {@link PersonDataManager#people} if necessary
     */
    private void updateArrSize() {
        // If the number of people have reached the array capacity, double the capacity
        if(numPeople == people.length) {
            Person[] copy = new Person[2 * people.length + 1];
            System.arraycopy(people, 0, copy, 0, numPeople);
            people = copy;
        }
    }

    /**
     * Checks if a certain name is a valid name.
     * This is used when parsing CSV data and reading user inputs.
     * A valid name consists of only alphabet letters.
     * @param name The queried name
     * @return Whether the name is considered valid
     */
    public static boolean isInvalidName(String name) {
        return !Pattern.matches("[a-zA-Z]+", name);
    }

    /**
     * Checks if a certain gender is a valid gender.
     * This is used when parsing CSV data and reading user inputs.
     * A valid gender is only one character, and is either "M" or "F".
     * @param gender The queried gender
     * @return Whether the gender is considered valid
     */
    public static boolean isInvalidGender(String gender) {
        return !Pattern.matches("[MF]", gender);
    }

    /**
     * Checks if a certain string is a valid integer.
     * This is used when parsing CSV data and reading user inputs.
     * A valid integer consists of only numeric characters from 0-9
     * @param num The queried string
     * @return Whether the string is considered valid
     */
    public static boolean isInvalidInt(String num) {
        return !Pattern.matches("[0-9]+", num);
    }

    /**
     * Checks if a certain string is a valid double
     * This is used when parsing CSV data and reading user inputs.
     * A valid double consists of only numeric characters 0-9, a possible decimal point,
     * and more possible numeric characters 0-9 after the decimal point.
     * @param num The queried string
     * @return Whether the string is considered valid
     */
    public static boolean isInvalidDouble(String num) {
        return !Pattern.matches("[0-9]+\\.?[0-9]*", num);
    }

    /**
     * Builds a {@link PersonDataManager} from a CSV file
     * @param location Path to the CSV file
     * @return A {@link PersonDataManager} filled with data from the specified CSV file.
     * @throws IllegalArgumentException Thrown if any of the data in the CSV file is considered invalid.
     * See the "isInvalid" methods in the {@link PersonDataManager}
     * @throws IOException Thrown if the specified path is not accessible or invalid
     */
    public static Person[] buildFromFile(String location) throws IllegalArgumentException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(location));

        // Count all the lines in the CSV file
        int peopleCount = -1; // Instantiate to -1 to account for header line
        while(br.readLine() != null) {
            peopleCount++;
        }
        br = new BufferedReader(new FileReader(location));

        // Create the people array with the size of the just counted file
        Person[] people = new Person[peopleCount];

        // Process the first, header line of the CSV
        String line = br.readLine();

        // Read through the entire CSV file
        int counter = 0;
        while((line = br.readLine()) != null) {
            String[] data = line.split(",");
            for(int i = 0; i < data.length; i++) {
                // "Clean" the data string by removing all quotes and unnecessary whitespace
                data[i] = data[i].replace(" ", "").replace("\"", "");

                // Verify that each biological data component is valid
                if(i == 0 && isInvalidName(data[i])) {
                    br.close();
                    throw new IllegalArgumentException("Invalid name in CSV File: " + data[i]);
                } else if(i == 1 && isInvalidGender(data[i])) {
                    br.close();
                    throw new IllegalArgumentException("Invalid gender in CSV File: " + data[i]);
                } else if(i == 2 && isInvalidInt(data[i])) {
                    br.close();
                    throw new IllegalArgumentException("Invalid age in CSV File: " + data[i]);
                } else if(i == 3 && isInvalidDouble(data[i])) {
                    br.close();
                    throw new IllegalArgumentException("Invalid height in CSV File: " + data[i]);
                } else if(i == 4 && isInvalidDouble(data[i])) {
                    br.close();
                    throw new IllegalArgumentException("Invalid weight in CSV File: " + data[i]);
                }
            }
            String name = data[0], gender = data[1];

            // Convert the data values to proper numeric values (int and doubles)
            int age = Integer.parseInt(data[2]);
            double height = Double.parseDouble(data[3]);
            double weight = Double.parseDouble(data[4]);

            // Add the person to the array
            people[counter] = new Person(age, height, weight, name, gender);
            counter++;
        }
        br.close();
        return people;
    }

    /**
     * Used in combination with {@link PersonDataManager#buildFromFile(String)} to initialize a PersonDataManager
     * with data from a CSV file.
     * @param newPeople The new people[] array to be set
     */
    public void setPeople(Person[] newPeople) {
        people = newPeople;
        numPeople = newPeople.length;
    }

    /**
     * Add a person to the {@link PersonDataManager#people} array, in alphabetical order.
     * Updates the array size if filled to max capacity.
     * Finds the alphabetical position of the person through lexicographical comparison.
     * Upon finding the index, shift everything to the right of the index down 1 position.
     * @param newPerson The new {@link Person} to be added
     * @throws PersonAlreadyExistsException Thrown if there is already a {@link Person} in the array with the same name
     */
    public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
        for(int i = 0; i < numPeople; i++) {
            if(people[i].getName().equals(newPerson.getName())) {
                throw new PersonAlreadyExistsException("Person " + newPerson.getName() + " already exists.");
            }
        }
        updateArrSize();
        // If there is no one in the array, just assign the first position to newPerson
        if(numPeople == 0) {
            people[0] = newPerson;
        } else if(newPerson.getName().compareTo(people[numPeople - 1].getName()) > 0) {
            // This is the edge-case when the newPerson is the greatest lexicographical value in the array
            people[numPeople] = newPerson;
        } else {
            // Find the lexicographical position in the array
            for(int i = 0; i < numPeople; i++) {
                if(newPerson.getName().compareTo(people[i].getName()) < 0) {
                    // Upon finding it, shift everything to the right down one position
                    for(int j = numPeople; j > i; j--) {
                        people[j] = people[j - 1];
                    }
                    // Insert the newPerson into this index
                    people[i] = newPerson;
                    break;
                }
            }
        }
        numPeople++;
    }

    /**
     * Prints a {@link Person}'s biological data, if they exist within the {@link PersonDataManager#people} array
     * @param name The queried name
     * @throws PersonDoesNotExistException Thrown if there is no person with the queried name
     */
    public void getPerson(String name) throws PersonDoesNotExistException {
        for(int i = 0; i < numPeople; i++) {
            if(people[i].getName().equals(name)) {
                System.out.println(people[i].getInfoString());
                return;
            }
        }
        throw new PersonDoesNotExistException("Person " + name + " does not exist.");
    }

    /**
     * Removes a Person from the {@link PersonDataManager#people} array.
     * To maintain inserted order, we copy all elements back to the {@link PersonDataManager#people} array with the
     * queried person removed. Typically, remove() would swap the removed person with the last person, but this approach
     * still has time complexity O(N), so this remove() implementation instead.
     * @param name The name of the to-be-removed person
     * @throws PersonDoesNotExistException Thrown of the queried person does not exist within the array
     */
    public void removePerson(String name) throws PersonDoesNotExistException {
        for(int i = 0; i < numPeople; i++) {
            if(people[i].getName().equals(name)) {
                people[i] = null;

                // Copy all the people to a new array
                Person[] copy = new Person[people.length];

                // Utilize a "counter" to figure out the target position in the new array
                // This is needed since we skip over one value (the newly removed Person)
                int cnt = 0;
                for(int j = 0; j < numPeople; j++) {
                    if(people[j] != null) {
                        copy[cnt] = people[j];
                        cnt++;
                    }
                }

                // Assign the copied array to the current array
                people = copy;
                numPeople--;
                return;
            }
        }
        throw new PersonDoesNotExistException("Person " + name + " does not exist.");
    }

    /**
     * Prints all people within the {@link PersonDataManager#people} array, in a cleanly formatted table.
     */
    public void printTable() {
        System.out.format("%5s %5s %5s %5s %10s %5s %20s %5s %15s %n", "Name", "|", "Age", "|", "Gender", "|", "Height", "|", "Weight");

        // Build the "bar" underneath the header
        String bar = "";
        for(int i = 0; i < 100; i++) {
            bar += "=";
        }
        System.out.format("%s %n", bar);

        for(int i = 0; i < numPeople; i++) {
            System.out.print(people[i].toString());
        }
    }

    /**
     * Writes all current data to a CSV file
     * @param location The location of the CSV file
     * @throws IOException Thrown if that location cannot be accessed (e.g. insufficient write access or invalid location)
     */
    public void saveToFile(String location) throws IOException {
        File csv = new File(location);
        FileWriter writer = new FileWriter(csv);

        // Print the header
        writer.write("\"Name\",\"Sex\",\"Age\",\"Height (in)\",\"Weight (lbs)\"\n");

        for(int i = 0; i < numPeople; i++) {
            StringBuilder line = new StringBuilder();
            String[] data = {people[i].getName(), people[i].getGender(), "" + people[i].getAge(), "" + (int)people[i].getHeight(), "" + (int)people[i].getWeight()};
            for(int j = 0; j < 5; j++) {
                // Only need to add quotation marks around the String values (indices 0 and 1)
                if(j < 2) {
                    line.append("\"");
                }

                line.append(data[j]);

                // Only need to add quotation marks around the String values (indices 0 and 1)
                if(j < 2) {
                    line.append("\"");
                }

                // Add comma if the current index is not the last index
                if(j != 4) {
                    line.append(",");
                }
            }
            line.append("\n");
            writer.write(line.toString());
        }
        writer.close();
    }
}