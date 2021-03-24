
public class Pair {

    	private int intA;
    	private int intB;
    	private boolean added;
    	private Object node;
    	private Object node2;
    	
    	public Pair(int a, int b) {
    		intA = a;
    		intB = b;
    		added = false;
    		node = null;
    	}
    	
    	public int getA() {
    		return intA;
    	}
    	
    	public int getB() {
    		return intB;
    	}
    	
    	public void setB(int a) {
    		intB = a;
    	}
    	
    	public boolean getinfo() {
    		return added;
    	}
    	
    	public void setinfo(boolean a) {
    		added = a;
    	}
    	
    	public String toString() {
    		return "(" + getA() + "," + getB() + ")";
    	}
    	
    	public void setNode(Object c) {
    		node = c;
    	}
    	
    	public Object getNode() {
    		return node;
    	}
    	
    	public void setNode2(Object c) {
    		node2 = c;
    	}
    	
    	public Object getNode2() {
    		return node2;
    	}
    	
}
