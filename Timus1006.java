package solution.timus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Timus1006 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Timus1006 resolver = new Timus1006();
		resolver.BruteForce();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void BruteForce() throws IOException {
//		in = new BufferedReader(new InputStreamReader(System.in));
		in = new BufferedReader(new FileReader(new File("1006I1.txt")));
		out = new PrintWriter(System.out);
		String[] lines = new String[20];
		String line = in.readLine();
		int i = 0, j = 0;
		while(line != null){
			lines[i++] = line;
			line = in.readLine();
		}
		
		// process
		int[][] flag = new int[50][20];
		int seq = 1;
		Queue<Square> queue = new LinkedList<Square>();
		for(i = 0; i < 20; i ++){
			line = lines[i];
			for(j = 0; j < 50; j ++){
				char cursor = line.charAt(j);
				Square mode;
				
				if(cursor == '┌') {
					Square s = new Square(j,i,seq++);
					queue.offer(s);
					flag[j][i] = seq;
					mode = s;
				}
			}
		}
		out.flush();
	}
	
	class Square {
		int x, y, width;
		int no;
		
		public Square(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.no = n;
			width = 0;
		}
	}
}
