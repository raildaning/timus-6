package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Timus1015 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1015 resolver = new Timus1015();
		resolver.resolve();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void resolve() throws NumberFormatException, IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		Die d1 = new Die(in.readLine());
		HashMap<Integer,Die> categories = new HashMap<Integer,Die>();
		categories.put(1, d1);
		HashMap<Integer,String> results = new HashMap<Integer,String>();
		results.put(1, "1 ");
		for(int i = 2; i < n+1; i ++) {
			Die die = new Die(in.readLine());
			String result = "";
			for(Integer k : categories.keySet()) {
				Die d = categories.get(k);
				if(die.equal(d)) {
					result = results.get(k);
					result += i + " ";
					results.put(k, result);
				}
			}
			if(result.length() == 0){
				// find a new scheme
				categories.put(i, die);
				results.put(i, i+" ");
			}
		} // end for
		out.println(results.size());
		
		ArrayList<Integer> s = new ArrayList<Integer>(results.keySet());
		Collections.sort(s);
		for(int i = 0; i < s.size(); i ++) {
			out.println(results.get(s.get(i)).trim());
		}
		
		out.flush();
	}
	
	class Die {
		HashMap<String,String> scheme = new HashMap<String,String>();
		String clock;
		
		public Die(String s) {
			String[] numbers = s.split(" ");
			scheme.put(numbers[0], numbers[1]);
			scheme.put(numbers[1], numbers[0]);
			scheme.put(numbers[2], numbers[4]);
			scheme.put(numbers[4], numbers[2]);
			scheme.put(numbers[3], numbers[5]);
			scheme.put(numbers[5], numbers[3]);
			if(numbers[0].equals("1")){
				int start = findMin(numbers);
				clock = numbers[(start-2)%4+2]+numbers[(start-1)%4+2]+numbers[start%4+2]+numbers[(start+1)%4+2];
			} else if(numbers[1].equals("1")){
				int start = findMin(numbers);
				clock = numbers[(start-2)%4+2]+numbers[(start+1)%4+2]+numbers[start%4+2]+numbers[(start-1)%4+2];
			} else if(numbers[2].equals("1")){
				String[] rotate = new String[6];
				rotate[0] = numbers[2];
				rotate[1] = numbers[4];
				rotate[2] = numbers[1];
				rotate[3] = numbers[3];
				rotate[4] = numbers[0];
				rotate[5] = numbers[5];
				int start = findMin(rotate);
				clock = rotate[(start-2)%4+2]+rotate[(start-1)%4+2]+rotate[start%4+2]+rotate[(start+1)%4+2];
			} else if(numbers[3].equals("1")){
				String[] rotate = new String[6];
				rotate[0] = numbers[3];
				rotate[1] = numbers[5];
				rotate[2] = numbers[1];
				rotate[3] = numbers[4];
				rotate[4] = numbers[0];
				rotate[5] = numbers[2];
				int start = findMin(rotate);
				clock = rotate[(start-2)%4+2]+rotate[(start-1)%4+2]+rotate[start%4+2]+rotate[(start+1)%4+2];
			} else if(numbers[4].equals("1")){
				String[] rotate = new String[6];
				rotate[0] = numbers[2];
				rotate[1] = numbers[4];
				rotate[2] = numbers[1];
				rotate[3] = numbers[3];
				rotate[4] = numbers[0];
				rotate[5] = numbers[5];
				int start = findMin(rotate);
				clock = rotate[(start-2)%4+2]+rotate[(start+1)%4+2]+rotate[start%4+2]+rotate[(start-1)%4+2];
			} else {
				String[] rotate = new String[6];
				rotate[0] = numbers[3];
				rotate[1] = numbers[5];
				rotate[2] = numbers[1];
				rotate[3] = numbers[4];
				rotate[4] = numbers[0];
				rotate[5] = numbers[2];
				int start = findMin(rotate);
				clock = rotate[(start-2)%4+2]+rotate[(start+1)%4+2]+rotate[start%4+2]+rotate[(start-1)%4+2];
			}
		}
		
		int findMin(String[] s){
			int r = 0;
			int min = 6;
			for(int i = 2; i < 6; i ++){
				if(Integer.parseInt(s[i]) < min){
					min = Integer.parseInt(s[i]);
					r = i;
				}
			}
			return r;
		}
		
		public boolean equal(Die d) {
			for(int i = 1; i < 7; i ++){
				if(!scheme.get(Integer.toString(i)).equals(d.scheme.get(Integer.toString(i))))
					return false;
			}
			if(!clock.equals(d.clock))
				return false;
			return true;
		}
	}
}
