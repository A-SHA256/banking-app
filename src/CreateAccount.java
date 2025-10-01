import java.util.Random;
import java.util.Scanner;

public class CreateAccount {
    private final Scanner sc = new Scanner(System.in);

    private final StringBuilder accountNumber = new StringBuilder();
    private String ownerName;
    private AccountType accountType;

    private static String promptForOption(Scanner sc, String message, String[] validOptions) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextLine()) {
                String input = sc.nextLine().trim();
                if (!input.isEmpty()) {
                    for (String option : validOptions) {
                        if (input.equalsIgnoreCase(option)) {
                            return option;
                        }
                    }
                    System.out.println("Incorrect option, try again.");
                } else {
                    System.out.println("Empty input, try again.");
                }
            } else {
                System.out.println("No input detected, try again.");
            }
        }
    }

    private static boolean checkName(String s) {
        boolean correct = false;

        String[] arr = s.split(" ");

        boolean nameAndSurnameCheck = arr.length <= 3 && arr.length >= 2;
        boolean notContainDigits = !s.matches(".*\\d.*");
        if (nameAndSurnameCheck && notContainDigits) correct = true;
        
        for (String word : arr) {
            word = word.trim();
            if (!String.valueOf(word.charAt(0)).toUpperCase().equals(String.valueOf(word.charAt(0)))) {
                correct = false;
                break;
            }
            String[] wArr = word.split("");
            for (int i = 1; i < wArr.length - 1; i++) {
                if (wArr[i].toUpperCase().equals(wArr[i])) {
                    correct = false;
                    break;
                }
            }
        }

        return correct;
    }

    private void input(){
        while (true) {
            System.out.print("Enter your name and surname as they are in your ID card/Passport: ");
            if (sc.hasNextLine()) {
                ownerName = sc.nextLine().trim();
                if (!ownerName.isEmpty()) {
                    if (checkName(ownerName)) {
                        break;
                    }
                    System.out.println("Incorrect name, try again.");
                } else {
                    System.out.println("Empty input, try again.");
                }
            } else {
                System.out.println("No input detected. Please try again.");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.print("Please choose your account type('S' for Saving account/'C' for Checking account): ");
            if(sc.hasNextLine()) {
                String choiceOfAccount = sc.nextLine().trim();
                String plan;
                if (!choiceOfAccount.isEmpty()) {
                    if (choiceOfAccount.equalsIgnoreCase("s")) {
                        plan = promptForOption(sc, "Please choose your saving account plan('B' for Basic/'H' for High): ", new String[]{"B","H"});
                        accountType = plan.equalsIgnoreCase("b") ? AccountType.BASIC_SAVINGS : AccountType.HIGH_SAVINGS;
                        break;
                    } else if (choiceOfAccount.equalsIgnoreCase("c")) {
                        plan = promptForOption(sc, "Please choose your checking account plan('S' for Standard/'P' for Premium): ", new String[]{"S","P"});
                        accountType = plan.equalsIgnoreCase("s") ? AccountType.STANDARD_CHECKING : AccountType.PREMIUM_CHECKING;
                        break;
                    } else {
                        System.out.println("Incorrect option, try again.");
                    }
                } else {
                    System.out.println("Empty input, try again.");
                }
            } else {
                System.out.println("No input detected. Please try again.");
                sc.nextLine();
            }
        }
        System.out.println();
    }
    public void scanCloser() {
        sc.close();
    }
    public BankAccount creatingOfAcc() {
        BankAccount acc;
        input();

        char[] converting = ownerName.toCharArray();
        int i = 0;
        while (accountNumber.length() < 16) {
            if (i < converting.length) {
                accountNumber.append((int) converting[i]);
            } else {
                Random r = new Random();
                accountNumber.append(r.nextInt(9));
            }
            i++;
        }

        if (accountNumber.length() > 16) {
            accountNumber.setLength(16);
        }

        // CREATING NEW ACCOUNT
        if (accountType == AccountType.STANDARD_CHECKING || accountType == AccountType.PREMIUM_CHECKING) {
            acc = new CheckingAccount(accountNumber.toString(), ownerName, accountType);
        } else {
            acc = new SavingAccount(accountNumber.toString(), ownerName, accountType);
        }

        accountNumber.setLength(0);
        return acc;
    }


}
