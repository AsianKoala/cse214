/**
 * The {@link PersonAlreadyExistsException} class is a custom Exception class.
 * This is thrown whenever a new Person is input into the {@link PersonDataManager} class, if that person
 * already exists within the manager
 */
class PersonAlreadyExistsException extends Exception {
    /**
     * Create an instance of {@link PersonAlreadyExistsException} with no message
     */
    public PersonAlreadyExistsException() {}

    /**
     * Create an instance of {@link PersonAlreadyExistsException} with a message
     * @param message The exception message
     */
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}