// GenericLinkedList.java // Create a generic linked list class, to be used for both Missions and astronauts

public class GenericLinkedList<T> {
  private Node<T> head;
  private Node<T> tail;
  private int size;

  // Node class to represent each element in the linked list
  private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  // Constructor
  public GenericLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  // Method to add a new element to the linked list, if head is null, set head  tail to the new node, if not business as usual
  public void add(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      tail = newNode;
    }
    size++;
  }

  // Method to remove an element from the linked list, if head is null, return, if head data equals data to be removed, set head to next node, if not the list until the data is found and remove it
  public void remove(T data) {
    if (head == null)
      return;
    if (head.data.equals(data)) {
      head = head.next;
      size--;
      return;
    }
    Node<T> current = head;
    while (current.next != null && !current.next.data.equals(data)) {
      current = current.next;
    }
    if (current.next != null) {
      current.next = current.next.next;
      if (current.next == null) {
        tail = current;
      }
      size--;
    }
  }

  // Method to check linked list
  public T get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  public int getSize() {
    return size;
  }
}
