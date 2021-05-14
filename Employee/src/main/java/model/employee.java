

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author HP
 *
 */
public class employee {

	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertEmployee(String name, String emptype, String empphone, String empemail,String passwords)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `employee`(`ID`, `EmpName`, `EmpType`, `EmpPhone`, `EmpEmail`, `EmpPassword`)  VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, name);
	        preparedStmt.setString(3,emptype );
	        preparedStmt.setString(4, empphone);
	        preparedStmt.setString(5, empemail);
	        preparedStmt.setString(6, passwords);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        String newemployee = readEmployee();
	   	 output = "{\"status\":\"success\", \"data\": \"" +newemployee + "\"}";
	   	 }
	   	 catch (Exception e)
	   	 {
	   	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the employee.\"}";
	   	 System.err.println(e.getMessage());
	   	 } 
	
		return output;
	 } 
	
	public String readEmployee(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1' align='center'><tr><th>Employee Name</th><th>Employee Type</th>" +
	                "<th>Employee Phone</th>" +
	                "<th>Employee Email</th>" +
	                "<th>Employee Password</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT `ID`, `EmpName`, `EmpType`, `EmpPhone`, `EmpEmail`, `EmpPassword` FROM `employee` ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String ID=rs.getString("ID");
	        	String EmpName = rs.getString("EmpName");
	            String EmpType = rs.getString("EmpType");
	            String EmpPhone = rs.getString("EmpPhone");
	            String EmpEmail = rs.getString("EmpEmail");
	            String EmpPassword = rs.getString("EmpPassword");
	            
	            // Add into the html table
	
	            output += "<tr><td>" + EmpName + "</td>";
	            output += "<td>" + EmpType + "</td>";
	            output += "<td>" + EmpPhone + "</td>";
	            output += "<td>" + EmpEmail + "</td>";
	            output += "<td>" + EmpPassword + "</td>";
	
	            // buttons
	            output += "<td><input name='btnUpdate' type='button' value='Update' "
	        			+ "class='btnUpdate btn btn-secondary' data-itemid='" + ID + "'></td>"
	        			+ "<td><input name='btnRemove' type='button' value='Remove' "
	        			+ "class='btnRemove btn btn-danger' data-itemid='" + ID + "'></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String updateEmployee(String ID,String name, String emptype, String empphone, String empemail,String passwords){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `employee` SET `EmpName`=?,`EmpType`=?,`EmpPhone`=?,`EmpEmail`=?,`EmpPassword`=? WHERE `ID`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, name);
	        preparedStmt.setString(2, emptype);
	        preparedStmt.setString(3, empphone);
	        preparedStmt.setString(4, empemail);
	        preparedStmt.setString(5, passwords);
	        preparedStmt.setInt(6, Integer.parseInt(ID));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        String newemployee = readEmployee();
		   	 output = "{\"status\":\"success\", \"data\": \"" +newemployee + "\"}";
		   	 }
		   	 catch (Exception e)
		   	 {
		   	 output = "{\"status\":\"error\", \"data\":\"Error while updating the employee.\"}";
		   	 System.err.println(e.getMessage());
		   	 } 
	
		return output;
	 } 
	
	public String deleteEmployee(String ID){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `employee` WHERE `ID`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(ID));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         String newemployee = readEmployee();
		   	 output = "{\"status\":\"success\", \"data\": \"" +newemployee + "\"}";
		   	 }
		   	 catch (Exception e)
		   	 {
		   	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the employee.\"}";
		   	 System.err.println(e.getMessage());
		   	 } 
	
		return output;
	 } 
}
