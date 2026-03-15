package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class ReportDAO {

    public ResultSet getReportData() {

        ResultSet rs = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
            "SELECT C.NAME, A.ACCOUNT_NO, A.BALANCE, A.UPDATED_TIMESTAMP, " +
            "IFNULL(T.TRANSACTION_TYPE,'N/A') AS TRANSACTION_TYPE " +
            "FROM CUSTOMER C " +
            "JOIN ACCOUNT A ON C.ID = A.CUSTOMER_ID " +
            "LEFT JOIN TRANSACTION_DETAILS T ON A.ACCOUNT_NO = T.ACCOUNT_NO";

            PreparedStatement ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}