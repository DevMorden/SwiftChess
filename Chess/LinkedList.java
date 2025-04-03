/**
 * LinkedList
 *
 * @author Matthew Morden, 7965196
 *
 * REMARKS: This class contains the data contained inside of the Linked List class
*/

public class LinkedList{
    private Node top;

    public LinkedList(){
        top = null;
    }

    // This method adds moves to the linked list based on the given value. It places it in a descending order.
    //
    // PARAMETERS:
    //    firstParameter: The toAdd parameter is the move we want to add to the linked list.
    //    secondParameter: The value parameter is the value of the move and will determine where it is placed 
    //                     in the linked list.
    public void add(Move toAdd, int value){
        Node newNode = new Node(toAdd);
        if(top == null || value > top.getData().getCapturePiece().getValue()){
            newNode.setNext(top);
            top = newNode;
        }else{
            Node curr = top;
            // Find the position to insert the new node
            while (curr.getNext() != null && curr.getNext().getData().getCapturePiece().getValue() > value) {
                curr = curr.getNext();
            }
            // Insert the new node
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
        }
    }

    public Move remove(){
        if(top == null){
            return null;
        }

        Move removedMove = top.getData();
        top = top.getNext();
        return removedMove;
    }

    // This method is used to find a move at the given index (num). In the EasyAI, every move is given a value 
    // of 0 so it doesn't change and the selection is random. For the HardAI, a capture move is given a certain 
    // value and the highest value is chosen.
    //
    // PARAMETERS:
    //    firstParameter: The num parameter is the index of which move we want to remove and return.
    //    
    // RETURNS:
    //    This method returns the move at the given location (num).
    public Move search(int num){
        if(top.getData().getCapturePiece().getValue() > 0){
            return top.getData();
        }else{
            int counter = 0;
            Node curr = top;
    
            while(curr.getNext() != null && counter < num){
                curr = curr.getNext();
                counter++;
            }
    
            return curr.getData();
        }
    }
}