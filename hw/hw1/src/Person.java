import java.util.regex.Pattern;

/**
 * The Person class represents the biological data of a person
 */
public class Person {
    private int age;
    private double height;
    private double weight;
    private String name;
    private String gender;
    private int feet;
    private double inches;

    /**
     * Creates an instance of {@link Person}
     * @param age       Numeric age of this person, in years
     * @param height    Height of this person, in inches (in)
     * @param weight    Weight of this person, in pounds (lbs)
     * @param name      Name of this person
     * @param gender    Gender of this person (M or F)
     */
    public Person(int age, double height, double weight, String name, String gender) {
        this.age = age;
        this.height = height;
        this.feet = (int)height / 12;
        this.inches = height % 12.0;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
    }

    /**
     * Creates a default instance of {@link Person}
     */
    public Person() {
        this.age = 0;
        this.height = 0;
        this.feet = 0;
        this.inches = 0;
        this.weight = 0;
        this.name = "";
        this.gender = "M"; // we will set gender to "M" by default to ensure data validity
    }

    /**
     * Returns the age of this person, in years
     * @return  This person's age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the height of this person, in inches
     * @return  This person's height in inches
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the weight of this person, in pounds
     * @return  This person's weight in pounds
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the name of this person
     * @return  This person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the gender of this person (M or F)
     * @return  This person's gender (M or F)
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns a sentence that details the person, with all data cleanly formatted into a sentence.
     * This is primarily used in {@link PersonDataManager#getPerson(String)}.
     * @return Cleanly formatted sentence containing information for this person.
     */
    public String getInfoString() {
        return String.format("%s is a %s year old male who is %s feet and %s inches tall and weighs %s pounds.", name, age, feet, (int)inches, (int)weight);
    }

    /**
     * Sets the age of this person to a specified integer
     * @param age The new age of this person, in years
     * @throws IllegalArgumentException Thrown if an age <= 0 is set
     */
    public void setAge(int age) throws IllegalArgumentException {
        if(age <= 0) {
            throw new IllegalArgumentException("Age cannot be less than or equal to 0.");
        }
        this.age = age;
    }

    /**
     * Sets the height of this person to a specified number
     * @param height The new height of this person, in inches
     * @throws IllegalArgumentException Thrown if height <= 0 is set
     */
    public void setHeight(double height) throws IllegalArgumentException {
        if(height <= 0) {
            throw new IllegalArgumentException("Height cannot be less than or equal to 0.");
        }
        this.height = height;
        this.feet = (int)height / 12;
        this.inches = height % 12.0;
    }

    /**
     * Sets the weight of this person to a specified number
     * @param weight The new weight of this person, in pounds
     * @throws IllegalArgumentException Thrown if weight <= 0 is set
     */
    public void setWeight(double weight) throws IllegalArgumentException {
        if(weight <= 0) {
            throw new IllegalArgumentException("Height cannot be less than or equal to 0.");
        }
        this.weight = weight;
    }

    /**
     * Sets the name of this person to a specific string
     * @param name The new name of this person
     * @throws IllegalArgumentException Thrown if an invalid name is set. A name is considered invalid if it does not consist solely of letters
     */
    public void setName(String name) throws IllegalArgumentException {
        if(name.length() == 0) {
            throw new IllegalArgumentException("Name length must be at least 1.");
        } else if(!Pattern.matches("[a-zA-Z]+", name)) {
            throw new IllegalArgumentException("Invalid name string, must only be alphabet characters.");
        }
        this.name = name;
    }

    /**
     * Sets the gender of this person to Male or Female
     * @param gender The new gender of this person
     * @throws IllegalArgumentException Thrown if the set gender is not "M" or "F"
     */
    public void setGender(String gender) throws IllegalArgumentException {
        if(!Pattern.matches("[MF]", gender)) {
            throw new IllegalArgumentException("Invalid gender string, must be either 'M' or 'F'.");
        }
        this.gender = gender;
    }

    /**
     * Returns a tabular form of this person's biological data
     * @return Tabular form of this person
     */
    @Override
    public String toString() {
        String formatStr = "%5s %5s %5s %5s %10s %5s %20s %5s %15s pounds %n";
        String heightStr = String.format("%s feet %s inches", feet, (int)inches);
        return String.format(formatStr, name, "|", age, "|", gender, "|", heightStr, "|", (int)weight);
    }
}