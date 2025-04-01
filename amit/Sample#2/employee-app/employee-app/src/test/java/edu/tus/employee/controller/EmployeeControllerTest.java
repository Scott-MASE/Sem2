package edu.tus.employee.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tus.employee.service.EmployeeService;
import edu.tus.employee.errors.ErrorMessage;
import edu.tus.employee.errors.ErrorMessages;
import edu.tus.employee.exception.EmployeeValidationException;
import edu.tus.employee.model.Employee;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
	
	@Autowired
	EmployeeController employeeController;
	
	@MockBean
	EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addEmployeeTestSuccess() throws Exception
	{
		//Mock the createEmployee method in the Service Layer
		//call addEmployee method in the controller
		//test the httpstatus code return is "CREATED"
		//test the details for the employee object in the message body is correct
		Employee employee = buildEmployee();
		ObjectMapper map = new ObjectMapper();
		String jsonString = map.writeValueAsString(employee);
		
		when(employeeService.createEmployee(employee)).thenReturn(employee);
		
		this.mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andDo(print()).andExpect(status().isCreated());
		
	}
	
	@Test
	public void addEmployeeTestFail() throws EmployeeValidationException, Exception
	{
		//Mock the createEmployee method to throw an EmployeeValidationException with EMPTY_FIELDS method
		//call the addEmployee method in the controller
                //test that the response is "BAD_REQUEST"
                //tests that the correct error mesage is received in the request body 
	
		Employee employee = buildEmployee();
		
		when(employeeService.createEmployee(employee)).thenThrow(new EmployeeValidationException(ErrorMessages.EMPTY_FIELDS.getMsg()));
		
		ResponseEntity<ErrorMessage> response = employeeController.addEmployee(employee);
		
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertEquals(response.getBody().getErrorMessage(), ErrorMessages.EMPTY_FIELDS.getMsg());
	}
	
	
	Employee buildEmployee() {
		Employee employee = new Employee();
		employee.setAge(20);
		employee.setFirstName("Joe");
		employee.setLastName("Bloggs");
		employee.setEmailAddress("Joe@gmail.com");
		return employee;
	}

}
