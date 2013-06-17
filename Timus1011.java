package solution.timus;
import java.io.PrintWriter;
import java.util.Scanner;


public strictfp class Timus1011 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timus1011 resolver = new Timus1011();
		resolver.resolve();
	}
	
	Scanner scan;
	PrintWriter out;
	
	public void resolve() {
		scan = new Scanner(System.in);
		out = new PrintWriter(System.out);
		float P = scan.nextFloat();
		float Q = scan.nextFloat();
		
//		P = P/100;
//		Q = Q/100;
		
		int iP = (int)(P*100);
		int iQ = (int)(Q*100);
		
		int min = 0, n=1;
		while(min == 0){
			n ++;
			for(int i = 1; i < n; i ++){
//				if((float)i/n > P && (float)i/n < Q){
				if(10000*i > n*iP && 10000*i < n*iQ){
					min = n;
					break;
				}
			}
		}
		
		out.println(min);
		
		out.flush();
	}
	
	

}
