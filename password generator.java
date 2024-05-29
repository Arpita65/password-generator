import java.util.Scanner;

// Alphabet class
class Alphabet {
    public static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "1234567890";
    public static final String SYMBOLS = "!@#$%^&*()-_=+\\/~?";

    private final StringBuilder pool;

    public Alphabet(boolean uppercaseIncluded, boolean lowercaseIncluded, boolean numbersIncluded, boolean specialCharactersIncluded) {
        pool = new StringBuilder();

        if (uppercaseIncluded) pool.append(UPPERCASE_LETTERS);
        if (lowercaseIncluded) pool.append(LOWERCASE_LETTERS);
        if (numbersIncluded) pool.append(NUMBERS);
        if (specialCharactersIncluded) pool.append(SYMBOLS);
    }

    public String getAlphabet() {
        return pool.toString();
    }
}

// Password class
class Password {
    private String value;
    private int length;

    public Password(String s) {
        value = s;
        length = s.length();
    }

    public int charType(char c) {
        if (Character.isUpperCase(c)) return 1;
        if (Character.isLowerCase(c)) return 2;
        if (Character.isDigit(c)) return 3;
        return 4; // Assume any non-alphanumeric character is a symbol
    }

    public int passwordStrength() {
        boolean usedUpper = false;
        boolean usedLower = false;
        boolean usedNum = false;
        boolean usedSym = false;
        int score = 0;

        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            int type = charType(c);

            switch (type) {
                case 1:
                    usedUpper = true;
                    break;
                case 2:
                    usedLower = true;
                    break;
                case 3:
                    usedNum = true;
                    break;
                case 4:
                    usedSym = true;
                    break;
            }
        }

        if (usedUpper) score += 1;
        if (usedLower) score += 1;
        if (usedNum) score += 1;
        if (usedSym) score += 1;

        if (length >= 8) score += 1;
        if (length >= 16) score += 1;

        return score;
    }

    public String calculateScore() {
        int score = passwordStrength();

        if (score == 6) {
            return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        } else if (score >= 4) {
            return "This is a good password :) but you can still do better";
        } else if (score >= 3) {
            return "This is a medium password :/ try making it better";
        } else {
            return "This is a weak password :( definitely find a new one";
        }
    }

    @Override
    public String toString() {
        return value;
    }
}

// Generator class
class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNum, boolean includeSym) {
        alphabet = new Alphabet(includeUpper, includeLower, includeNum, includeSym);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {
            userOption = keyboard.next();

            switch (userOption) {
                case "1":
                    requestPassword();
                    printMenu();
                    break;
                case "2":
                    checkPassword();
                    printMenu();
                    break;
                case "3":
                    printUsefulInfo();
                    printMenu();
                    break;
                case "4":
                    printQuitMessage();
                    break;
                default:
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                    break;
            }
        }
    }

    private Password generatePassword(int length) {
        StringBuilder pass = new StringBuilder();
        String alphabetString = alphabet.getAlphabet();
        int alphabetLength = alphabetString.length();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * alphabetLength);
            pass.append(alphabetString.charAt(index));
        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers, and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences,");
        System.out.println("usernames, relative or pet names, romantic links (current or past),");
        System.out.println("and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void requestPassword() {
        boolean includeUpper = false;
        boolean includeLower = false;
        boolean includeNum = false;
        boolean includeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hello, welcome to the Password Generator :) answer the following questions by Yes or No \n");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) includeLower = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) includeUpper = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) includeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) includeSym = true;

            if (!includeUpper && !includeLower && !includeNum && !includeSym) {
                System.out.println("You have selected no characters to generate your password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password:");
        int length = keyboard.nextInt();

        Generator generator = new Generator(includeUpper, includeLower, includeNum, includeSym);
        Password password = generator.generatePassword(length);

        System.out.println("Your generated password -> " + password);
    }

    private boolean isInclude(String input) {
        return input.equalsIgnoreCase("yes");
    }

    private void PasswordRequestError(String input) {
        if (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        System.out.print("\nEnter your password:");
        String input = keyboard.next();

        Password password = new Password(input);
        System.out.println(password.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program. Bye bye!");
    }
}

// Main class
public class Main {
    public static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Generator generator = new Generator(keyboard);
        generator.mainLoop();
        keyboard.close();
    }
}
