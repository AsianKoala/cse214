/**
 * The Line class represents a number of {@link Person} standing in line.
 * This class is essentially a Singly-Linked List.
 * Furthermore, this Linked List is always sorted in ascending order (based on each Person's seat number).
 * At the same time, the Line class acts as a Linked List Node as well, as the program
 * will run with multiple lines consisting of multiple people.
 */
public class Line {
    private Person headPerson;
    private Person tailPerson;
    private int length;
    private Line lineLink;

    /**
     * Creates the default instance of {@link Line}
     */
    public Line() {
        length = 0;
    }

    /**
     * Adds a person to the Line. To ensure that the Line is always sorted in ascending order,
     * we will find the first Node in which the node's seat number is greater than the attendee's
     * seat number. Then, we will add the attendee's Node behind it, with the use of a trailing
     * cursor.
     * @param attendee  The new person who will be added to the Line.
     */
    public void addPerson(Person attendee) {
        // If the line is empty, simply set the head and tail pointers to this new node
        if(length == 0) {
            headPerson = attendee;
            tailPerson = attendee;
        } else {
            Person cursor = headPerson;
            Person trail = null; // Trailing cursor that is always one node behind cursor
            boolean foundPerson = false;
            while(cursor != null) {
                // We have found the first position where the cursor node is greater than the attendee node
                if(cursor.getSeatNumber() > attendee.getSeatNumber()) {
                    foundPerson = true;
                    // If trail is null, then the attendee should be the first person in the list.
                    if(trail == null) {
                        attendee.setNextPerson(headPerson);
                        headPerson = attendee;
                    } else {
                        // Add the new node between the trail and cursor nodes
                        trail.setNextPerson(attendee);
                        attendee.setNextPerson(cursor);
                    }
                    break;
                }

                // Iterate the cursor and trail while moving across the Linked List
                trail = cursor;
                cursor = cursor.getNextPerson();
            }

            // If we never found a person who has a greater seat number
            // Then we need to add the attendee to the very end of the list, since that is the biggest seat number.
            if(!foundPerson) {
                tailPerson.setNextPerson(attendee);
                tailPerson = attendee;
            }
        }
        // Make sure to increase the length of the list each time we add
        length++;
    }

    /**
     * Remove the first person (head of the Linked List).
     * This is also equivalent to removing the person with the lowest seat number.
     * @return  The person who was removed
     */
    public Person removeFrontPerson() {
        Person result = headPerson;
        headPerson = headPerson.getNextPerson();
        length--;
        return result;
    }

    /**
     * Returns the length of the line
     * @return  The length of the line
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the next line
     * @return  The reference to the next {@link Line}
     */
    public Line getLineLink() {
        return lineLink;
    }

    /**
     * Returns the first person in line
     * @return  The first {@link Person} in line
     */
    public Person getHeadPerson() {
        return headPerson;
    }

    /**
     * Returns the last person in line
     * @return  The last {@link Person} in line
     */
    public Person getTailPerson() {
        return tailPerson;
    }

    /**
     * Sets the next line to a specified {@link Line}
     * @param lineLink  The new {@link Line} reference for the next line
     */
    public void setLineLink(Line lineLink) {
        this.lineLink = lineLink;
    }
}
