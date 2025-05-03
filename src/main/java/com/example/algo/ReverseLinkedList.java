package com.example.algo;

public class ReverseLinkedList {

    static class Node {
        int data;
        Node next;
        Node (int data) {
            this.data = data;
        }
    }

    Node head;

    public void add(int data) {
        if (head == null) {
            this.head = new Node(data);
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    public Node reverse() {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public void print(Node nod) {
        Node curr = nod;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedList linkedList = new ReverseLinkedList();
        linkedList.add(10);
        linkedList.add(1);
        linkedList.add(60);
        linkedList.add(30);
        linkedList.add(5);
        linkedList.print(linkedList.head);

        // reverse
        Node nod = linkedList.reverse();
        System.out.println("** reverse **");
        linkedList.print(nod);

    }
}
