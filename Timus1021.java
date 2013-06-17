package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Timus1021 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1021 resolver = new Timus1021();
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
		int[] hit = new int[65537];
		int n1,n2;
		boolean yes = false;
		n1 = Integer.parseInt(in.readLine());
		for(int i = 0; i < n1; i ++) {
			int temp = Integer.parseInt(in.readLine());
			if(temp<-22768)
				continue;
			hit[10000-temp+32768]=1;
		}
		n2 = Integer.parseInt(in.readLine());
		for(int i = 0; i < n2; i ++) {
			int temp = Integer.parseInt(in.readLine());
			if(hit[temp+32768] == 1){
				yes = true;
				break;
			}
		}
		if(yes)
			out.println("YES");
		else
			out.println("NO");
	}
}
