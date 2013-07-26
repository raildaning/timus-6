package solution.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class Timus1027 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		Timus1027 resolver = new Timus1027();
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

	char[] text = new char[10001];

	void BF() throws IOException {
		int n = in.read(text);
		Stack<Integer> arith = new Stack<>();
		boolean inComm = false;
		for (int i = 0; i < n; i++) {
			if (!inComm) {
				// normal text
				if (text[i] == '(' && text[i + 1] == '*') {
					i++;
					inComm = true;
				} else if (text[i] == '(') {
					arith.push(i);
				} else if (text[i] == ')') {
					if (arith.isEmpty()) {
						out.println("NO");
						return;
					} else {
						arith.pop();
					}
				} else {
					if (!arith.isEmpty()) {
						// only =+-*/0123456789)( and 'end of line' is
						// acceptable
						if(text[i]==13 && text[i+1] == 10)
							i++;
						else if(!isAcceptable(text[i])){
							out.println("NO");
							return;
						}
					}
				}
			} else {
				// in comment, wait for *) to exit
				if (text[i] == '*' && text[i + 1] == ')') {
					i++;
					inComm = false;
				}
			}
		} // end for
		
		if(!inComm && arith.isEmpty())
			out.println("YES");
		else
			out.println("NO");

	}

	boolean isAcceptable(char c) {
		switch(c) {
		case '=':
		case '+':
		case '-':
		case '*':
		case '/':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}

	}
}
