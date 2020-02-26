package be.pxl.student.util;

import be.pxl.student.entity.Account;

public class AccountMapper {

    public Account map(String validLine) throws InvalidPaymentException {
        String[] splitted = validLine.split(",");
        if (splitted.length != 7) {
            throw new InvalidPaymentException("Invalid number of fields in line.");
        }
        Account account = new Account();
        account.setName(splitted[0]);
        account.setIBAN(splitted[1]);
        return account;
    }
}
