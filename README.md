# password-generator
password generator

Alphabet class:
This class represents the set of characters that can be used to generate passwords.
It has four static final strings: UPPERCASE_LETTERS, LOWERCASE_LETTERS, NUMBERS, and SYMBOLS, which represent the different types of characters that can be used.
The class has a constructor that takes four boolean parameters, which determine whether to include uppercase letters, lowercase letters, numbers, and symbols in the password generation.
The getAlphabet() method returns a string that combines the selected character types.

Password class:
This class represents a password and provides methods to calculate its strength.
It has a constructor that takes a string parameter, which is the password to be checked.
The charType() method determines the type of a given character (uppercase, lowercase, digit, or symbol).
The passwordStrength() method calculates the strength of the password based on the types of characters used and the length of the password.
The calculateScore() method returns a string that describes the strength of the password.

Generator class:
This class is responsible for generating passwords and providing a menu-driven interface for the user.
It has a constructor that takes a Scanner object as a parameter, which is used to read user input.
The mainLoop() method is the main entry point of the program, which displays a menu and processes user input.
The generatePassword() method generates a password based on the selected character types and length.
The requestPassword() method prompts the user to select the character types and length for the password generation.
The checkPassword() method checks the strength of a user-input password.
The printUsefulInfo() method displays useful information about password generation and security.
The printMenu() method displays the main menu.
The printQuitMessage() method displays a quit message when the user exits the program.

Main class:
This class is the entry point of the program, which creates a Generator object and starts the mainLoop() method.
The program provides the following features:

Password generation: The user can select the character types and length to generate a password.
Password strength checking: The user can input a password to check its strength.
Useful information: The program displays useful information about password generation and security.
Menu-driven interface: The program provides a menu-driven interface for the user to interact with.
