import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String, BankAccount> listOfAccounts = getBankAccountMap(2);


        for (Map.Entry<String, BankAccount> acc: listOfAccounts.entrySet()) {
            System.out.printf("Account Number: %s%nAccount Owner: %s%nCurrent Balance: %.2f%n", acc.getValue().accountNumber, acc.getValue().ownerName, acc.getValue().getBalance());
            if (acc.getValue() instanceof CheckingAccount checkingAcc) {
                System.out.printf("Account Type: %s%nAccount Overdraft Limit: %d%n", checkingAcc.getAccountType().toString(), checkingAcc.getOverdraftLimit());

                //CHECK OVERDRAFT LIMIT AND WITHDRAW
                checkingAcc.withdrawMoney(10_400);
                System.out.printf("Current Balance: %.2f%n", checkingAcc.getBalance());
                //CHECK DEPOSIT
                checkingAcc.deposit(2000);
                System.out.printf("Current Balance: %.2f%n", checkingAcc.getBalance());
            } else {
                SavingAccount savingAcc = (SavingAccount) acc.getValue();
                double intRate = savingAcc.getInterestRate();
                System.out.printf("Account Type: %s%nAccount Interest Rate: %.2f%nInterest Earned: %.2f%n", savingAcc.getAccountType().toString(), intRate, savingAcc.getBalance()/100 * intRate);

                savingAcc.deposit(2000);
                System.out.printf("Current Balance: %.2f%n", savingAcc.getBalance());
            }
            System.out.println();
        }
    }

    private static Map<String, BankAccount> getBankAccountMap(int num) {
        CreateAccount data = new CreateAccount();
        Map<String, BankAccount> listOfAccounts = new HashMap<>();
        String accNum;

        for (int i = 0; i < num; i++) {
            BankAccount b = data.creatingOfAcc();
            b.setBalance(10_000);
            accNum = b.accountNumber;
            listOfAccounts.put(accNum, b);
        }
        data.scanCloser();
        return listOfAccounts;
    }
}