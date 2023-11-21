import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // create variables
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        String word = sc.nextLine();
        // convert into char arrays to make letter shifting easier
        char[] keywordArr = keyword.toCharArray();
        char[] wordArr = word.toCharArray();
        // split the word into ints columns. (# of columns = keyword.length())
        // copy array in a 2d array
        // the # of rows is the "spillover" of letters when you divide by the # of
        // columns
        // if you cant fit the rows fully, you need another, hence the ternary operation
        // for row calculation below.
        int rows = wordArr.length % (keywordArr.length) != 0 ? wordArr.length % (keywordArr.length)
                : wordArr.length / (keywordArr.length) + 1;
        int columns = keywordArr.length;
        char[][] word2DArr = new char[rows][columns];
        // temp variable to iterate through original
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // copy in word array with "spillover"
                word2DArr[i][j] = wordArr[k];
                k++;
            }
        }

        // keep track of how much each letter shifts in the keyword at each element
        int[] letterShift = new int[keywordArr.length];
        // calculate lettershifts for each letter
        for (int i = 0; i < letterShift.length; i++) {
            // if keyword letter in the column is A -> shift is 0, B is 1, C is 2 ....
            letterShift[i] = (int) keywordArr[i] - 65;
        }

        // shift and remove letters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Make sure it is between A and Z
                if (((int) word2DArr[i][j] - 65) > 25 || ((int) word2DArr[i][j] - 65) < 0) {
                    // leave it blank and remove later in the full encoded word
                    word2DArr[i][j] = ' ';
                } else {
                    // shift letter based on what letter in the column is
                    word2DArr[i][j] = (char) ((int) word2DArr[i][j] + letterShift[j]);
                }
            }

            // create an encoded version of the original word
            
            String temp = word2DArr.toString();
            char[] encodedWord = temp.toCharArray();
            for (int j = 0; j < encodedWord.length; j++) {
                if(encodedWord[j] == ' '){
                    //do nothing
                }else System.out.print(encodedWord[j]);
            }
        }

    }
}
