
public class Stack {
    private StackElement head = null;
    
    private static class StackElement {
        Object value;
        StackElement next;

        StackElement(Object value)
        {
            this.value = value;
        }
    }
    
    public Stack() {
    	head = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void push(Object value){
        StackElement newElement = new StackElement(value);
        newElement.next = head;
        head = newElement;
    }

    public Object pop(){
        StackElement returnedValue = head;
        head = returnedValue.next;
        return returnedValue.value;
    }
    
    public Object peek() {
    	return head;
    }

    public void clear(){
        head = null;
    }
}
