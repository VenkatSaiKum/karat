package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import util.DBConnection;

public class AccountDAO {

    public void createAccount(long accNo, String type, int customerId, String status, int balance) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, accNo);
            ps.setString(2, type);
            ps.setInt(3, customerId);
            ps.setString(4, status);
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setInt(6, balance);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error creating account");
        }
    }

    public int getBalance(long accountNo) {

        int balance = 0;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                balance = rs.getInt("BALANCE");
            }

        } catch (Exception e) {
            System.out.println("Error fetching balance");
        }

        return balance;
    }

    public void updateBalance(long accountNo, int balance) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE ACCOUNT SET BALANCE=?, UPDATED_TIMESTAMP=? WHERE ACCOUNT_NO=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, balance);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setLong(3, accountNo);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error updating balance");
        }
    }
}