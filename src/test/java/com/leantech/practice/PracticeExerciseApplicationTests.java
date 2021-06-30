package com.leantech.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.persistance.repositories.EmployeeRepository;
import com.leantech.practice.request.CandidateRequest;
import com.leantech.practice.request.EmployeeRequest;
import com.leantech.practice.response.ApiResponse;

@SpringBootTest
@AutoConfigureMockMvc
class PracticeExerciseApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testGetAllEmployees() throws Exception {
		List<EmployeeEntity> employeesFromDb = (List<EmployeeEntity>) employeeRepository.findAll();

		ResultActions resultActions = this.mockMvc.perform(get("/api/1.0/employees"))
			.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		List<Object> employees = parseListOfEmployees(apiResponse);

		assertNotNull(employees);
		assertEquals(employeesFromDb.size(), employees.size());
	}

	@Test
	public void testEmployeesFilteredByName() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(get("/api/1.0/employees" + "?name=Cam"))
			.andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		List<Object> employees = parseListOfEmployees(apiResponse);

		assertNotNull(employees);
		assertEquals(1, employees.size());
	}

	@Test
	public void testEmployeesFilteredByPosition() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(get("/api/1.0/employees?position=dev"))
			.andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		List<Object> employees = parseListOfEmployees(apiResponse);

		assertNotNull(employees);
		assertEquals(2, employees.size());
	}

	@Test
	public void testEmployeesFilteredByNameAndPosition() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(get("/api/1.0/employees?name=Cam&position=dev"))
			.andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		List<Object> employees = parseListOfEmployees(apiResponse);

		assertNotNull(employees);
		assertEquals(1, employees.size());
	}

	@Test
	public void testSaveNewEmployee() throws Exception {
		List<EmployeeEntity> employeesBeforeSaved = (List<EmployeeEntity>) employeeRepository.findAll();
		EmployeeRequest req = getEmployeeRequest();

		ResultActions resultActions = this.mockMvc.perform(post("/api/1.0/employees/employee")
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(parseObjToJson(req)))
			.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		EmployeeEntity employee = (EmployeeEntity) parseBody(apiResponse.getBody(), EmployeeEntity.class);

		assertNotNull(employee);

		List<EmployeeEntity> employeesAfterSaved = (List<EmployeeEntity>) employeeRepository.findAll();

		assertTrue(employeesBeforeSaved.size() < employeesAfterSaved.size());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		EmployeeEntity employeeBeforeUpdate = employeeRepository.findById(1l).orElse(null);
		assertNotNull(employeeBeforeUpdate);

		EmployeeRequest req = getEmployeeRequest();
	
		ResultActions resultActions = this.mockMvc.perform(put("/api/1.0/employees/employee/" + employeeBeforeUpdate.getId())
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(parseObjToJson(req)))
			.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNotNull(apiResponse.getBody());

		EmployeeEntity employeeAfterUpdate = employeeRepository.findById(1l).orElse(null);
		
		assertNotNull(employeeAfterUpdate);

		assertEquals(employeeBeforeUpdate.getId(), employeeAfterUpdate.getId());
		assertNotNull(employeeBeforeUpdate.getPerson().getName(), employeeAfterUpdate.getPerson().getName());
	}

	@Test
	public void testDeleteUser() throws Exception {
		List<EmployeeEntity> employeesBeforeDelete = (List<EmployeeEntity>) employeeRepository.findAll();

		ResultActions resultActions = this.mockMvc.perform(delete("/api/1.0/employees/employee/" + 1l).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertTrue(apiResponse.getStatus());
		assertNull(apiResponse.getMessage());
		assertNull(apiResponse.getBody());

		List<EmployeeEntity> employeesAfterDelete = (List<EmployeeEntity>) employeeRepository.findAll();

		assertTrue(employeesBeforeDelete.size() > employeesAfterDelete.size());
	}

	@Test
	public void testDeleteNonExistingUser() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(delete("/api/1.0/employees/employee/" + 6l).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isInternalServerError());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertFalse(apiResponse.getStatus());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getBody());

		assertEquals(Messages.EmployeeNotFound, apiResponse.getMessage());
	}

	@Test
	public void testSaveEmployeeWithBadPosition() throws Exception {
		EmployeeRequest req = getEmployeeRequest();
		req.setPosition(3l);

		ResultActions resultActions = this.mockMvc.perform(post("/api/1.0/employees/employee")
			.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(parseObjToJson(req)))
			.andExpect(status().isInternalServerError());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ApiResponse apiResponse = parseResponse(contentAsString);

		assertNotNull(apiResponse);
		assertFalse(apiResponse.getStatus());
		assertNotNull(apiResponse.getMessage());
		assertNull(apiResponse.getBody());

		assertEquals(Messages.PositionNotFound, apiResponse.getMessage());
	}

	private EmployeeRequest getEmployeeRequest() {
		CandidateRequest candidateRequest = new CandidateRequest();
		candidateRequest.setName("Jose Luis");
		candidateRequest.setLastName("Perez Olvera");
		candidateRequest.setAddress("Privada Solare III Real del Sol");
		candidateRequest.setCellphone("+525563166076");
		candidateRequest.setCityName("Mexico");

		EmployeeRequest employeeRequest = new EmployeeRequest();
		employeeRequest.setCandidate(candidateRequest);
		employeeRequest.setPosition(1);
		employeeRequest.setSalary(2500);

		return employeeRequest;
	}

	@SuppressWarnings("unchecked")
	private List<Object> parseListOfEmployees(ApiResponse apiResponse) {
		return ((List<HashMap<String, Object>>) apiResponse.getBody()).stream().map(obj -> {
			try {
				return parseBody(obj, EmployeeEntity.class);
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());
	}

	private String parseObjToJson(Object obj) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	private ApiResponse parseResponse(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ApiResponse apiResponse = mapper.readValue(json, ApiResponse.class);

		return apiResponse;
	}

	private <T> Object parseBody(Object body, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(body);

		return mapper.readValue(json, clazz);
	}

}
