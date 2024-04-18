package OSPractice;

import java.util.*;

class Process {
	int pno;
	int priority;
	int at;
	int bt;
	int st;
	int ct;
	int tat;
	int wt;

	Process(int pno, int priority, int at, int bt) {
		this.pno = pno;
		this.priority = priority;
		this.at = at;
		this.bt = bt;
	}
}

public class A2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = 0;
		System.out.println("Enter the no of Processes :");
		n = sc.nextInt();

		Process pro[] = new Process[n];

		for (int i = 0; i < n; i++) {
			int currAT = 0;
			int currBT = 0;
			int currPriority = 0;
			System.out.println("Enter Priority AT BT for process " + (i + 1));
			currPriority = sc.nextInt();
			currAT = sc.nextInt();
			currBT = sc.nextInt();
			pro[i] = new Process(i + 1, currPriority, currAT, currBT);
		}

		System.out.println("FCFS :");
		fcfs(pro);
		System.out.println("SJF :");
		sjf(pro);
		System.out.println("PRIORITY :");
		priority(pro);

		int tq = 0;
		System.out.println("Enter the time Quantum :");
		tq = sc.nextInt();
		System.out.println("ROUND_ROBIN :");
		roundRobin(pro, tq);

	}

	static void fcfs(Process pro[]) {
		Arrays.sort(pro, (a, b) -> Integer.compare(a.at, b.at));
		int ct = 0;
		System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");

		for (Process p : pro) {
			if (ct < p.at) {
				ct = p.at;
			}
			p.st = ct;
			p.ct = p.bt + ct;
			ct = p.ct;
			p.tat = p.ct - p.at;
			p.wt = p.tat - p.bt;

			System.out.println(p.pno + "\t" + p.at + "\t" + p.bt + "\t" + p.ct + "\t" + p.tat + "\t" + p.wt);
		}

		for (Process p : pro) {
			System.out.print(p.pno + " -> ");
		}
		System.out.println();
	}

	static void sjf(Process pro[]) {
		Arrays.sort(pro, (a, b) -> Integer.compare(a.at, b.at));
		int ct = 0;
		System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < pro.length; i++) {
			if (ct < pro[i].at) {
				ct = pro[i].at;
			}
			int minbt = pro[i].bt;
			int min_process_index = -1;

			for (int j = i; j < pro.length; j++) {
				if (pro[j].at <= ct && pro[j].bt < minbt) {
					minbt = pro[j].bt;
					min_process_index = j;
				}
			}

			if (min_process_index != -1) {
				Process temp = pro[i];
				pro[i] = pro[min_process_index];
				pro[min_process_index] = temp;
			}

			pro[i].st = ct;
			pro[i].ct = pro[i].bt + ct;
			ct = pro[i].ct;
			pro[i].tat = pro[i].ct - pro[i].at;
			pro[i].wt = pro[i].tat - pro[i].bt;
			System.out.println(pro[i].pno + "\t" + pro[i].at + "\t" + pro[i].bt + "\t" + pro[i].ct + "\t" + pro[i].tat
					+ "\t" + pro[i].wt);

		}

		for (Process p : pro) {
			System.out.print(p.pno + " -> ");
		}
		System.out.println();

	}

	static void priority(Process pro[]) {
		Arrays.sort(pro, (a, b) -> Integer.compare(a.at, b.at));
		int ct = 0;
		System.out.println("Process\tAT\tBT\tCT\tTAT\tWT\tPriority");
		for (int i = 0; i < pro.length; i++) {
			if (ct < pro[i].at) {
				ct = pro[i].at;
			}
			int minPriority = pro[i].priority;
			int min_priority_index = -1;

			for (int j = i; j < pro.length; j++) {
				if (pro[j].at <= ct && pro[j].priority < minPriority) {
					minPriority = pro[j].priority;
					min_priority_index = j;
				}
			}

			if (min_priority_index != -1) {
				Process temp = pro[i];
				pro[i] = pro[min_priority_index];
				pro[min_priority_index] = temp;
			}

			pro[i].st = ct;
			pro[i].ct = pro[i].bt + ct;
			ct = pro[i].ct;
			pro[i].tat = pro[i].ct - pro[i].at;
			pro[i].wt = pro[i].tat - pro[i].bt;
			System.out.println(pro[i].pno + "\t" + pro[i].at + "\t" + pro[i].bt + "\t" + pro[i].ct + "\t" + pro[i].tat
					+ "\t" + pro[i].wt + "\t" + pro[i].priority);

		}

		for (Process p : pro) {
			System.out.print(p.pno + " -> ");
		}
		System.out.println();

	}

	static void roundRobin(Process pro[], int tq) {
		Arrays.sort(pro, (a, b) -> Integer.compare(a.at, b.at));
		int BTarr[] = new int[pro.length];
		for (int i = 0; i < pro.length; i++) {
			BTarr[i] = pro[i].bt;
			pro[i].ct =0;
		}
		Queue<Process> q = new LinkedList<Process>();
		ArrayList<Process> ans = new ArrayList<Process>();
		int ct = 0;

		if (ct < pro[0].ct) {
			ct = pro[0].ct;
			pro[0].st = ct;
		}
		q.add(pro[0]);
		while (!q.isEmpty()) {
			Process curr = q.remove();
			System.out.print(curr.pno + "->");
			if (curr.bt <= tq) {
				ct = ct + curr.bt;
				curr.bt = 0;
			} else {
				ct = ct + tq;
				curr.bt = curr.bt - tq;
			}
			for (Process p : pro) {
				if (curr.pno != p.pno && !q.contains(p) && p.at <= ct && !ans.contains(p)) {
					q.add(p);
				}
			}
			if (curr.bt > 0) {
				q.add(curr);
			} else {
				curr.ct = ct;
				ans.add(curr);
			}
		}
		System.out.println();
		System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < pro.length; i++) {
            pro[i].bt = BTarr[i];
			pro[i].tat = pro[i].ct - pro[i].at;
			pro[i].wt = pro[i].tat - pro[i].bt;
			System.out.println(pro[i].pno + "\t" + pro[i].at + "\t" + pro[i].bt + "\t" + pro[i].ct + "\t" + pro[i].tat
					+ "\t" + pro[i].wt);

		}
	}

}

