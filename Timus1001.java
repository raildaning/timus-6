package solution.timus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;


public class Timus1001 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Timus1001 resolver = new Timus1001();
		resolver.resolve();
	}

	StreamTokenizer token;
	PrintWriter out;
	
//	private int nextVal() throws IOException {
//		token.nextToken();
//		return (int)token.nval;
//	}
	
	public void resolve() throws IOException {
		token = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		out = new PrintWriter(System.out);
		
		double[] params = new double[200000];
		int index = 0;
		
		token.nextToken();
		while(token.ttype != StreamTokenizer.TT_EOF) {
			if(token.ttype == StreamTokenizer.TT_NUMBER) {
				params[index++] = token.nval;
			}
			token.nextToken();
		}
		while(index > 0) {
			out.println(new DecimalFormat("0.0000").format(Math.sqrt(params[--index])));
		}
		out.flush();
	}
}
