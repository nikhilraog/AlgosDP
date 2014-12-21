import java.util.Scanner;

/**
 * @authors: Nikhil G Rao(ngr140030) and Arun BG (axb142530)
 * @date: 10/26/2014
 * @Last modified: 11/02/2014
 * @Inpu: Reads from stdin
 */

public class Lmsdp

{   
	//Part_a of the required solution
	public static void part_a(int[] a, int[] w) {
		int[] LW = new int[a.length];
		int[] use = new int[a.length];

		LW[0] = 0;// Base Case
        // Calculate Maximum Weight
		for (int i = 1; i < a.length; i++) {
			LW[i] = w[i];				// Single element is itself having maximum weight
			use[i] = i;						
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j] && w[i] + LW[j] > LW[i]) {
					LW[i] = w[i] + LW[j];		// add the weight of previous soln
					use[i] = j;					//Store index to backtrack later
				}
			}
		}

		// loop over array to find maximum weight and finds corresponding index//
		int maxi = LW[0];
		int ind = 0;
		for (int i = 1; i < LW.length; i++) {

			if (LW[i] > maxi) {
				maxi = LW[i];	 	// Value of Max Weight
				ind = i;      		// index of Max Weight element for current iteration
			}
		}

		System.out.println("Output for part a:");

		System.out.print(maxi+"  ");  		//Print Maximum Weight
		System.out.println();	

		int[] userev = new int[use.length];		// Array to store index of elements used in computing max weight

		int m = 0;
		int i = ind;

		while (i != use[i]) {
			userev[m] = use[i];
			i = use[i];
			m++;
		}
		userev[m] = ind;

		//Reverse the userev array to print solution in order of scanning elements initailly
		for (int r = 0; r < m / 2; r++) {
			int temp = userev[r];
			userev[r] = userev[m - 1 - r];
			userev[m - 1 - r] = temp;
		}

		for (int s = 0; s <= m; s++) {
			System.out.print(userev[s] + "  ");
			

		}

		int l = 0;
		System.out.println();
        
		//Output Elements that make longest maximum weight sequence
		while (l <= m) {

			System.out.print(a[userev[l]] + "  ");
			l++;
		}
		System.out.println();

	}

	//Main Function
	public static void main(String[] args) throws java.lang.Exception {
		//File f = new File("in-2-10-2.txt");
		//File f = new File(args[0]);
		// if (args.length>1) {
		// System.out.println("Usage: java lmsdp <input filename>" + args);
			//  }
		System.out.println("Usage : Enter Input as specified");
		Scanner in = new Scanner(System.in);
		//Scanner in = new Scanner(f);
		int n = in.nextInt();
		int k = in.nextInt();

		int a[] = new int[n + 1];
		int w[] = new int[n + 1];

		try {
			for (int i = 1; i < a.length; i++) {
				if (in.hasNextInt()) {
					a[i] = in.nextInt();
					w[i] = in.nextInt();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		part_a(a, w); 			//function call for part_a
		
		part_b(a, w, k + 1);	//function call for part_b

	}

	private static void part_b(int[] a, int[] w, int k) {
		int[][] L = new int[a.length][k];			// L - array to store maximum weight at each step
		int[][] sol = new int[a.length][k];			// soln array to backtrack later
		int[][] solk = new int[a.length][k];		// If a violation is used in computing max Weight

		// Base Case
		L[0][0] = 0;
		for (int i = 1; i < k; i++) {
			L[0][i] = 0;
		}
		
		
		
		for(int i=1;i<a.length;i++){
			for(int j=0;j<k;j++){
				L[i][j] = w[i];
				sol[i][j] = i;
				
				for(int l=i-1;l>=0;l--){
					// If no violation
					if(a[l]<a[i] && L[i][j]< L[l][j]+w[i]){
						L[i][j] = L[l][j]+ w[i];
						sol[i][j] = l;
						solk[i][j]=j;
					}
					// If violation is considered
					else if( a[l]>=a[i] && j>0 && L[i][j] < (L[l][j-1] + w[i] - (a[l]-a[i])*w[i])){
						L[i][j] = (L[l][j-1] + w[i]  - (a[l]-a[i])*w[i]);
						sol[i][j] = l;
						solk[i][j]=j-1;
					}
					
				  }				 
				}
			}  //end of last outer for
	
		//Loop over to find maximum weight adn corresponding row and column
		int maxValue = 0;int row=0,col = 0;
		for (int i = 0; i < L.length; i++) {
		    for (int j = 0; j < k; j++) {
		        if (L[i][j] > maxValue) {
		           maxValue = L[i][j];    //Maximum Weight
		           row = i;				  //row in which Max wt was found
		           col = j;   			  //coulmn in which Max Wt. was found
		           
		        }
		    }
		    
		}
		System.out.println("Output for part b: ");
		System.out.println(maxValue);
		// To print index of elements causing Longest Max Wt
		String str = "";
		int r=row;int i,j=col; 
	    while(sol[row][col]!=row){
	    	
	    	str= row+"  "+str;   // Only indices are appended
	    	i=sol[row][col];		// row in soln array
	    	col=solk[row][col];     // column in solk array - previous violation used
	    	row=i;
	    }	
	    str= row+"  "+str;
	    
	 System.out.println(str);	//Print indices of elements in our solution
		
	 str = "";
		row=r; col=j;
	    while(sol[row][col]!=row){
	    	str= a[row]+"  "+str;    // Elements are considered
	    	i=sol[row][col];		// row
	    	col=solk[row][col];		//column
	    	row=i;
	    }	
	    str= a[row]+"  "+str;
	 System.out.println(str);		//Print elements that are responsible for Longest Max Wt. sequence
	 
	}	 

}
