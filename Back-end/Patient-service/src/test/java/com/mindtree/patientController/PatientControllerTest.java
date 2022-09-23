package com.mindtree.patientController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
//import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.patient.entity.Patient;
import com.mindtree.patient.service.PatientService;
import com.mindtree.patient.controller.PatientController;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PatientController.class)
@AutoConfigureMockMvc
public class PatientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientService patientService;
	
	@Test
	public void testAddPatient() throws Exception {
		Patient patient = new Patient();
		patient.setId((long) 1);
		patient.setAge(1);
		patient.setGender("Male");
		patient.setName("Suraj Singh");
		patient.setDateOfVisit("20-09-2022");
		patient.setDoctorsPriscription("testing");
		patient.setVisitedDoctorId(1);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(
			    patient,
			    header, 
			    HttpStatus.OK
			);
		
		String inputInJson = this.mapToJson(patient);
		
		String URI = "/patients";
		
		Mockito.when(patientService.addPatient(Mockito.any(Patient.class))).thenReturn((ResponseEntity<Patient>) responseEntity);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetPatientById() throws Exception{
		Patient patient = new Patient();
		patient.setId((long) 1);
		patient.setAge(1);
		patient.setGender("Male");
		patient.setName("Suraj Singh");
		patient.setDateOfVisit("20-09-2022");
		patient.setDoctorsPriscription("testing");
		patient.setVisitedDoctorId(1);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(
			    patient,
			    header, 
			    HttpStatus.OK
			);
		
		Mockito.when(patientService.getPatientById(Mockito.anyLong())).thenReturn((ResponseEntity<Optional<Patient>>) responseEntity);
		
		String URI = "/patients/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(patient);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testgetAllPatients() throws Exception{
		Patient patient1 = new Patient();
		patient1.setId((long) 1);
		patient1.setAge(1);
		patient1.setGender("Male");
		patient1.setName("Suraj Singh");
		patient1.setDateOfVisit("20-09-2022");
		patient1.setDoctorsPriscription("testing");
		patient1.setVisitedDoctorId(1);
		
		Patient patient2 = new Patient();
		patient2.setId((long) 2);
		patient2.setAge(2);
		patient2.setGender("Male");
		patient2.setName("Suraj Singh");
		patient2.setDateOfVisit("20-09-2022");
		patient2.setDoctorsPriscription("testing");
		patient2.setVisitedDoctorId(1);
		
		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient1);
		patientList.add(patient2);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(
			    patientList,
			    header, 
			    HttpStatus.OK
			);
		
		Mockito.when(patientService.getAllPatients()).thenReturn((ResponseEntity<List<Patient>>) responseEntity);
		
		String URI = "/patients";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(patientList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	
	
	//Maps an object into a Json string.
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper= new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	

}
