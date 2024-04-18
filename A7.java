package OSPractice;
import java.util.*;


public class A7 {
	
	public static void main(String[] args) {
		int n=0;
		int fs = 0;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter No elemtnts in sequence :");
		n = sc.nextInt();
		
		int arr[] = new int[n];
		
		System.out.println("Enter the Space Seperated Sequence :");
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.println("Enter the frame size :");
		fs = sc.nextInt();
		
		int fcfs = fcfs(arr, fs );
		System.out.println("FCFS :" + fcfs);
		
		int lru = lru(arr, fs );
		System.out.println("lru :" + lru);
		
		int optimal = optimal(arr, fs );
		System.out.println("optimal :" + optimal);
		
		
	}
	
	
	static int fcfs(int arr[] , int fs) {
		int pf = 0;
		Queue <Integer> q = new LinkedList<Integer>();
		
		for(int e: arr) {
			if(!q.contains(e)) {
				if(q.size()<fs) {
					q.add(e);
					pf++;
				}else {
					q.remove();
					pf++;
					q.add(e);
				}
			}
		}
		return pf;
	}
	
	static int lru(int arr[], int fs) {
		int pf = 0;
		Queue <Integer> q = new LinkedList<Integer>();
		
		for(int e: arr) {
			if(!q.contains(e)) {
				if(q.size()<fs) {
					q.add(e);
					pf++;
				}else {
					q.remove();
					pf++;
					q.add(e);
				}
			}else {
				q.remove(e);
				q.add(e);
			}
		}
		return pf;
	}
	
	static int optimal(int arr[], int fs) {
		int pf = 0;
		Queue <Integer> q = new LinkedList<Integer>();
		
		for(int i=0; i<arr.length; i++) {
			if(!q.contains(arr[i])) {
				if(q.size()<fs) {
					q.add(arr[i]);
					pf++;
				}else {
					int element_to_remove = findElementToRemove(arr, q, fs, i);
//					System.out.println("remove "+element_to_remove+"add "+arr[i]);
					q.remove(element_to_remove);
					pf++;
					q.add(arr[i]);
				}
			}
		}
		return pf;
	}
	
	
	static int findElementToRemove(int arr[], Queue<Integer> q, int fs, int i) {
		int cnt=0;
		Queue <Integer> q2 = new LinkedList<Integer>();
		q2.addAll(q);
		for(int j=i+1; j<arr.length; j++) {
			if(q.contains(arr[j])) {
				if(cnt==fs-1) {
					break;
				}
				q2.remove(arr[j]);
				cnt++;
			}
		}
		if(cnt > 1) {
			return q2.remove();
		}
		return q2.remove();
	}

}


//Enter No elemtnts in sequence :
//20
//Enter the Space Seperated Sequence :
//7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
//Enter the frame size :
//4
//FCFS :10
//lru :8
//optimal :8

