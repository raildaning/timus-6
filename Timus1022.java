package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;


public class Timus1022 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1022 resolver = new Timus1022();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		GraphSearch();
		out.flush();
	}
	
	int n;
	int[] flag;
	int[][] edges;
	
	void GraphSearch() throws NumberFormatException, IOException{
		n = Integer.parseInt(in.readLine());
		int[] vertices = new int[n+1];
		flag = new int[n+1];
		edges = new int[n+1][n+1];
		// build graph
		for(int i = 0; i < n; i ++) {
			String[] vs = in.readLine().split(" ");
			for(String s : vs){
				if(s.equals("0"))
					break;
				int t = Integer.parseInt(s);
				edges[i+1][t] = 1;
				vertices[t] = 1;
			}
		}
		
		for(int i = 1; i <= n; i ++) {
			if(vertices[i]==0)
				BFS(i);
		}
	}
	
	void BFS(int s) {
		Queue<Integer> children = new LinkedList<Integer>();
		children.offer(s);
		
		while(!children.isEmpty()) {
			int child = children.poll();
			
			int id = 0;
			for(int i = 1; i <= n; i ++) {
				if(edges[i][child] == 1)
					id ++;
			}
			if(id == 0){
				if(flag[child] == 0) {
					out.printf("%d ", child);
					flag[child] = 1;
				}
				
				for(int i = 1; i <= n; i ++) {
					if(edges[child][i]==1) {
						edges[child][i] = 0; 	// remove the visited edge
						children.offer(i);
					}
				}
			}
		}
	}
}
