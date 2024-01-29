import java.util.Arrays;

/*
 * A version of the Scrabble game.
 */
public class Scrabble {
    // Note: "Class variables", like the following five class-level variables,
    // are global variables that can be accessed by all the functions in the class.
    // It is customary to name class variables using capital letters and underline
    // characters. If a variable is "final", it means that it is treated as a
    // constant value which is declared once and cannot changed later.

    // Name of the dictionary file:
    static final String WORDS_FILE = "/Users/tltnynyb/Desktop/scrabble/scrabble/src/dictionary.txt";

    // Number of words in the dictionary file (assumed to be at most 100,000):
    static int NUM_OF_WORDS = 0;

    // The dictionary array (will be read from the dictionary file)
    static String[] DICTIONARY = new String[83667];

    // The "Scrabble value" of each letter in the English alphabet
    static final int[] SCRABBLE_LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

    // The hand size (number of random letters dealt at each round of the Scrabble game):
    static int HAND_SIZE = 10;


    private static String lowercase(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            int letterV = (int) letter;
            if (letterV <= (int) 'Z' && letterV >= (int) 'A') {
                int lowerV = letterV + 32;
                char lower = (char) lowerV;
                res = res + lower;
            } else {
                res = res + letter;
            }
        }
        return res;
    }

    /**
     *  Initializes the game by performing the following SIDE EFFECTS:
     *  1. Populates the DICTIONARY array with all the words found in the WORDS_FILE.
     *     Each word is stored in its lowercase version.
     *  2. Sets NUM_OF_WORDS to the number of words found in the file.
     *  3. Sets standard input to the keyboard.
     */
    public static void init() {
        // Sets the standard input stream to the given word file
        StdIn.setInput(WORDS_FILE);
        System.out.println("Loading word list from file...");
        // Reads all the words from the file into the DICTIONARY array
        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            DICTIONARY[NUM_OF_WORDS] = lowercase(line);
            NUM_OF_WORDS++;
        }
        System.out.println(NUM_OF_WORDS + " words loaded.");
        // Sets the standard input stream to the keyboard, to allow interaction with the user.
        StdIn.setInput("keyboard");
    }

    /**
     * Returns the Scrabble score of a given word.
     * The score of a word is the sum of the points of the letters in the word,
     * multiplied by the length of the word, plus 50 points if the length of the word is n.
     *
     * @param word - a lowercase string of letters
     * @param n - a given integer
     * @return - the Scrabble value of the word
     */
    public static int getWordScore(String word, int n) {
        word = lowercase(word);
        int sum = 0;
        int ans = 0;
        if (word.length() < n) {
            for (int i = 0; i < word.length(); i++) {
                sum += SCRABBLE_LETTER_VALUES[word.charAt(i) - 97];
            }
            ans = sum * word.length();
        }
        if (word.length() == n) {
            for (int i = 0; i < word.length(); i++) {
                sum += SCRABBLE_LETTER_VALUES[word.charAt(i) - 97];
            }
            ans = (sum * word.length()) + 50;
        }
        return ans;
    }


    /**
     * Runs a single hand in a Scrabble game. The hand starts with n letters.
     *
     * @param hand - the hand
     */
    public static void playHand(String hand) {
        int handLen = hand.length();
        int scoreHand = 0;
        while (handLen > 0) {
            System.out.println("Current hand: " + MyString.spacedString(hand));
            System.out.println("Enter a word, or '.' to finish this hand:");
            String ans = StdIn.readString();
            if (ans.equals(".")) {
                break;
            }
            if (!MyString.subsetOf(ans, hand)) {
                System.out.println("Invalid word. Try again");
            }
            if (MyString.subsetOf(ans,hand)) {
                if (isWordInDictionary(ans,DICTIONARY)) {
                    int scoreInput = getWordScore(ans, handLen);
                    scoreHand += scoreInput;
                    System.out.println(ans + " earned " + scoreInput + " points. Total: " + scoreHand + " points");
                    hand = MyString.remove(hand,ans);
                    handLen = hand.length();
                } else {
                    System.out.println("No such word in the dictionary. Try again");
                }
            }
            System.out.println();
        }
        if (handLen == 0) {
            System.out.println("Ran out of letters. Total score: " + scoreHand + " points.");
        } else {
            System.out.println("End of hand. Total score: " + scoreHand + " points.");
        }
    }


    /**
     * 	Initializes the game, and then allows the user to play an arbitrary number of hands.
     *
     *  1) Asks the user to input 'n' or 'r' or 'e'.
     * 		- If the user inputs 'n', lets the user play a new (random) hand.
     * 		- If the user inputs 'r', lets the user play the last hand again
     *                                (works only if this is not the first hand).
     * 		- If the user inputs 'e', exits the game.
     * 		- If the user inputs anything else, writes that the input is invalid.
     *
     *  2) When the user is done playing the hand, repeats from step 1.
     */
    public static void playGame() {
        String hand = "";
        System.out.println("Enter n to deal a new hand, r to replay the last hand, or e to end game:");
        String answer = StdIn.readString();
        while (!answer.equals("e")) {
            if (answer.equals("n")) {
                hand = MyString.randomStringOfLetters(HAND_SIZE);
                playHand(hand);
            }
            if (answer.equals("r") && !hand.equals("")) {
                playHand(hand);
            }
            if (answer.equals("r") && hand.equals("")) {
                System.out.println("You have not played a hand yet." +
                        " Please play a new hand first!");
            }
            if (!answer.equals("n") && !answer.equals("r")) {
                System.out.println("Invalid command.");
            }
            System.out.println("Enter n to deal a new hand, r to replay the last hand, or e to end game:");
            answer = StdIn.readString();
            }
            }

    // Checks if the given word is in the given dictionary.
    private static boolean isWordInDictionary(String word, String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            if (word.equals(dictionary[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        init();
        //testPlayHand();
        playGame();
    }

    public static void testPlayHand() {
        playHand("pzuttto");
        playHand("aqwffip");
        playHand("aretiin");
    }
}
