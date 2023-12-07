/**
 * The {@link Person} class represents a person standing in line
 */
public class Person {
    private String name;
    private int seatNumber;
    private Person nextPerson;

    /**
     * Creates a default instance of {@link Person}
     */
    public Person() {
        name = "";
        seatNumber = 0;
    }

    /**
     * Creates an instance of {@link Person} with specified parameters
     * @param name          The name of this person
     * @param seatNumber    The seat number of this person
     */
    public Person(String name, int seatNumber) {
        this.name = name;
        this.seatNumber = seatNumber;
    }

    /**
     * Creates an instance of {@link Person} with the next person in line specified
     * @param name          The name of this person
     * @param seatNumber    The seat number of this person
     * @param nextPerson    The next person in the same line of this person
     */
    public Person(String name, int seatNumber, Person nextPerson) {
        this.name = name;
        this.seatNumber = seatNumber;
        this.nextPerson = nextPerson;
    }

    /**
     * Returns the name of this person
     * @return  This person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the seat number of this person
     * @return  This person's seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Returns the next person in line
     * @return  A {@link Person} reference to the next person in line
     */
    public Person getNextPerson() {
        return nextPerson;
    }

    /**
     * Sets the name of this person to a specified string
     * @param name  The new name of this person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the seat number of this person to a specified integer
     * @param seatNumber                    The new seat number of this person
     * @throws IllegalArgumentException     Thrown if the new seat number is <= 0
     */
    public void setSeatNumber(int seatNumber) throws IllegalArgumentException {
        if(seatNumber <= 0) {
            throw new IllegalArgumentException("Error: A seat number cannot be <= 0");
        }
        this.seatNumber = seatNumber;
    }

    /**
     * Sets the reference to the next person in line to a specified {@link Person}
     * @param nextPerson    The new reference to the next person in line
     */
    public void setNextPerson(Person nextPerson) {
        this.nextPerson = nextPerson;
    }
}