//Enter the no of Processes :
//6
//Enter Priority AT BT for process 1
//0 0 4
//Enter Priority AT BT for process 2
//0 1 5
//Enter Priority AT BT for process 3
//0 2 2
//Enter Priority AT BT for process 4
//0 3 1
//Enter Priority AT BT for process 5
//0 4 6
//Enter Priority AT BT for process 6
//0 6 3
//FCFS :
//Process	AT	BT	CT	TAT	WT
//1	0	4	4	4	0
//2	1	5	9	8	3
//3	2	2	11	9	7
//4	3	1	12	9	8
//5	4	6	18	14	8
//6	6	3	21	15	12
//1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 
//SJF :
//Process	AT	BT	CT	TAT	WT
//1	0	4	4	4	0
//4	3	1	5	2	1
//3	2	2	7	5	3
//6	6	3	10	4	1
//2	1	5	15	14	9
//5	4	6	21	17	11
//1 -> 4 -> 3 -> 6 -> 2 -> 5 -> 
//PRIORITY :
//Process	AT	BT	CT	TAT	WT	Priority
//1	0	4	4	4	0	0
//2	1	5	9	8	3	0
//3	2	2	11	9	7	0
//4	3	1	12	9	8	0
//5	4	6	18	14	8	0
//6	6	3	21	15	12	0
//1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 
//Enter the time Quantum :
//2
//ROUND_ROBIN :
//1->2->3->1->4->5->2->6->5->2->6->5->
//Process	AT	BT	CT	TAT	WT
//1	0	4	8	8	4
//2	1	5	18	17	12
//3	2	2	6	4	2
//4	3	1	9	6	5
//5	4	6	21	17	11
//6	6	3	19	13	10

