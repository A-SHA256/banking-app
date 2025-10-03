public class CheckingAccount extends BankAccount {
    private int overdraftLimit;
    public CheckingAccount(String accountNumber, String ownerName, AccountType accountType) {
        super(accountNumber, ownerName);
        super.setAccountType(accountType);
        if (accountType == AccountType.STANDARD_CHECKING) {
            this.overdraftLimit = -500;
        } else if (accountType == AccountType.PREMIUM_CHECKING) {
            this.overdraftLimit = -2000;
        }
    }

    public void withdrawMoney(double amount) {
        double limit =  getBalance() - amount;
        if (limit < overdraftLimit) {
            System.out.println("Maximum amount exceeded, try again");
            System.out.println();
        } else {
            setBalance(limit);
        }
    }

    public int getOverdraftLimit() {
        return overdraftLimit;
    }
}
