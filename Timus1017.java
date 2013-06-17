package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1017 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1017 resolver = new Timus1017();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int N = Integer.parseInt(in.readLine().trim());
		long[][] dp = new long[501][501];
		for(int i = 1; i <= 500; i ++)
			dp[i][i] = 1;
		for(int i = 2; i <= 500; i ++){
			for(int j = 1; j < i; j ++) {
				for(int k = 1; k <= i-j; k ++)
					if(k<j)
						dp[i][j] += dp[i-j][k];
			}
		}
		long sum = 0;
		for(int i = 1; i < N; i ++)
			sum += dp[N][i];
		out.println(sum);
		out.flush();
	}
}
