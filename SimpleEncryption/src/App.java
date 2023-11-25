import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // create variables
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        String word = sc.nextLine();
        keyword = keyword.toUpperCase();
        word = word.toUpperCase();
        // convert into char arrays to make letter shifting easier
        char[] OGkeywordArr = keyword.toCharArray();
        char[] wordArr = word.toCharArray();
        byte numOfSpecialChar = 0;

        // remove all special characters
        for (int i = 0; i < wordArr.length; i++) {
            if ((int) wordArr[i] > 90 || (int) wordArr[i] < 65) {
                // special character
                wordArr[i] = ' ';
                numOfSpecialChar++;
            }
        }
        int l = 0;
        char[] letterWord = new char[wordArr.length - numOfSpecialChar];
        for (int j = 0; j < wordArr.length; j++) {
            // if not special character, copy in to the new letterWord array
            if (wordArr[j] != ' ') {
                letterWord[l] = wordArr[j];
                l++;
            }
        }

        // remove all special characters from keyword
        l =0; numOfSpecialChar =0;    
        for (int i = 0; i < OGkeywordArr.length; i++) {
            if ((int) OGkeywordArr[i] > 90 || (int) OGkeywordArr[i] < 65) {
                // special character
                OGkeywordArr[i] = ' ';
                numOfSpecialChar++;
            }
        }
        char[] keywordArr = new char[OGkeywordArr.length - numOfSpecialChar];
        for (int j = 0; j < OGkeywordArr.length; j++) {
            // if not special character, copy in to the new letterWord array
            if (OGkeywordArr[j] != ' ') {
                keywordArr[l] = OGkeywordArr[j];
                l++;
            }
        }

        // split the word into ints columns. (# of columns = keyword.length())
        // copy array in a 2d array
        // the # of rows is the "spillover" of letters when you divide by the # of
        // columns
        // if you cant fit the rows fully, you need another, hence the ternary operation
        // for row calculation below.
        
        int rows = letterWord.length % (keywordArr.length) == 0 ? letterWord.length / (keywordArr.length)
                : letterWord.length / (keywordArr.length) + 1;
        int columns = keywordArr.length;
        char[][] word2DArr = new char[rows][columns];
        // temp variable to iterate through original
        int k = 0;
        // temp variable to make sure array does not go out of bounds (The blank spaces
        // of the 2D array that are needed when column overflows)
        int p = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (p > letterWord.length - 1) {
                    // reached end of valid letters, copy in ' ' for the rest of the 2d array to
                    // indicate that this row/column combination is blank due to spillover
                    word2DArr[i][j] = ' ';
                } else {
                    // copy in word array with "spillover"
                    word2DArr[i][j] = letterWord[k];
                    k++;
                }
                p++;
            }
        }
        // keep track of how much each letter shifts in the keyword at each element
        int[] letterShift = new int[keywordArr.length];
        // calculate lettershifts for each letter
        for (int i = 0; i < letterShift.length; i++) {
            // if keyword letter in the column is A -> shift is 0, B is 1, C is 2 ....
            letterShift[i] = (int) keywordArr[i] - 65;
        }

        // shift letters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (word2DArr[i][j] == ' ') {
                    // skip because it is blank, end loop bc this means we are at the end of our
                    // table (blank characters in last row due to spillover)
                    break;
                }
                // shift letter based on what letter in the column is
                if ((int) word2DArr[i][j] + letterShift[j] > 90) {
                    word2DArr[i][j] = (char) (64 + (int) word2DArr[i][j] + letterShift[j] - 90);
                } else {
                    word2DArr[i][j] = (char) ((int) word2DArr[i][j] + letterShift[j]);
                }

            }
        }
        // print out 2D char array
        for (int i = 0; i < word2DArr.length; i++) {
            for (int j = 0; j < word2DArr[i].length; j++) {
                if (word2DArr[i][j] != ' ') {
                    System.out.print(word2DArr[i][j]);
                }
            }
        }
    }
}
