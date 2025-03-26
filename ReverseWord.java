import java.io.*;

public class ReverseWord {
    public static void main(String[] args) throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter a word: ");
        String word = reader.readLine();
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for (int i = word.length() - 1 ; i >= 0; i--) {
            writer.write(word.charAt(i));
        }
        
        reader.close();
        writer.close();
    }
}