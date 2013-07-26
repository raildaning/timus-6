package solution.timus;
import java.io.*;
import java.util.*;

public class Timus1002 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Timus1002 resolver = new Timus1002();
		resolver.solve();
	}

	BufferedReader in;
	PrintWriter out;

	char mapTable[] = { '2', '2', '2', '3', '3', '3', '4', '4', '1', '1', '5',
			'5', '6', '6', '0', '7', '0', '7', '7', '8', '8', '8', '9', '9',
			'9', '0' };

	int[] dist;
	int[][] edges;
	String[][] matchedWords;
	String[] dic;
	String[] results;

	public void solve() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		String target = in.readLine();
		while (!target.equalsIgnoreCase("-1")) {
			int n = Integer.parseInt(in.readLine());
			dist = new int[101];
			results = new String[101];
			results[0]="No solution.";
			edges = new int[100][100];
			matchedWords = new String[100][100];
			dic = new String[n];
			int[] flag = new int[100];
			AhoCorasick ac = new AhoCorasick();

			// read and add key words in Trie tree
			for (int i = 0; i < n; i++) {
				dic[i] = in.readLine();
				ac.addKeyword(dic[i]);
			}
			ac.prepare();
			
			// search using the AC automation and set graph edge
			if(search(target,ac)) {
				int size = target.length();
				int head = 0, tail = -1;
				for(int i = 1; i < size + 1; i ++) {
					if(edges[0][i-1] == 1){
						dist[i] = 1;
						results[i] = matchedWords[0][i-1];
//						if(i < size - 1)
							flag[++tail] = i + 1;
					}
					else
						dist[i] = 999;	// INFINITE value
				}
				while(head <= tail) {
					int current = flag[head++] - 1;
					for(int i = current; i < size; i ++) {
						if(edges[current][i] == 1){
							if(dist[current]+1 < dist[i+1]){
								dist[i+1] = dist[current]+1;
								results[i+1] = results[current] + " " + matchedWords[current][i];
//								if(i != size - 1){
									flag[++tail] = i + 2;
//								}
							}
						}
					}
				}
				if(n == 0 && target.length() == 0)
					out.println();
				else {
					if(results[target.length()] != null)
						out.println(results[target.length()]);
					else
						out.println("No solution.");
				}
			} else {
				out.println("No solution.");
			}
			out.flush();
			target = in.readLine();
		}
//		out.flush();
	}
	
	boolean search(String target, AhoCorasick ac) {
		int size = target.length();
		char[] chars = target.toCharArray();
		TrieNode p = ac.root;
		TrieNode temp;
		for(int i = 0; i < size; i ++) {
			while(p.children[chars[i]-'0'] == null && p != ac.root) {
				p = p.fail;
			}
			if(p.children[chars[i]-'0'] != null) {
				p = p.children[chars[i]-'0'];
				if(p.wordEnd) {
					edges[i+1-p.value.length()][i] = 1;
					matchedWords[i+1-p.value.length()][i] = p.value;
				}
				temp = p;
				while(temp != ac.root) {
					temp = temp.fail;
					if(temp.wordEnd){
						edges[i+1-temp.value.length()][i] = 1;
						matchedWords[i+1-temp.value.length()][i] = temp.value;
					}
				}
			} else 
				return false;
		}
		return true;
	}

	class AhoCorasick {
		TrieNode root;

		public AhoCorasick() {
			root = new TrieNode();
		}

		public void addKeyword(String key) {
			int l = key.length();
			TrieNode node = root;
			for(int i = 0; i < l; i ++) {
				node = node.add(key.charAt(i));
			}
			node.wordEnd = true;
		}

		public void prepare() {
			Queue<TrieNode> s = new LinkedList<TrieNode>();
			for(TrieNode n : root.children) {
				if(n != null){
					n.fail = root;
					s.offer(n);
				}
			}
			while(!s.isEmpty()) {
				TrieNode p = s.poll();
				if(p.hasChild) {
					for(TrieNode n : p.children) {
						if(n != null){
							TrieNode f = p.fail;
							while(f != null && f.find(n.value) == null)
								f = f.fail;
							if(f == null)
								n.fail = root;
							else
								n.fail = f.find(n.value);
							if(n.hasChild)
								s.offer(n);
						}
					}
				}
			}
		}

		public TrieNode getRoot() {
			return root;
		}
	}

	class TrieNode {
		public String value="";
		public boolean wordEnd = false;
		TrieNode[] children = new TrieNode[10];
		public boolean hasChild = false;
		public TrieNode fail;

		public TrieNode() {
		}

		public TrieNode(String v) {
			value = v;
		}

		public TrieNode add(char c) {
			char map = mapTable[c-'a'];
			if(children[map-'0'] == null) {
				children[map-'0'] = new TrieNode(value+c);
				hasChild = true;
			}
			return children[map-'0'];
		}
		
		public TrieNode find(String key) {
			char last = key.charAt(key.length()-1);
			last = mapTable[last-'a'];
			return children[last-'0'];
		}
	}

}
