package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1010 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1010 resolver = new Timus1010();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		long i1,i0 = Integer.parseInt(in.readLine());
		long r = 0;
		int abs = 0;
		for(int j=0; j < n-1; j ++){
			i1 = Integer.parseInt(in.readLine());
			long temp = Math.abs(i1 - i0);
			i0 = i1;
			if(temp > r) {
				r = temp;
				abs = j;
			}
		}
		out.print(abs+1);
		out.print(" ");
		out.println(abs+2);
		out.flush();
	}
}
