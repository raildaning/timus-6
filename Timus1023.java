package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1023 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1023 resolver = new Timus1023();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		BF();
		out.flush();
	}
	
	void BF() throws NumberFormatException, IOException {
		int n = Integer.parseInt(in.readLine());
		if(n%3 == 0)
			out.println(2);
		else if(n%4 == 0)
			out.println(3);
		else if(n%5 == 0)
			out.println(4);
		else {
			if(isPrime(n))
				out.println(n-1);
			else {
				int div = 3;
				for(int i = 3; i < n/2+1; i ++) {
					if(n%i == 0) {
						out.println(i-1);
						break;
					}
				}
			}
		}
	}
	
	boolean isPrime(int n) {
		if(n < 4)
			return true;
		for(int i = 2; i < n/2+1; i ++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
}
