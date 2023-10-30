/**
 * The SecurityCheck class represents a number of {@link Line}s.
 * This class is essentially a Singly-Linked List.
 */
public class SecurityCheck {
    private Line headLine;
    private Line tailLine;
    private Line cursorLine;
    private int lineCount;
    private int lastRemovedPersonLine;
    private int cursorPos;
    // The default padding is used for
    private final int defaultPadding = 25;

    /**
     * Creates a default instance of {@link SecurityCheck}.
     * Initializes the list to have a single {@link Line}
     */
    public SecurityCheck() {
        headLine = new Line();
        tailLine = headLine;
        cursorLine = headLine;
        lineCount = 1;
        cursorPos = 1;
        lastRemovedPersonLine = -1;
    }

    public void addPerson(String name, int seatNumber) throws TakenSeatException {
        // Check if the person's seat number is found in any line
        Line cursor = headLine;
        while(cursor != null) {
            Person pCursor = cursor.getHeadPerson();
            while(pCursor != null) {
                // If a duplicate seat number was found, throw a TakenSeatException
                if(pCursor.getSeatNumber() == seatNumber) {
                    throw new TakenSeatException("The input you entered is incorrect: Seat " + seatNumber + " already taken. Please try again!");
                }
                pCursor = pCursor.getNextPerson();
            }
            cursor = cursor.getLineLink();
        }

        // Now that we have checked that seatNumber doesn't exist in any of the lines
        // We can add the person to the cursor line
        Person toAdd = new Person(name, seatNumber);
        cursorLine.addPerson(toAdd);

        System.out.println("\nLoading...");
        System.out.println(name + " successfully added to line " + cursorPos);

        // Adjust the position of the cursor to ensure Condition 2 is met
        fixCursor();
    }

    /**
     * Removes the person with the lowest seat number while ensuring max(difference) over all lines <= 1
     * @return  The {@link Person} who was removed
     * @throws AllLinesEmptyException   Thrown if all lines have length 0
     */
    public Person removeNextAttendee() throws AllLinesEmptyException {
        // Check if all lines have length 0 first
        Line cursor = headLine;
        Line temp = cursor; // This temp Line saves the first Line with a length > 0
        boolean existsValidLength = false;
        while(cursor != null) {
            if(cursor.getLength() > 0) {
                temp = cursor;
                existsValidLength = true;
                break;
            }
            cursor = cursor.getLineLink();
        }

        if(!existsValidLength) {
            throw new AllLinesEmptyException("The input you entered is incorrect: Cannot remove next attendee if all lines are empty. Please try again!");
        }

        // The idea will be removing the lowest seat number from the line(s) with the maximal length
        // This will ensure Condition 2 is satisfied
        cursor = temp;
        int maxLength = -1;
        int lowestNumber = cursor.getHeadPerson().getSeatNumber();
        Line bestLine = cursor;
        int counter = 1;

        // Iterate through all lines to find the one with the maximal length and minimum seat
        while(cursor != null) {
            // If we found a new maximal length, update our variables
            if(cursor.getLength() > maxLength) {
                maxLength = cursor.getLength();
                bestLine = cursor;
                lastRemovedPersonLine = counter;
            } else if(cursor.getLength() == maxLength && cursor.getHeadPerson().getSeatNumber() < lowestNumber) {
                // If we found a line with the same length but a lesser seat number, update our variables
                lowestNumber = cursor.getHeadPerson().getSeatNumber();
                bestLine = cursor;
                lastRemovedPersonLine = counter;
            }
            cursor = cursor.getLineLink();
            counter++;
        }
        fixCursor();
        // Remove the first person from the best line
        return bestLine.removeFrontPerson();
    }

