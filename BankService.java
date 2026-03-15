package service;

import java.sql.ResultSet;
import java.util.Scanner;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.ReportDAO;
import dao.TransactionDAO;
import model.Customer;
import util.AccountNumberGenerator;

public class BankService {

    Scanner sc = new Scanner(System.in);

    CustomerDAO customerDAO = new CustomerDAO();
    AccountDAO accountDAO = new AccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    public void createCustomer() {

        try {

            System.out.println("Enter Customer Details (comma separated):");
            System.out.println("Name,DOB,Email,Phone,AccountType,Status,Balance");

            String input = sc.nextLine();   // read full line

            if(input.trim().isEmpty()) {
                input = sc.nextLine();      // handles leftover newline
            }

            String[] data = input.split(",");

            if(data.length != 7){
                System.out.println("Invalid Input Format");
                return;
            }

            String name = data[0];
            String dob = data[1];
            String email = data[2];
            String phone = data[3];
            String accountType = data[4];
            String status = data[5];
            int balance = Integer.parseInt(data[6]);

            Customer customer = new Customer(name,dob,email,phone);

            int customerId = customerDAO.createCustomer(customer);

            long accountNumber = AccountNumberGenerator.generateAccountNumber();

            accountDAO.createAccount(accountNumber, accountType, customerId, status, balance);

            System.out.println("Customer Created Successfully");
            System.out.println("Account Number: " + accountNumber);

        } catch(Exception e) {
            System.out.println("Invalid Input Format");
        }
    }
    public void transferMoney() {

        try {

            System.out.println("Enter From Account:");
            long fromAcc = sc.nextLong();

            System.out.println("Enter To Account:");
            long toAcc = sc.nextLong();

            System.out.println("Enter Amount:");
            int amount = sc.nextInt();

            int fromBalance = accountDAO.getBalance(fromAcc);

            if (fromBalance < amount) {
                System.out.println("Insufficient Balance");
                return;
            }

            int toBalance = accountDAO.getBalance(toAcc);

            accountDAO.updateBalance(fromAcc, fromBalance - amount);
            accountDAO.updateBalance(toAcc, toBalance + amount);

            transactionDAO.addTransaction(fromAcc, 0, amount, "DEBIT");
            transactionDAO.addTransaction(toAcc, 0, amount, "CREDIT");

            System.out.println("Transfer Successful");

        } catch (Exception e) {
            System.out.println("Transfer Failed");
        }
    }

    public void report() {

        try {

            ReportDAO dao = new ReportDAO();

            ResultSet rs = dao.getReportData();

            Scanner sc = new Scanner(System.in);

            int count = 0;

            System.out.println("S.No | Customer Name | Account Number | Balance | Last Updated | Transaction");

            while(rs.next()) {

                count++;

                System.out.println(
                    count + " | " +
                    rs.getString("NAME") + " | " +
                    rs.getLong("ACCOUNT_NO") + " | " +
                    rs.getInt("BALANCE") + " | " +
                    rs.getTimestamp("UPDATED_TIMESTAMP") + " | " +
                    rs.getString("TRANSACTION_TYPE")
                );

                if(count % 5 == 0) {

                    System.out.println("\nPress ENTER for next records or Q to exit");

                    String input = sc.nextLine();

                    if(input.equalsIgnoreCase("q")) {
                        break;
                    }
                }
            }

        } catch(Exception e) {
            System.out.println("Error generating report");
        }
    }
}