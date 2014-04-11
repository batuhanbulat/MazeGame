package snippet;


public class Queue {
	private int rear, front;

	private Object[] elements;

	Queue(int capacity) {
		elements = new Object[capacity];
		rear = -1;
		front = 0;
	}

	void Enqueue(Object data) {
		if (isFull())
			System.out.println("Queue overflow");
		else{
			rear++;
		elements[rear] = data;}
	}

	Object Dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			Object data = elements[front];
			elements[front] = 0;
			front++;
			return data;
		}
	}

	Object Peek() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			return elements[front];
		}
	}

	boolean isEmpty() {
		return rear < front;
	}

	boolean isFull() {
		return (rear + 1 == elements.length);
	}

	int size() {
		return rear - front + 1;
	}

}