/**
 * The {@link PersonAlreadyExistsException} class is a custom Exception class.
 * This is thrown whenever a person is queried from the {@link PersonDataManager} class, if that person
 * does not exist within the manager.
 */
class PersonDoesNotExistException extends Exception {
    /**
     * Create an instance of {@link PersonDoesNotExistException}
     */
    public PersonDoesNotExistException() {}

    /**
     * Create an instance of {@link PersonDoesNotExistException} with a message
     * @param message The exception message
     */
    public PersonDoesNotExistException(String message) {
        super(message);
    }
}