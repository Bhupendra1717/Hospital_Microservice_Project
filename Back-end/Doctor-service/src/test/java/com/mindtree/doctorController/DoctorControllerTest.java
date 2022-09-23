package com.mindtree.doctorController;

import static org.junit.Assert.assertEquals;

//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import com.mindtree.doctor.controller.DoctorController;
import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.service.DoctorService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoctorController.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DoctorService doctorService;
	
	@Test
	public void testAddDoctor() throws Exception {
		Doctor doctor = new Doctor();
		doctor.setId(1);
		doctor.setAge(1);
		doctor.setGender("Male");
		doctor.setName("Suraj Singh");
		doctor.setNoOfPatientsAttended((long) 30);
		doctor.setSpecialistOf("Neurology");
		
		String inputInJson = this.mapToJson(doctor);
		
		String URI = "/doctors";
		
		Mockito.when(doctorService.addDoctor(Mockito.any(Doctor.class))).thenReturn(doctor);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertEquals(outputInJson, inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetDoctorById() throws Exception{
		Doctor doctor = new Doctor();
		doctor.setId(1);
		doctor.setAge(1);
		doctor.setGender("Male");
		doctor.setName("Suraj Singh");
		doctor.setNoOfPatientsAttended((long) 30);
		doctor.setSpecialistOf("Neurology");
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(
			    doctor,
			    header, 
			    HttpStatus.OK
			);
		
		Mockito.when(doctorService.getDoctorById(Mockito.anyInt())).thenReturn((ResponseEntity<Optional<Doctor>>) responseEntity);
		
		String URI = "/doctors/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(doctor);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(outputInJson, expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void testgetAllDoctors() throws Exception{
		Doctor doctor1 = new Doctor();
		doctor1.setId(1);
		doctor1.setAge(1);
		doctor1.setGender("Male");
		doctor1.setName("Suraj Singh");
		doctor1.setNoOfPatientsAttended((long) 30);
		doctor1.setSpecialistOf("Neurology");
		
		Doctor doctor2 = new Doctor();
		doctor2.setId(2);
		doctor2.setAge(2);
		doctor2.setGender("Male");
		doctor2.setName("Suraj Singh");
		doctor2.setNoOfPatientsAttended((long) 40);
		doctor2.setSpecialistOf("Neurology");
		
		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(doctor1);
		doctorList.add(doctor2);
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<?> responseEntity = new ResponseEntity<>(
			    doctorList,
			    header, 
			    HttpStatus.OK
			);
		
		Mockito.when(doctorService.getAllDoctors()).thenReturn((ResponseEntity<List<Doctor>>) responseEntity);
		
		String URI = "/doctors";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(doctorList);
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(outputInJson, expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	
	
	//Maps an object into a Json string.
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper= new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
}
