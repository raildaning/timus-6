package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1018 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1018 resolver = new Timus1018();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		TreeDP();
		out.flush();
	}
	
	Tree[] nodes;
	int[][] edges;
	int[][] dp;
	int[] flag;
	
	void TreeDP() throws NumberFormatException, IOException{
		String[] NQ = in.readLine().split(" ");
		int N = Integer.parseInt(NQ[0]);
		int Q = Integer.parseInt(NQ[1]);
		
		nodes = new Tree[N+1];
		dp = new int[N+1][N+1];
		flag = new int[N+1];
		edges = new int[N+1][N+1];
		
		for(int i = 0; i < N+1; i ++) {
			nodes[i] = new Tree();
			for(int j = 0; j < N+1; j ++){
				edges[i][j] = -1;
				dp[i][j] = -1;
			}
		}
		
		// construct inum and value
		for(int i = 1; i < N; i ++){
			String[] branch = in.readLine().split(" ");
			int begin = Integer.parseInt(branch[0]);
			int end = Integer.parseInt(branch[1]);
			int apple = Integer.parseInt(branch[2]);
			edges[begin][end] = apple;
			edges[end][begin] = apple;
		}
		buildTree(1,N);
		dfs(1,Q+1);
		out.println(dp[1][Q+1]);
	}
	
	void buildTree(int rt, int n) {
		flag[rt] = 1;
		for(int i = 1; i <= n; i ++){
			if(flag[i]==0 && edges[rt][i] != -1){
				if(nodes[rt].lchld == 0)
					nodes[rt].lchld = i;
				else
					nodes[rt].rchld = i;
				nodes[i].value = edges[rt][i];
				buildTree(i,n);
			}
		}
	}
	
	int dfs(int rt, int m) {
		if(rt == 0 || m <= 0) return 0;
		if(dp[rt][m] != -1)	return dp[rt][m];
		
		dp[rt][m] = 0;
		for(int a = 0; a < m; a ++){
			int b = m-a-1;
			int sl, sr, lch, rch;
			lch = nodes[rt].lchld;
			rch = nodes[rt].rchld;
			sl = dfs(lch,a);
			sr = dfs(rch,b);
			dp[rt][m] = max(dp[rt][m],sl+sr);
		}
		return dp[rt][m] += nodes[rt].value;
	}
	
	int max(int a, int b) {
		if(a > b) return a;
		else return b;
	}
	
	class Tree {
		int lchld, rchld;
		int value;
	}
}
