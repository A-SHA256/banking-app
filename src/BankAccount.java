public class BankAccount {
    public String accountNumber;
    public String ownerName;
    private double balance;
    private AccountType accountType;

    public BankAccount(String accountNumber, String ownerName){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    void setBalance(double balance) {
        this.balance = balance;
    }
    double getBalance() {
        return balance;
    }

    void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    AccountType getAccountType() {
        return accountType;
    }
}
