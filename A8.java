package OSPractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A8 {

	public static void main(String[] args) {
		int n=0;
		int bs = 0;
		int hp = 0;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter No elemtnts Task Request :");
		n = sc.nextInt();
		
		int arr[] = new int[n];
		
		System.out.println("Enter the Space Seperated task requests :");
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.println("Enter the baral_size :");
		bs = sc.nextInt();
		
		System.out.println("Enter the head position :");
		hp = sc.nextInt();
		
		int FIFO = fifo(arr, bs, hp );
		System.out.println("FIFO :" + FIFO);
		
		int SSTF = sstf(arr, bs, hp );
		System.out.println("SSTF :" + SSTF);
		
		int SCAN = scan(arr, bs, hp );
		System.out.println("SCAN :" + SCAN);
		
		int CSCAN = c_scan(arr, bs, hp );
		System.out.println("CSCAN :" + CSCAN);
		
		
	}
	
	static int fifo(int arr[] , int bs, int hp) {
		int seektime = 0;
		seektime = Math.abs(hp- arr[0]);
		for(int i = 1; i<arr.length; i++) {
			seektime += Math.abs(arr[i]-arr[i-1]);
		}
		return seektime;
	}
	
	static int sstf(int arr[], int bs, int hp) {
		int seektime =0;
		int left = 0;
		int right =0;
		
		Arrays.sort(arr);
		
		
		while(arr[left]<=hp) {
		   left++;
		}
		
		right = left;
		
		
		int curr = hp;
		
		while(left>=0 && right < arr.length) {
			if(Math.abs(curr-arr[left]) < Math.abs(curr-arr[right])) {
//				System.out.println(curr +"-"+ arr[left]);
				seektime += Math.abs(curr-arr[left]);
				curr = arr[left];
				left--;
			}else {
//				System.out.println(curr +"-"+ arr[right]);
				seektime += Math.abs(curr-arr[right]);
				curr = arr[right];
				right++;
			}
		}
		
		while(left>=0) {
//			System.out.println(curr +"-"+ arr[left]);
			seektime += Math.abs(curr-arr[left]);
			curr = arr[left];
			left--;
		}
		while(right < arr.length) {
//			System.out.println(curr +"-"+ arr[right]);
			seektime += Math.abs(curr-arr[right]);
			curr = arr[right];
			right++;
		}
		return seektime;
	}
	
	static int scan(int arr[], int bs, int hp) {
		int seektime = 0;
		int left = 0;
		int right =0;
		
		Arrays.sort(arr);
		
		while(arr[right]<=hp) {
			   right++;
			}
			
			left = right-1;
			
			int curr = hp;
			

			while(right < arr.length) {
//				System.out.println(curr +"-"+ arr[right]);
				seektime += Math.abs(curr-arr[right]);
				curr = arr[right];
				right++;
			}
			
			seektime += Math.abs(curr-bs);
			curr  = bs;
			
			while(left>=0) {
//				System.out.println(curr +"-"+ arr[left]);
				seektime += Math.abs(curr-arr[left]);
				curr = arr[left];
				left--;
			}
		
		
		return seektime;
	}
	
	static int c_scan(int arr[], int bs, int hp) {
		int seektime = 0;
		int left = 0;
		int right =0;
		
		Arrays.sort(arr);
		
		while(arr[right]<=hp) {
			   right++;
			}
			
			left = right-1;
			
			int curr = hp;
			

			while(right < arr.length) {
//				System.out.println(curr +"-"+ arr[right]);
				seektime += Math.abs(curr-arr[right]);
				curr = arr[right];
				right++;
			}
			seektime += Math.abs(curr-bs);
			seektime += Math.abs(0-bs);
			curr  = 0;
			right =0;
			
			while(right<=left) {
//				System.out.println(curr +"-"+ arr[right]);
				seektime += Math.abs(curr-arr[right]);
				curr = arr[right];
				right ++;
			}
		
		
		return seektime;
	}
	
	
	
}


//Enter No elemtnts Task Request :
//8
//Enter the Space Seperated task requests :
//98 183 37 122 14 124 65 67
//Enter the baral_size :
//199
//Enter the head position :
//53
//FIFO :640
//SSTF :236
//SCAN :331
//CSCAN :382
