package com.example.algo;

public class LinkedListSort {

    private Node head;
    /**
     * Node class
     */
    static class Node {
        int data;
        Node next;

        Node() {
        }
        Node (int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(int data) {
        if (head == null) {
            this.head = new Node(data);
            return;
        }

        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node(data);
    }

    public Node getMid(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast.next != null  && fast.next.next  != null) {
            slow = slow.next;
            fast = fast.next.next;
       }

        return slow;
    }

    public  Node sortList(Node head) {
        if (head == null || head.next == null) return head;
        Node mid = getMid(head);
        Node right = mid.next;
        mid.next = null;

        Node leftSorted = sortList(head);
        Node rightSorted = sortList(right);
        return merge(leftSorted, rightSorted);
    }

    Node merge(Node list1, Node list2) {
        Node dummyHead = new Node();
        Node tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
        }
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    private void printList(Node node) {
        System.out.print("My List: " );
        Node nd = node;
        while (nd != null) {
            System.out.print(" " + nd.data);
            nd = nd.next;
        }

    }

    public static void main(String[] args) {
        // 4,2,1,3
        LinkedListSort linkedList = new LinkedListSort();
        linkedList.add(10);
        linkedList.add(1);
        linkedList.add(60);
        linkedList.add(30);
        linkedList.add(5);

        linkedList.printList(linkedList.head);
        System.out.println("Mid value is " + linkedList.getMid(linkedList.head).data);

        Node sortedNode = linkedList.sortList(linkedList.head);

        linkedList.printList(sortedNode);

    }




}
