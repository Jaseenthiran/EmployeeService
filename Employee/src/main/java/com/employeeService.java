
package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.employee;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;




@Path("/Employee")
public class employeeService{
  
	employee Obj = new employee();

	@GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
	public String readEmployee(){
 
		return Obj.readEmployee();
 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("EmpName") String EmpName,@FormParam("EmpType") String EmpType,@FormParam("EmpPhone") String EmpPhone,@FormParam("EmpEmail") String EmpEmail,@FormParam("EmpPassword") String EmpPassword) {
		
		String output = Obj.insertEmployee(EmpName, EmpType, EmpPhone, EmpEmail, EmpPassword);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String EmployeeData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(EmployeeData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = Object.get("ID").getAsString();
	 String EmpName = Object.get("EmpName").getAsString();
	 String EmpType = Object.get("EmpType").getAsString();
	 String EmpPhone = Object.get("EmpPhone").getAsString();
	 String EmpEmail = Object.get("EmpEmail").getAsString();
	 String EmpPassword = Object.get("EmpPassword").getAsString();
	 String output = Obj.updateEmployee(ID, EmpName, EmpType, EmpPhone, EmpEmail, EmpPassword);
	
	 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String EmployeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(EmployeeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = Obj.deleteEmployee(ID);
	return output;
	}


}

