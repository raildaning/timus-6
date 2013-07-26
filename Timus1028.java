package solution.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Timus1028 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		Timus1028 resolver = new Timus1028();
		resolver.resolve();
	}

	BufferedReader in;
	StreamTokenizer st;
	PrintWriter out;

	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(in);
		out = new PrintWriter(System.out);
//		DP();
		Fenwick();
		out.flush();
	}

	int[] levels;
	int[] dp = new int[32002];

	/***
	 * TLE #9, 0.29s
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	void DP() throws NumberFormatException, IOException {
		int N = Integer.parseInt(in.readLine());
		levels = new int[N];
		for (int i = 0; i < N; i++) {
			String[] coor = in.readLine().split(" ");
			int x = Integer.parseInt(coor[0]);
			int l = 0;
			for (int j = x; j >= 0; j--) {
				if (dp[j] != 0) {
					l = dp[j];
					break;
				}
			}

			for (int j = x; j < 32001; j++) {
				dp[j]++;
			}

			levels[l]++;
		}

		for (int i = 0; i < N; i++)
			out.println(levels[i]);
	}
	
	/***
	 * AC 0.14s, 5000K
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	void Fenwick() throws NumberFormatException, IOException {
//		int N = Integer.parseInt(in.readLine());
		int N = nextInt();
		levels = new int[N];
		for (int i = 0; i < N; i++) {
//			String[] coor = in.readLine().split(" ");
			int x = nextInt() + 1;
			nextInt();
			int l = 0, temp = x;
			
			// get c[x]
			while(temp > 0){
				l += dp[temp];
				temp -= lowbit(temp);
			}
			levels[l]++;
			// add 1 to f[x]
			temp = x;
			while(temp < 32002) {
				dp[temp] += 1;
				temp += lowbit(temp);
			}
		}
		for (int i = 0; i < N; i++)
			out.println(levels[i]);
	}
	
	int lowbit(int n) {
		return n&(-n);
//		return n&(n^(n-1));
	}
	
	/***
	 * change to tokenizer
	 * AC, 0.125s, 1264K
	 * @return
	 * @throws IOException
	 */
	int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
