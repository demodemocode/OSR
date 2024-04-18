package OSPractice;
import java.lang.reflect.Executable;
import java.util.*;

public class A5 {
	public static void main(String []args) {
		Scanner sc = new Scanner(System.in);
		int rn=0;
		int pn =0;
		System.out.println("Enter No of resources :");
		rn = sc.nextInt();
		
		System.out.println("Enter No of Processes :");
		pn = sc.nextInt();
		
		int aloc[][] = new int[pn][rn];
		int maxNeed[][]= new int[pn][rn];
		int remNeed[][] = new int[pn][rn];
		
		int avlRes[] = new int[rn];
		
		System.out.println("Enter alocated resoures :");
		for(int i=0; i<pn; i++) {
			for(int j = 0; j<rn; j++) {
				aloc[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter Max required resoures :");
		for(int i=0; i<pn; i++) {
			for(int j = 0; j<rn; j++) {
				maxNeed[i][j] = sc.nextInt();
			}
		}
		
		System.out.println("Enter Available Resources :");
		for(int i=0; i<rn; i++) {
			avlRes[i]= sc.nextInt();
		}
		
		System.out.println("Allocated Resources :");
		printMat(aloc);
		System.out.println("Max Needed Resources :");
		printMat(maxNeed);
		
		
		System.out.println("Remaining need resources :");
		for(int i=0; i<pn; i++) {
			for(int j = 0; j<rn; j++) {
				remNeed[i][j] = maxNeed[i][j]-aloc[i][j];
			}
		}
		
		printMat(remNeed);
		
		
		System.out.print("Available Resources :");
		for(int i=0; i<rn; i++) {
			System.out.print(avlRes[i]+"\t");
		}
		System.out.println();
		
		
		ArrayList<Integer> seq = new ArrayList<>();
		seq = printSequence(aloc, remNeed, avlRes);
		if(seq.size()== pn) {
			System.out.println(seq);
		}else {
			System.out.println("System has deadlock");
		    System.out.println("Safe Seq found till "+ seq);
		}
		
		
		
	}
	
	static void printMat(int mat[][]) {
		for(int i=0; i<mat.length; i++) {
			for(int j = 0; j<mat[0].length; j++) {
				System.out.print(mat[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	static ArrayList<Integer> printSequence(int aloc[][], int remNeed[][], int avlRes[]) {
		ArrayList<Integer> ans = new ArrayList<>();
	    while(true) {
	    	int executbleProcess = -1;
	    	for(int i=0; i< aloc.length; i++) {
	    		if(!ans.contains(i)) {
	    			int check =1;
	    			for(int j=0; j<avlRes.length; j++) {
	    				if(remNeed[i][j]>avlRes[j]) {
	    					check =0;
	    					System.out.println("Process "+i + " is chhecked for " + avlRes[j]);
	    					break;
	    				}
	    			}
	    			if(check ==1) {
	    				System.out.println("Executable porcess found "+i);
	    				executbleProcess = i;
	    				ans.add(executbleProcess);
	    	    		for(int k=0; k<avlRes.length; k++) {
	    	    			avlRes[k] = avlRes[k] + aloc[executbleProcess][k];
	    	    		}	
	    			}
	    		}
	    	}
	    	
	    	if(executbleProcess==-1) {
	    		break;
	    	}
	    	
	    }
		return ans;
	}
	
	

}


//Enter No of resources :
//3
//Enter No of Processes :
//5
//Enter alocated resoures :
//0 1 0
//2 0 0
//3 0 2
//2 1 1
//0 0 2
//Enter Max required resoures :
//7 5 3
//3 2 2
//9 0 2
//2 2 2
//4 3 3
//Enter Available Resources :
//3 3 2
//Allocated Resources :
//0	1	0	
//2	0	0	
//3	0	2	
//2	1	1	
//0	0	2	
//
//Max Needed Resources :
//7	5	3	
//3	2	2	
//9	0	2	
//2	2	2	
//4	3	3	
//
//Remaining need resources :
//7	4	3	
//1	2	2	
//6	0	0	
//0	1	1	
//4	3	1	
//
//Available Resources :3	3	2	
//Process 0 is chhecked for 3
//Executable porcess found 1
//Process 2 is chhecked for 5
//Executable porcess found 3
//Executable porcess found 4
//Executable porcess found 0
//Executable porcess found 2
//[1, 3, 4, 0, 2]

