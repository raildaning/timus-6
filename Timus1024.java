package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1024 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1024 resolver = new Timus1024();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
//		BF();
		LCM();
		out.flush();
	}
	
	int[] p, test;
	
	/***
	 * Time limit exceeded at Test#11
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	void BF() throws NumberFormatException, IOException {
		int N = Integer.parseInt(in.readLine());
		p = new int[N+1];
		test = new int[N+1];
		String[] ps = in.readLine().split(" ");
		for(int i = 0; i < N; i ++) {
			p[i+1] = Integer.parseInt(ps[i]);
			test[i+1] = p[i+1];
		}
		int t = 1;
		while(!isEn(test)) {
			t ++;
			// permutate p(p[i])
			for(int i = 1; i <= N; i ++) {
				test[i] = p[test[i]];
			}
		}
		out.println(t);
	}
	
	boolean isEn(int[] p) {
		int l = p.length;
		for(int i = 1; i < l; i ++) {
			if(i != p[i])
				return false;
		}
		return true;
	}
	
	/***
	 * test each position how many times it can be back
	 * calculate the LCM for all the moves of each position
	 */
	void LCM() throws NumberFormatException, IOException {
		int N = Integer.parseInt(in.readLine());
		p = new int[N+1];
		test = new int[N+1];
		String[] ps = in.readLine().split(" ");
		for(int i = 0; i < N; i ++) {
			p[i+1] = Integer.parseInt(ps[i]);
			test[i+1] = p[i+1];
		}
		
		for(int i = 1; i <= N; i ++) {
			int t = 1;
			while(test[i] != i) {
				test[i] = p[test[i]];
				t ++;
			}
			test[i] = t;
//			out.printf("%d ",t);
		}
		int a = test[1];
		for(int i = 2; i <= N; i ++) {
			a = calLCM(a,test[i]);
		}
		out.println(a);
	}
	
	int calLCM(int a, int b) {
		if(a == b)
			return a;
		else if(a > b){
			return a / calGCD(a,b) * b;
		} else 
			return b / calGCD(b,a) * a;
	}
	
	int calGCD(int a, int b) {
		int mode;
		if(a == b)
			return a;
		else if(a > b) {
			a = a % b;
			if(a == 0)
				return b;
			else
				return calGCD(b, a);
		}
		else {
			b = b % a;
			if(b == 0)
				return a;
			else
				return calGCD(a, b);
		}
	}
}
