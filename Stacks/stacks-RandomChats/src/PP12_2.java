import java.util.*;

public class PP12_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder reverse;
		
		System.out.println("Enter a sentence to get it's words reversed!");
		String sentence = scan.nextLine();
		
		String[] splitWords = sentence.split(" ");

		Stack<String> userWords = new Stack<String>();
		userWords.push("Original Sentence: " + sentence);
		
		for(int i=0; i < splitWords.length; i++) {
			reverse = new StringBuilder();
			reverse.append(splitWords[i]);
			String word = reverse.reverse().toString();
			userWords.push(word);
		}
		System.out.println(userWords);

	}

}
