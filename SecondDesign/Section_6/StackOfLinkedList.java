package Section_6;
import java.util.*;

public class StackOfLinkedList<E extends ResultNode> implements Cloneable{
	private LinkedList<E> paths;
	
	public StackOfLinkedList(){
		paths = new LinkedList<E>();
	}
	
	public StackOfLinkedList(E element){
		paths = new LinkedList<E>();
		paths.add(element);
	}
	
	public E pop(){
		E result = paths.getLast();
		paths.removeLast();
		return result;
	}
	
	public void push(E element){
		if(isElementAvaliable(element))
			paths.add(element);
	}
	
	public E peek(){
		if(paths.size() == 0)
			return null;
		return paths.getLast();
	}
	
	public int size(){
		return paths.size();
	}
	
	public boolean isEmpty(){
		return paths.isEmpty();
	}
	
	//判断是否有元素相同
	public boolean isElementAvaliable(E element){
		for(int i = 0;i < paths.size();i++)
			if(paths.get(i).compareTo(element) == 0)
				return false;
		return true;
	}
	
	public Object clone(){
		StackOfLinkedList<E> stack = new StackOfLinkedList<E>();
		for(int i = 0;i < paths.size();i++){
			stack.push((E) paths.get(i).getClone());
		}
		return stack;
	}
	
	public StackOfLinkedList<E> getClone(){
		return (StackOfLinkedList)clone();
	}
}
