package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;


public class Timus1003 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1003 resolver = new Timus1003();
//		resolver.resolve();
		resolver.improve();
	}
	
	BufferedReader in;
	PrintWriter out;
	
	String[] guesses;
	
	public void improve() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);

		int len = Integer.parseInt(in.readLine());
		while(len != -1) {
			// read and store guesses
			int len_g = Integer.parseInt(in.readLine());
			guesses = new String[len_g];
			for(int i = 0; i < len_g; i ++) {
				guesses[i] = in.readLine();
			}
			
			// do guess
			int result = 0, i = 0;
			DistinctSet ds = new DistinctSet();
			for(i = 0; i < len_g; i ++) {
				String[] guess = guesses[i].split(" ");
				int start = Integer.parseInt(guess[0]);
				int end = Integer.parseInt(guess[1]);
				int p = guess[2].equalsIgnoreCase("even")?0:1;	// even:0, odd:1
				
				if(start > end || start > len || end > len) {
					result = i;
					break;
				}
				
				ds.addEle(start-1);
				ds.addEle(end);
				ds.addEle(start-1+len+1);
				ds.addEle(end+len+1);
				
				LinkNode fs = ds.find(start-1);
				LinkNode fe = ds.find(end);
				LinkNode es = ds.find(start-1+len+1);
				LinkNode ee = ds.find(end+len+1);
				
				if(p == 0) {
					// if p is even, start & end are friend, their enemies are friend
					if(fs==ee && fe==es) {
						result = i;
						break;
					}

					ds.union(fs, fe);
					ds.union(es, ee);
				} else {
					// if p is odd, start & end are enemy
					if(fs == fe) {
						result = i;
						break;
					}

					ds.union(fs, ee);
					ds.union(fe, es);
				}
				
			} // end do guess
			
			if(i >= len_g)
				out.println(len_g);
			else
				out.println(result);
			
			len = Integer.parseInt(in.readLine());
		} // end while
		out.flush();
	}
	
	class DistinctSet {
		HashMap<Integer,LinkNode> elements = new HashMap<Integer,LinkNode>();
		
		public void addEle(int x) {
			if(!elements.containsKey(x)) {
				elements.put(x, new LinkNode(x));
			}
		}
		
		public LinkNode find(int x) {
			if(elements.containsKey(x)) {
				LinkNode n = elements.get(x);
				if(n.parent != n){
					LinkNode temp = find(n.parent.value);
					n.parent = temp;
					return temp;
				} else {
					return n;
				}
					
			} else 
				return null;
		}
		
		public void union(LinkNode x, LinkNode y) {
			LinkNode px = find(x.value);
			LinkNode py = find(y.value);
			py.parent = px;
		}
	}
	
	class LinkNode {
		int value;
		LinkNode parent;
		
		public LinkNode(int i) {
			value = i;
			parent = this;
		}
		
		public LinkNode(int i, LinkNode p) {
			value = i;
			parent = p;
		}
	}
	
	public void resolve() throws NumberFormatException, IOException {
		int[][] parity;
		
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		
		int len = Integer.parseInt(in.readLine());
		while(len != -1l) {
			// reset parity matrix
			parity = new int[len+1][len+1];
			for(int i = 0; i < len+1; i ++)
				for(int j = 0; j < len+1; j ++)
					parity[i][j]=-1;
			
			// read and store guesses
			int len_g = Integer.parseInt(in.readLine());
			guesses = new String[len_g];
			for(int i = 0; i < len_g; i ++) {
				guesses[i] = in.readLine();
			}
			
			// do guess
			int result = 0, i = 0;
			for(i = 0; i < len_g; i ++) {
				String[] guess = guesses[i].split(" ");
//				out.println(guess[0]+":"+guess[1]+" - "+guess[2]);
				int start = Integer.parseInt(guess[0]);
				int end = Integer.parseInt(guess[1]);
				int p = guess[2].equalsIgnoreCase("even")?0:1;	// even:0, odd:1
				
				if(start > end || start > len || end > len) {
					result = i;
					break;
				}
				
				// 1st case, check internal 
				if(parity[start][end] == -1) {
					// DFS search path from start to end
					int[] stack = new int[len];
					int top = -1;
					for(int j = start; j < end; j ++) {
						if(parity[start][j] != -1)
							stack[++top] = j;
					}
					while(top != -1) {
						int ts = stack[top--];
						for(int k = ts + 1; k <= end; k ++) {
							if(parity[ts+1][k] != -1){
								parity[start][k] = parity[start][ts]^parity[ts+1][k];
								stack[++top] = k;
							}
						}
					} // end stack loop
					// if no result, set parity[start][end]
					if(parity[start][end] == -1)
						parity[start][end] = p;
					
				} // end calculate parity[start][end]
				
				if(parity[start][end] != p) {
					result = i;
					break;
				}
				
				// 2nd case, check before and after, to tell the parity of this is valid or not
				int beforeP = -1, afterP = -1, iter = 0, temp = start;
				int beforeIndex = 1, afterIndex = len;
				boolean stop = false;
				while(iter < temp){
					if(parity[iter][temp] != -1){
						temp = iter - 1;
						iter = 0;
						if(beforeP == -1){
							beforeP = parity[iter][temp];
						}
						else {
							beforeP ^= beforeP;
						}
						
						beforeIndex = iter;
						
						if(parity[iter][end] != -1 && parity[iter][end] != (p^beforeP)){
							result = i;
							stop = true;
							break;
						}
					}
					iter ++;
				}
				
				if(stop)
					break;
					
				iter = len;
				temp = end;
				while(iter > temp) {
					if(parity[temp][iter] != -1) {
						temp = iter + 1;
						iter = len;
						if(afterP == -1) {
							afterP = parity[temp][iter];
						} else {
							afterP ^= parity[temp][iter];
						}
						
						afterIndex = iter;
						
						if(parity[start][iter] != -1 && parity[start][iter] != (p^afterP)) {
							result = i;
							stop = true;
							break;
						}
					}
					iter --;
				}
				
				if(stop)
					break;
				
				if(beforeP != -1 
					&& afterP != -1
					&& parity[beforeIndex][afterIndex] != -1
					&& parity[beforeIndex][afterIndex] != (beforeP^p^afterP)) {
					result = i;
					break;
				}
					
					
			} // end for guess
			if(i >= len_g)
				out.println(len_g);
			else
				out.println(result);
			
			len = Integer.parseInt(in.readLine());
		} // end while
		out.flush();
	}
}
