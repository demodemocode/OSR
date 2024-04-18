package OSPractice;
import java.util.*;

public class A6 {
	public static void main(String[] args) {
		int b =0;
		int p =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter No of blockes :");
		b= sc.nextInt();
		
		int block[] = new int[b];
		System.out.println("Enter Block Array :");
		for(int i = 0; i<b; i++) {
			block[i]= sc.nextInt();
		}
		
		System.out.println("Enter no of Processes :");
		p = sc.nextInt();
		
		int process[] = new int[p];
		
		System.out.println("Enter Process Array :");
		for(int i = 0; i < p; i++) {
			process[i]= sc.nextInt();
		}
		
		
		System.out.println("First Fit :");
		firstFit(block, process);
		
		System.out.println("Best Fit :");
		bestFit(block, process);
		
		System.out.println("Wrost Fit :");
		wrostFit(block, process);
		
		System.out.println("Next Fit :");
		nextFit(block, process);
		
		
	}
	
    static void firstFit(int b[], int p[]) {
    	int b1[] = b.clone();
    	int p1[] = p.clone(); 
    	int flag=0;
    	for(int i=0; i < p1.length; i++) {
    		for(int j =0; j<b1.length; j++) {
    			if(p1[i]<=b1[j]) {
    				int rms = b1[j]-p1[i];
    				System.out.println("Process "+i+" of size "+p1[i]+ " is alocated to block "+j+" of size "+b1[j]+ " Remaining space "+ rms);
    				b1[j]-=p1[i];
    				flag =1;
    				break;
    			}
    		}
    		if (flag == 0)
                System.out.println("Process " + i +" of size "+p1[i]+ " can not be allocated any block");
    		flag = 0;
    	}
    }
    
    static void bestFit(int b[], int p[]) {  
    	int b1[] = b.clone();
    	int p1[] = p.clone(); 
    	for(int i=0; i < p1.length; i++) {
    		int curr = Integer.MAX_VALUE;
    		int curr_block =-1;
    		for(int j = 0; j<b1.length; j++) {
    			if(p1[i]<= b1[j] &&  b1[j]<curr) {
    				curr = b1[j];
    				curr_block =j;
    			}
    		}
    		
    		if(curr_block!=-1) {
    			int rms = b1[curr_block]-p1[i];
				System.out.println("Process "+i+" of size "+p1[i]+" is alocated to block "+curr_block+" of size "+b1[curr_block]+ " Remaining space "+ rms);
				b1[curr_block]-=p1[i];
    		}else {
                System.out.println("Process " + i +" of size "+p1[i]+ " can not be allocated any block");    		}
    	}
    }

    static void wrostFit(int b[], int p[]) {  
    	int b1[] = b.clone();
    	int p1[] = p.clone(); 
    	for(int i=0; i < p1.length; i++) {
    		int curr = Integer.MIN_VALUE;
    		int curr_block =-1;
    		for(int j = 0; j<b1.length; j++) {
    			if(p1[i]<= b1[j] &&  b1[j]>curr) {
    				curr = b1[j];
    				curr_block =j;
    			}
    		}
    		
    		if(curr_block!=-1) {
    			int rms = b1[curr_block]-p1[i];
				System.out.println("Process "+i+" of size "+p1[i]+" is alocated to block "+curr_block+" of size "+b1[curr_block]+ " Remaining space "+ rms);
				b1[curr_block]-=p1[i];
    		}else {
                System.out.println("Process " + i +" of size "+p1[i]+ " can not be allocated any block");    		}
    	}
    }
    
    static void nextFit(int b[], int p[]) {
    	int b1[] = b.clone();
    	int p1[] = p.clone(); 
    	int flag=0;
    	int curr =0;
    	for(int i=0; i < p1.length; i++) {
    		int j = curr;
    		while(true) {
    			
    			if(p1[i]<=b1[j]) {
    				int rms = b1[j]-p1[i];
    				System.out.println("Process "+i+" of size "+p1[i]+ " is alocated to block "+j+" of size "+b1[j]+ " Remaining space "+ rms);
    				b1[j]-=p1[i];
    				flag =1;
    				curr = (j+1)%b1.length;
    				break;
    			}
    			if(j==curr-1) break;
    			j=(j+1)%b1.length;
    		}
    		if (flag == 0)
                System.out.println("Process " + i +" of size "+p1[i]+ " can not be allocated any block");
            flag = 0;
    	}
    }
}


//Enter No of blockes :
//3
//Enter Block Array :
//75 300 185
//Enter no of Processes :
//4
//Enter Process Array :
//100 200 20 150

//First Fit :
//Process 0 of size 100 is alocated to block 1 of size 300 Remaining space 200
//Process 1 of size 200 is alocated to block 1 of size 200 Remaining space 0
//Process 2 of size 20 is alocated to block 0 of size 75 Remaining space 55
//Process 3 of size 150 is alocated to block 2 of size 185 Remaining space 35

//Best Fit :
//Process 0 of size 100 is alocated to block 2 of size 185 Remaining space 85
//Process 1 of size 200 is alocated to block 1 of size 300 Remaining space 100
//Process 2 of size 20 is alocated to block 0 of size 75 Remaining space 55
//Process 3 of size 150 can not be allocated any block

//Wrost Fit :
//Process 0 of size 100 is alocated to block 1 of size 300 Remaining space 200
//Process 1 of size 200 is alocated to block 1 of size 200 Remaining space 0
//Process 2 of size 20 is alocated to block 2 of size 185 Remaining space 165
//Process 3 of size 150 is alocated to block 2 of size 165 Remaining space 15

//Next Fit :
//Process 0 of size 100 is alocated to block 1 of size 300 Remaining space 200
//Process 1 of size 200 is alocated to block 1 of size 200 Remaining space 0
//Process 2 of size 20 is alocated to block 2 of size 185 Remaining space 165
//Process 3 of size 150 is alocated to block 2 of size 165 Remaining space 15