    /**
     * Adds some number of new lines into {@link SecurityCheck}.
     * The {@link SecurityCheck} will re-balance itself afterward.
     * @param newLines                      The number of new lines to add
     * @throws InvalidLineCountException    Thrown if newLines is <= 0
     */
    public void addNewLines(int newLines) throws InvalidLineCountException {
        if(newLines <= 0) {
            throw new InvalidLineCountException("The input you entered is incorrect: Cannot add negative lines. Please try again!");
        }
        System.out.println("\nLoading...");
        lineCount += newLines;
        balanceLines();
    }

    /**
     * Removes some subset of the lines from the {@link SecurityCheck}
     * @param removedLines                  The subset of lines to remove, in integer array form
     * @throws LineDoesNotExistException    Thrown if a queried line does not exist within the {@link SecurityCheck}
     * @throws SingleLineRemovalException   Thrown if the user attempts to remove all/the last line
     */
    public void removeLines(int[] removedLines) throws LineDoesNotExistException, SingleLineRemovalException {
        // Iterate through removedLines to ensure every line is valid
        for (int line : removedLines) {
            if (line <= 0 || line > lineCount) {
                throw new LineDoesNotExistException("The input you entered is incorrect: Line " + line + " does not exist. Please try again!");
            }
        }

        // If the line count is equal to removedLines length,
        // this means that the user is attempting to remove all the lines. Throw exception if true
        if(lineCount == removedLines.length) {
            throw new SingleLineRemovalException("The input you entered is incorrect: Cannot remove the last line. Please try again!");
        }

        // We can simply adjust the line count and re-balance
        lineCount -= removedLines.length;
        balanceLines();

        // Handle printing out the result
        System.out.println("\nLoading...");
        if(removedLines.length == 1) {
            System.out.println("Line " + removedLines[0] + " has been decommissioned!");
        } else {
            System.out.print("Lines ");
            for(int i = 0; i < removedLines.length; i++) {
                System.out.print(removedLines[i]);
                if(i + 1 < removedLines.length) {
                    System.out.print(", ");
                }
            }
            System.out.print(" have been decommissioned!\n");
        }
    }

    /**
     * Handles all the re-balancing of the {@link SecurityCheck}.
     * This algorithm works by calculating the number of {@link Person} per {@link Line}.
     * Let's call this number x, from here we can find the number of lines with x people
     * or (x + 1) people fairly simple. If we have a total count of all people in the SecurityCheck,
     * We simply need to solve the equation: x * c + (x + 1) * (n - c) = T,
     * where:
     * x is the people per line,
     * c is the count of "x-people lines"
     * n is the total number of lines
     * T is the total number of people.
     * Once we do this, we put all the existing People into another intermediate LinkedList (called totalLine).
     * We then clear the existing Lines, and iterate through totalLine.
     * Upon hitting x or x + 1 people in a Line, add a new one and continue.
     */
    public void balanceLines() {
        Line cursor = headLine;
        // Add all existing people into one intermediate Linked List
        Line totalLine = new Line();
        while(cursor != null) {
            Person pCursor = cursor.getHeadPerson();
            while(pCursor != null) {
                totalLine.addPerson(new Person(pCursor.getName(), pCursor.getSeatNumber()));
                pCursor = pCursor.getNextPerson();
            }
            cursor = cursor.getLineLink();
        }
        int totalCount = totalLine.getLength();
        // solve the equation: x * c + (x + 1) * (n - c) = T
        int numPeoplePerLine = totalCount / lineCount;
        int numFullLines = numPeoplePerLine * lineCount + lineCount - totalCount;

        // Clear the existing LinkedList
        headLine = new Line();
        tailLine = headLine;
        cursor = headLine;
        Person pCursor = totalLine.getHeadPerson();

        // Iterate over the necessary number of lists we need to have in our SecurityCheck
        for(int i = 0; i < lineCount; i++) {
            int toAdd = numPeoplePerLine;
            // If we are adding (x + 1), then increment toAdd
            if(i < lineCount - numFullLines) {
                toAdd++;
            }

            // Iterate through totalLine, and while doing this, add a copy of that Person
            // to the current Line
            for(int j = 0; j < toAdd; j++) {
                Person thePerson = new Person(pCursor.getName(), pCursor.getSeatNumber());
                cursor.addPerson(thePerson);
                pCursor = pCursor.getNextPerson();
            }

            // If we are not at the final iteration of this loop,
            // add a new Line to the end of the LinkedList
            if(i + 1 < lineCount) {
                Line nextLine = new Line();
                cursor.setLineLink(nextLine);
                tailLine = nextLine;
                cursor = cursor.getLineLink();
            }
        }

        // Finally, fix the cursor position
        fixCursor();
    }

