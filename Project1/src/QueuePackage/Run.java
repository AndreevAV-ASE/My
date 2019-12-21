package QueuePackage;

import java.util.Random;

public class Run {
	public static void main(String[] args) {
		System.out.println("Start");
		Random rand = new Random();
		Queue q = new Queue(rand.nextInt(100));
		for(int i = 1; i < 9; i++) {
			q.Inqueue(rand.nextInt(100));
		}
		q.PrintInfo();
		
		q.Inqueue(7);
		q.Inqueue(8);
		q.Inqueue(9);	
		q.Dequeue();
		q.PrintInfo();

		
		q.PrintInfo();
		
	}
	
}

class Queue{
	private int value;
	
	private int size = 10;
	
	public Queue next;
	
	public Queue(int a) {
		value = a;
	}
	
	public void Inqueue(int a) {
		int count = GetSize();
		if(count == size) {
			Dequeue();
		}
		Queue q = this;
		while(q.next != null)
			q = q.next;
		q.next = new Queue(a);
	}
	
	public void Dequeue() {
		if(GetSize() != 1) {
			this.value = this.next.value;
			this.next = this.next.next;
		}
	}
	
	public void Print() {
		Queue q = this;
		while(q != null) {
			System.out.print(q.value + "\n");
			q = q.next;
		}
		System.out.println("\n");
	}
	
	public void PrintInfo() {
		int[] arr = ParseToArray();
		int[] sort = Sort(ParseToArray());
		System.out.println("[FIFO]|[Sorted]|[Median]");
		for(int i = 0; i < arr.length; i++) {
			if(GetMedian() == sort[i])
				System.out.println(arr[i] +"    | "+ sort[i]+" |    " + GetMedian());
			else
				System.out.println(arr[i] +"    | "+ sort[i]);
		}
	}
	
	public int[] ParseToArray() {
		int[] array = new int[GetSize()];
		Queue q = this;
		int i = 0;
		while( q != null) {
			array[i] = q.value;
			q = q.next;
			i++;
		}
		return array;
	}
	
	public int GetSize() {
		Queue q = this;
		int count = 0;
		while( q != null) {
			q = q.next;
			count++;
		}
		return count;
	}
	
	
	public int[] Sort(int [] a) {
		int l = a.length;
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < l; j++) {
				if(a[j] > a[i]) {
					int temp = a[i];
					a[i]=a[j];
					a[j] = temp;
				}
			}
		}
		return a;
	}
	public int GetMedian() {
		int[] a = Sort(ParseToArray());
		return a[a.length/2];
	}

}
