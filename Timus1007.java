package solution.timus;
import java.io.*;

public class Timus1007 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Timus1007 resolver = new Timus1007();
		resolver.BruteForce();
	}

	BufferedReader in;
	PrintWriter out;
	
	int N;
	
	
	public Timus1007() throws NumberFormatException, IOException{
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		N = Integer.parseInt(in.readLine().trim());
	}
	
	public void BruteForce() throws IOException {
		String word  = in.readLine();
		String result = "";
		while(word != null) {
			word = word.trim();
			// check length
			if(word.length() == N) { 
				// case 1
				int checksum = 0;
				for(int j = 0; j < N; j ++) {
					if(word.charAt(j) == '1') {
						checksum += (j+1);
					}
				}
				int dirtyIndex = checksum % (N+1);
				if(dirtyIndex != 0)
					result = word.substring(0, dirtyIndex-1)+"0"+word.substring(dirtyIndex, N);
				else
					result = word;
				
				out.println(result);
			} else if(word.length() < N) {
				// case 2
				int checksum = 0;
				for(int i = 0; i <= N-1; i ++){
					for(int j = 0; j < N-1; j ++){
						if(word.charAt(j) == '1'){
							if(j>=i)
								checksum += (j+2);
							else
								checksum += (j+1);
						}
					}
					if(checksum % (N+1) == 0){
						result = word.substring(0,i)+"0"+word.substring(i);
						break;
					} else if((checksum+i+1)%(N+1) == 0) {
						result = word.substring(0,i)+"1"+word.substring(i);
						break;
					}
					checksum = 0;
				} // end for guess
				out.println(result);
			} else {
				// case 3
				int checksum = 0;
				for(int i = 0; i <= N+1; i ++){
					for(int j = 0; j < N+1; j ++) {
						if(word.charAt(j) == '1' && j != i) {
							if(j >= i)
								checksum += j;
							else
								checksum += (j+1);
						}
					}
					
					if(checksum % (N+1) == 0){
						result = word.substring(0,i)+word.substring(i+1);
						break;
					}
					checksum = 0;
				} // end for guess
				out.println(result);
			}
			word  = in.readLine();
		} // end while
		out.flush();
	}
}