    /**
     * Adjust the cursor position to find the best {@link Line} for adding a new {@link Person}
     * This works by finding the first {@link Line} that has a length less than the head {@link Line}
     */
    private void fixCursor() {
        Line cursor = headLine;
        cursorLine = headLine;
        int len = cursor.getLength();
        int counter = 1;
        while(cursor != null) {
            // If a line was found with length less than head line
            // Set cursor to this line and break
            if(cursor.getLength() < len) {
                cursorLine = cursor;
                cursorPos = counter;
                break;
            }
            counter++;
            cursor = cursor.getLineLink();
        }
    }

    /**
     * Prints all the line counts by iterating through the {@link SecurityCheck} LinkedList
     */
    public void printLineCounts() {
        int counter = 1;
        Line cursor = headLine;
        while(cursor != null) {
            System.out.print("Line " + counter + ": " + cursor.getLength());
            if(cursor.getLength() > 1 || cursor.getLength() == 0) {
                System.out.print(" People Waiting.\n");
            } else {
                System.out.print(" Person waiting.\n");
            }
            cursor = cursor.getLineLink();
            counter++;
        }
    }

    /**
     * Returns the number of {@link Line}s in this {@link SecurityCheck}
     * @return  Number of lines
     */
    public int getLineCount() {
        return lineCount;
    }

    /**
     * Returns the line of the most recent {@link Person} removal.
     * This is used when printing out which line the person was removed from.
     * @return  Line where most recent removal occurred
     */
    public int getLastRemovedPersonLine() {
        return lastRemovedPersonLine;
    }

    /**
     * This utility function centers a String using String.format()
     * @param s The string to be centered
     * @return  The input string centered according to the default padding
     */
    private String centerString(String s) {
        int rightPadding = s.length() + ((defaultPadding - s.length()) / 2);
        String leftStr = "%-" + defaultPadding + "s";
        String rightStr = "%" + rightPadding + "s";
        return String.format(leftStr, String.format(rightStr, s));
    }

    /**
     * Prints a header of "=" signs for table formatting
     */
    private void printHeader() {
        int menuWidth = defaultPadding * 3 + defaultPadding / 2;
        for(int i = 0; i < menuWidth; i++) {
            System.out.print("=");
            if(i == menuWidth - 1) {
                System.out.print("\n");
            }
        }
    }

    /**
     * Prints all Lines in tabular format
     */
    public void printAllLines() {
        System.out.println("\nLoading...\n");
        printHeader();
        String format = "| %s | %s | %s |\n";
        System.out.format(format, centerString("Line"), centerString("Name"), centerString("Seat Number"));
        printHeader();
        int counter = 1;
        Line cursor = headLine;

        // Iterate through all Lines and format them accordingly
        while(cursor != null) {
            Person pCursor = cursor.getHeadPerson();
            while(pCursor != null) {
                String countStr = centerString(String.valueOf(counter));
                String nameStr = centerString(pCursor.getName());
                String seatStr = centerString(String.valueOf(pCursor.getSeatNumber()));
                System.out.format(format, countStr, nameStr, seatStr);
                pCursor = pCursor.getNextPerson();
            }
            counter++;
            cursor = cursor.getLineLink();
        }

        // Finally print the header again to close off the table
        printHeader();
    }
}
