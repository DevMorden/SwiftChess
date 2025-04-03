/**
 * Node
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class contains the data contained inside of the Linked List class
*/

public class Node{
    private Move data;
    private Node next;
    
    // Constructors
    public Node(Move move){
        this.data = move;
        this.next = null;
    }

    // Getter and setters
    public void setNext(Node toSet){
        next = toSet;
    }

    public Move getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }
}