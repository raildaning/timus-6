package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Timus1029 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1029 resolver = new Timus1029();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	StreamTokenizer st;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(in);
		out = new PrintWriter(System.out);
		DP();
		out.flush();
	}
	
	int[][] fees;
	int[] entries;
	int[][] dp;
	
	void DP() throws IOException {
		int M = nextInt();
		int N = nextInt();
		fees = new int[M+1][N+1];
		entries = new int[M+1];
		dp = new int[M+1][N+1];
		
		for(int i = 1; i <= M; i ++)
			for(int j = 1; j <= N; j ++) {
				fees[i][j] = nextInt();
//				dp[i][j]
			}
		
		int start; // = findMin(1, N); 
//		entries[1] = start;
		for(int i = 1; i <= N; i ++)
			dp[1][i] = fees[1][i];
		
		for(int i = 2; i <= M; i ++) {
			// determine start of this floor
			start = findMin(i, N);
			entries[i] = start;
			dp[i][start] = dp[i-1][start] + fees[i][start];
			
			for(int j = start - 1; j > 0; j --) {
				dp[i][j] = min(dp[i-1][j]+fees[i][j], dp[i][j+1]+fees[i][j]);
			}
			
			for(int j = start + 1; j <= N; j ++) {
				dp[i][j] = min(dp[i-1][j]+fees[i][j], dp[i][j-1]+fees[i][j]);
			}
			
		} // end loop of M floors
		
		int e = entries[2];
		out.printf("%d ", e);
		
		for(int i = 2; i <= M; i ++) {
			int t = entries[i];
			if(t > e) {
				for(int j = e; j < t; j ++)
					out.printf("%d ", j+1);
			} else if(t < e) {
				for(int j = e; j > t; j --)
					out.printf("%d ", j-1);
			}
			e = t;
			out.printf("%d ", e);
		}
	}
	
	int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
	
	int findMin(int m, int N) {
		int a = dp[m-1][1]+fees[m][1];
		int min = 1;
		for(int i = 2; i <= N; i ++) {
			if(a > dp[m-1][i]+fees[m][i]) {
				a = dp[m-1][i]+fees[m][i];
				min = i;
			}
		}
		return min;
	}
	
	int min(int a, int b) {
		if(a >= b)
			return b;
		else 
			return a;
	}
}
