package com.zzc.test.list;

public class ReverseLinkedList {

	public static void main(String[] args) {
		
		//create the test List
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		nodeA.setNext(nodeB);
		Node nodeC = new Node("C");
		nodeB.setNext(nodeC);
		Node nodeD = new Node("D");
		nodeC.setNext(nodeD);
		Node nodeE = new Node("E");
		nodeD.setNext(nodeE);
		
		printList(nodeA);
		
		//Reverse the List
		
		Node head = reverse(nodeA);
		printList(head);
		
		//Reverse print the list
		reversePrint(head);
	}
	
	public static void reversePrint (Node head) {
		//print the last one node
		if(head.next == null) {
			System.out.println(head.getValue());
			return;
		}
		
		//print the left Nodes		
		reversePrint(head.getNext());
		
		//print itself
		System.out.println(head.getValue());
	}
	
	public static Node reverse (Node head) {
		Node prev, curr;
		
		prev = null;
		curr = head;
		while(curr != null) {
			//get the next Node
			Node next = curr.getNext();
			//point the next to the prev
			curr.setNext(prev);
			//set prev = curr
			prev = curr;
			//set curr = next
			curr = next;			
		}
		
		return prev;
		
	}
	
	public static void printList (Node head) {
		Node curr = head;
		while(curr != null) {
			System.out.println(curr.getValue());
			curr = curr.getNext();
		}
		System.out.println("================================");
	}

}

class Node {
	
	String value;
	Node next;
	
	public Node (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
