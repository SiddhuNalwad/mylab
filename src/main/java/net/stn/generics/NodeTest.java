package net.stn.generics;

class Node<T> {

	public T data;

	public Node(T data) {
		this.data = data;
	}

	public void setData(T data) {
		System.out.println("Node.setData");
		this.data = data;
	}
}

class MyNode extends Node<Integer> {
	public MyNode(Integer data) {
		super(data);
	}

	public void setData(Integer data) {
		System.out.println("MyNode.setData");
		super.setData(data);
	}
}

public class NodeTest {
	public static void main(String[] args) {

		MyNode mn = new MyNode(5);
		Node n = mn; // A raw type - compiler throws an unchecked warning
		n.setData("Hello");
		// Integer x = mn.data; // Causes a ClassCastException to be thrown.

		/*
		 * Here is what happens as the code is executed:
		 * 
		 * n.setData("Hello"); causes the method setData(Object) to be executed
		 * on the object of class MyNode. (The MyNode class inherited
		 * setData(Object) from Node.) In the body of setData(Object), the data
		 * field of the object referenced by n is assigned to a String. The data
		 * field of that same object, referenced via mn, can be accessed and is
		 * expected to be an integer (since mn is a MyNode which is a
		 * Node<Integer>. Trying to assign a String to an Integer causes a
		 * ClassCastException from a cast inserted at the assignment by a Java
		 * compiler.
		 * 
		 */
	}

}
