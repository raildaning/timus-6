package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Timus1019 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		Timus1019 resolver = new Timus1019();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;

	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		BF();
		out.flush();
	}

	void BF() throws NumberFormatException, IOException {
		int N = Integer.parseInt(in.readLine().trim());
		Seg bs = new Seg(0, 0);
		Seg ws = new Seg(0, 1000000000);
		for (int i = 0; i < N; i++) {
			String[] paint = in.readLine().split(" ");
			int begin = Integer.parseInt(paint[0]);
			int end = Integer.parseInt(paint[1]);
			if (paint[2].equalsIgnoreCase("b")) {
				// paint black
//				bs.add(begin, end);
				ws.remove(begin, end);
//				bs.clean();
				ws.clean();
			} else {
				// paint white
				ws.insert(begin, end);
//				bs.delete(begin, end);
//				bs.clean();
				ws.clean();
			}
		}
		int max = ws.end - ws.start;
		int sout = ws.start, eout = ws.end;
		while(ws.hasNext()){
			ws = ws.next;
			if(max < (ws.end - ws.start)){
				max = ws.end - ws.start;
				sout = ws.start;
				eout = ws.end;
			}
		}
		out.printf("%d %d\n", sout, eout);
	}

	class Seg {
		int start, end;
		Seg next = null;

		public Seg(int s, int e) {
			start = s;
			end = e;
		}

		boolean hasNext() {
			return !(next == null);
		}
		
		void clean() {
			if(start == 0 && end == 0)
				if(hasNext()){
					start = next.start;
					end = next.end;
					next = next.next;
				}
			
			Seg temp = this;
			boolean stop = true;
			while(temp != null && temp.hasNext()){
				
				if(temp.end == temp.next.start){
					temp.end = temp.next.end;
					temp.next = temp.next.next;
					stop = false;
				}
				else if(temp.start == 0 && temp.end == 0){
					if(temp.hasNext()){
						temp.start = temp.next.start;
						temp.end = temp.next.end;
						temp.next = temp.next.next;
					}
				}
				temp = temp.next;
			}
			if(!stop)
				clean();
		}
		
		void insert(int s, int e) {
			if (start <= s && end >= e) {
				// do nothing
			}
			else if (start >= e) {
				Seg temp = new Seg(start, end);
				temp.next = next;
				this.start = s;
				this.end = e;
				this.next = temp;
			}
			else if(start >= s) {
				this.start = s;
				if(end <= e)
					this.end = e;
				else if(hasNext()) {
					next.insert(end, e);
				} 
			}
			else if(end > s && end < e) {
				if(hasNext()){
					next.insert(end, e);
				} else {
					this.end = e;
				}
			}
			else if (end <= s) {
				if (hasNext())
					next.insert(s, e);
				else
					next = new Seg(s, e);
			}
		}
		
		void remove(int s, int e) {
			if(start >= e) {
				// do nothing
			}
			else if(start <= s && end >= e) {
				if(start == s && end == e) {
					start = 0;
					end = 0;
				} else if(start == s) {
					start = e;
				} else if(end == e) {
					end = s;
				} else {
					Seg tmp = new Seg(e,end);
					this.end = s;
					tmp.next = next;
					next = tmp;
				}
			}
			else if(start >= s) {
				if(end > e) {
					start = e;
				} else if(end == e) {
					start = 0;
					end = 0;
				} else {
					start = 0;
					end = 0;
					if(hasNext())
						next.remove(end, e);
				}
			}
			else if(end > s && end < e) {
				end = s;
				if(hasNext()) {
					next.remove(s, e);
				} 
			}
			else if (end <= s) {
				if(hasNext())
					next.remove(s, e);
			}
		}
	}
}
