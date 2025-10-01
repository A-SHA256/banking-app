public class SavingAccount extends BankAccount {

    private double interestRate;

    public SavingAccount(String accountNumber, String ownerName, AccountType accountType) {
        super(accountNumber, ownerName);
        super.setAccountType(accountType);
        if (accountType == AccountType.BASIC_SAVINGS) {
            this.interestRate = 1.4;
        } else if (accountType == AccountType.HIGH_SAVINGS) {
            this.interestRate = 2.8;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }
}

