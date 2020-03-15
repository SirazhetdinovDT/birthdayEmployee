package com.birthdayEmployee.birthdayEmployee;

import com.birthdayEmployee.birthdayEmployee.dataClasses.Department;
import com.birthdayEmployee.birthdayEmployee.dataClasses.Employee;
import com.birthdayEmployee.birthdayEmployee.dboConnection.RepositoryDepartment;
import com.birthdayEmployee.birthdayEmployee.dboConnection.RepositoryEmployee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

@SpringBootTest
@AutoConfigureMockMvc
class BirthdayEmployeeApplicationTests {

	@Autowired
	private RepositoryEmployee repositoryEmployee;

	@Autowired
	private RepositoryDepartment repositoryDepartment;

	@Test
	void createSitcoms(){

		repositoryDepartment.save(new Department(0,"sitcoms"));
		repositoryDepartment.save(new Department(1,"Friends"));
		repositoryDepartment.save(new Department(1,"TBBT"));
		repositoryDepartment.save(new Department(2,"MainActors"));
		repositoryDepartment.save(new Department(2,"SecondActors"));
		repositoryDepartment.save(new Department(3,"MainActors"));
		repositoryDepartment.save(new Department(3,"SecondActors"));
	}

	@Test
	void createActors() {

		repositoryEmployee.save(new Employee("Jennifer Joanna Aniston", new Date(69,2,11),4));
		repositoryEmployee.save(new Employee("Courteney Bass Cox", new Date(64,6,15),4));
		repositoryEmployee.save(new Employee("Matthew Langford Perry", new Date(69,8,17),4));
		repositoryEmployee.save(new Employee("Matt LeBlanc", new Date(67,7,25),4));
		repositoryEmployee.save(new Employee("Lisa Valerie Kudrow-Stern", new Date(63,7,30),4));
		repositoryEmployee.save(new Employee("David Schwimmer", new Date(66,11,2),4));

		repositoryEmployee.save(new Employee("Jim Parsons", new Date(73,03,24),6));
		repositoryEmployee.save(new Employee("Johnny Galecki", new Date(75,04,30),6));
		repositoryEmployee.save(new Employee("Kaley Cuoco", new Date(85,11,30),6));
		repositoryEmployee.save(new Employee("Simon Helberg", new Date(80,12,9),6));
		repositoryEmployee.save(new Employee("Kunal Nayyar", new Date(81,04,30),6));
		repositoryEmployee.save(new Employee("Melissa Rauch", new Date(80,06,23),6));
		repositoryEmployee.save(new Employee("Mayim Bialik", new Date(75,12,12),6));

		repositoryEmployee.save(new Employee("Kevin Sussman", new Date(70,12,4),7));
		repositoryEmployee.save(new Employee("Carol Ann Susi", new Date(52,2,2),7));
		repositoryEmployee.save(new Employee("Wil Wheaton", new Date(72,7,29),7));
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void TestTBBT_45() throws Exception {
		this.mockMvc.perform(get("/getEmployee?day=45&department=3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<td><b>Jim Parsons</b></td>")));
	}

	@Test
	public void TestTBBT_200() throws Exception {
		this.mockMvc.perform(get("/getEmployee?day=200&department=3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<td><b>Jim Parsons</b></td>")))
				.andExpect(content().string(containsString("<td><b>Wil Wheaton</b></td>")));
	}

	@Test
	public void testFriend_200() throws Exception {
		this.mockMvc.perform(get("/getEmployee?day=200&department=2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<td><b>Courteney Bass Cox</b>")))
				.andExpect(content().string(containsString("<td><b>Lisa Valerie Kudrow-Stern</b></td>")));
	}

	//Тут тест должен завалится
	@Test
	public void testTBBTnoFriends_200() throws Exception {
		this.mockMvc.perform(get("/getEmployee?day=200&department=3"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<td><b>Courteney Bass Cox</b>")))
				.andExpect(content().string(containsString("<td><b>Lisa Valerie Kudrow-Stern</b></td>")));
	}

}
