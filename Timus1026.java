package solution.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Timus1026 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		Timus1026 resolver = new Timus1026();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;

	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
//		BF();
		quick();
		out.flush();
	}

	int[] db = new int[5001];

	/***
	 * AC 0.109s
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	void BF() throws NumberFormatException, IOException {
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(in.readLine());
			db[a]++;
		}
		in.readLine();
		int k = Integer.parseInt(in.readLine());
		for (int i = 0; i < k; i++) {
			int q = Integer.parseInt(in.readLine());
			out.println(queryDB(q));
		}
	}

	int queryDB(int q) {
		int c = 0;
		for (int i = 1; i < 5001; i++) {
			if (db[i] != 0)
				c += db[i];
			if (c >= q)
				return i;
		}
		return -1;
	}

	/***
	 * build binary search tree once and use it for all queries
	 * need improve
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	void quick() throws NumberFormatException, IOException {
		int n = Integer.parseInt(in.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(in.readLine());
			db[a]++;
		}
		Node root = null;
		AVLTree tree = new AVLTree();
		int key = 0;
		for (int i = 1; i < 5001; i++) {
			if (db[i] != 0){
				key += db[i];
				root = tree.insert(key, i, root);
			}
		}
		in.readLine();
		int k = Integer.parseInt(in.readLine());
		for (int i = 0; i < k; i++) {
			int q = Integer.parseInt(in.readLine());
			out.println(tree.search(q, root));
		}
	}

	class Node {
		Node left;
		Node right;
		int height;
		int data;
		int ref;
	}

	class AVLTree {
		int search(int q, Node root) {
			if(root.data == q)
				return root.ref;
			else if(root.left == null && q < root.data)
				return root.ref;
			else if(q < root.data)
				return search(q, root.left);
			else if(q > root.data)
				return search(q, root.right);
			return -1;
		}

		int heigth(Node t) {
			if (t == null) {
				return 0;
			}
			return t.height;
		}

		Node rightRotate(Node a) {
			Node b = a.left;
			a.left = b.right;
			b.right = a;
			a.height = max(heigth(a.left), heigth(a.right));
			b.height = max(heigth(b.left), heigth(b.right));
			return b;
		}

		Node leftRotate(Node a) {
			Node b = a.right;
			a.right = b.left;
			b.left = a;
			a.height = max(heigth(a.left), heigth(a.right));
			b.height = max(heigth(b.left), heigth(b.right));
			return b;
		}

		Node leftRightRotate(Node a) {
			a.left = leftRotate(a.left);
			return rightRotate(a);
		}

		Node rightLeftRotate(Node a) {
			a.right = rightRotate(a.right);
			return leftRotate(a);
		}

		Node insert(int data, int ref, Node t) {
			if (t == null) {
				t = newNode(data, ref);
			} else if (data < t.data) {
				t.left = insert(data, ref, t.left);
				if (heigth(t.left) - heigth(t.right) == 2) {
					if (data < t.left.data) {
						t = rightRotate(t);
					} else {
						t = leftRightRotate(t);
					}
				}
			} else {
				t.right = insert(data, ref, t.right);
				if (heigth(t.right) - heigth(t.left) == 2) {
					if (data > t.right.data) {
						t = leftRotate(t);
					} else {
						t = rightLeftRotate(t);
					}
				}
			}
			t.height = max(heigth(t.left), heigth(t.right));
			return t;
		}

		Node newNode(int data, int ref) {
			Node a = new Node();
			a.left = a.right = null;
			a.height = 0;
			a.data = data;
			a.ref = ref;
			return a;
		}

		int max(int heigth, int heigth2) {
			return heigth > heigth2 ? heigth : heigth2;
		}

	}
}
