package org.example;

import org.example.core.Bank;
import org.example.core.Customer;
import org.example.core.StatusTypes;
import org.example.exeprions.CustomerNotExist;
import org.example.exeprions.UnActiveAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    private final MockNotificationFactory notificationFactory = new MockNotificationFactory();
    private final Bank bank = new Bank(notificationFactory);

    @Test
    public void if_customer_name_equal_null_return_false() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        String name = customer.getName();

        Assertions.assertEquals("eiad", name);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void if_customer_mobile_equal_null_return_false() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        String mobile = customer.getMobile();

        Assertions.assertEquals("0797357845", mobile);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void if_customer_emil_equal_null_return_false() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        String emil = customer.getEmil();

        Assertions.assertEquals("eiadnimer_swaidat@yahoo.com", emil);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void if_account_number_equal_zero_return_false() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        int accountNumber = customer.getAccountNumber();

        Assertions.assertEquals(1, accountNumber);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_default_balance_for_the_account_should_be_zero() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        int balance = customer.getBalance();

        Assertions.assertEquals(0, balance);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void account_number_should_be_unique_to_each_customer_in_the_bank() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumber1 = customer1.getAccountNumber();
        int accountNumber2 = customer2.getAccountNumber();

        Assertions.assertNotEquals(accountNumber1, accountNumber2);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void an_default_notification_should_send_to_the_customer_after_he_opens_an_account() {
        bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        boolean notificationWasSent = notificationFactory.getNotification().isCalled();

        Assertions.assertTrue(notificationWasSent);
    }

    @Test
    public void the_default_status_for_the_account_should_be_active() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        StatusTypes status = customer.getStatus();

        Assertions.assertEquals(status, StatusTypes.ACTIVE);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_customer_can_change_his_status_from_the_bank() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        StatusTypes status = customer.getStatus();
        int accountNumber = customer.getAccountNumber();
        bank.changeStatus(accountNumber, StatusTypes.UnACTIVE);

        Assertions.assertNotEquals(status, StatusTypes.UnACTIVE);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }


    @Test
    public void credit_money_to_customer_balance_from_the_customer_should_be_correct() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        int accountNumber = customer.getAccountNumber();
        bank.creditMoney(accountNumber, 10);
        int balance = customer.getBalance();

        Assertions.assertEquals(balance, 10);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_customer_can_not_credit_money_if_status_was_deActive() {
        Customer customer = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");

        int accountNumber = customer.getAccountNumber();
        bank.changeStatus(accountNumber, StatusTypes.UnACTIVE);

        Assertions.assertThrows(UnActiveAccount.class, () -> bank.creditMoney(accountNumber, 10));
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void to_transfer_money_from_customer_to_customer_should_both_be_exist_in_the_bank() {
        int accountNumberSender = 1;
        int accountNumberReceiver = 2;

        Assertions.assertThrows(CustomerNotExist.class, () -> bank.transferMoney(accountNumberSender, accountNumberReceiver, 50));

    }

    @Test
    public void the_sender_should_have_enough_money_to_make_transfer_to_other_customer() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();

        Assertions.assertThrows(IllegalArgumentException.class, () -> bank.transferMoney(accountNumberSender, accountNumberReceiver, 50));
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_status_for_sender_in_transfer_money_should_be_active() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();
        bank.creditMoney(accountNumberSender, 60);
        customer1.setStatus(StatusTypes.UnACTIVE);

        Assertions.assertThrows(UnActiveAccount.class, () -> bank.transferMoney(accountNumberSender, accountNumberReceiver, 50));
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_status_for_receiver_in_transfer_money_should_be_active() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();
        bank.creditMoney(accountNumberSender, 60);
        customer2.setStatus(StatusTypes.UnACTIVE);

        Assertions.assertThrows(UnActiveAccount.class, () -> bank.transferMoney(accountNumberSender, accountNumberReceiver, 50));
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_money_should_debit_from_sender_account_after_transfer_money_correctly() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();
        bank.creditMoney(accountNumberSender, 60);
        bank.transferMoney(accountNumberSender, accountNumberReceiver, 60);
        int balance = customer1.getBalance();

        Assertions.assertEquals(balance, 0);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_money_should_credit_to_receiver_account_after_transfer_money_correctly() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();
        bank.creditMoney(accountNumberSender, 60);
        bank.transferMoney(accountNumberSender, accountNumberReceiver, 60);
        int balance = customer2.getBalance();

        Assertions.assertEquals(balance, 60);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

    @Test
    public void the_customer_can_change_the_type_of_notifications_he_wants_to_receive_from_the_bank_after_transfer_money() {
        Customer customer1 = bank.openAccount("eiad", "0797357845", "eiadnimer_swaidat@yahoo.com");
        Customer customer2 = bank.openAccount("rafa", "0795151897", "rafa@gmail.com");

        int accountNumberSender = customer1.getAccountNumber();
        int accountNumberReceiver = customer2.getAccountNumber();

        bank.creditMoney(accountNumberSender, 60);
        bank.transferMoney(accountNumberSender, accountNumberReceiver, 60);
        Assertions.assertTrue(notificationFactory.getNotification().isCalled());
    }

}
