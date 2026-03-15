package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import util.DBConnection;

public class TransactionDAO {

    public void addTransaction(long accountNo, int customerId, int amount, String type) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO TRANSACTION_DETAILS VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, accountNo);
            ps.setInt(2, customerId);
            ps.setInt(3, amount);
            ps.setString(4, type);
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error saving transaction");
        }
    }
}