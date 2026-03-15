package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Customer;
import util.DBConnection;

public class CustomerDAO {
	public int createCustomer(Customer customer) {

	    int customerId = 0;

	    try {

	        Connection con = DBConnection.getConnection();

	        String sql = "INSERT INTO CUSTOMER(NAME,DOB,EMAIL,PHONE) VALUES(?,?,?,?)";

	        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        ps.setString(1, customer.getName());
	        ps.setString(2, customer.getDob());
	        ps.setString(3, customer.getEmail());
	        ps.setString(4, customer.getPhone());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();

	        if(rs.next()){
	            customerId = rs.getInt(1);
	        }

	    } catch(Exception e){
	        e.printStackTrace();
	    }

	    return customerId;
	}
    }