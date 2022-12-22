package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Reporting;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/* Test Cases for reporting service REST endpoints */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceImplTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String reportingUrl;
    private String employeeUrl;

    private HashMap<String, Employee> dummyEmployees = new HashMap<>();
    {
        Employee emp = new Employee();
        emp.setFirstName("David");
        emp.setLastName("Liu");
        emp.setDepartment("Engineering");
        emp.setPosition("Manager");
        emp.setEmployeeId("b7839309-5de1c168beb3");
        dummyEmployees.put("Manager", emp);

        Employee subEmp = new Employee();
        subEmp.setFirstName("Sub");
        subEmp.setLastName("Emp");
        subEmp.setDepartment("Engineering");
        subEmp.setPosition("Developer");
        dummyEmployees.put("Developer", subEmp);
    }

    @Before
    public void setup() {

        reportingUrl = "http://localhost:" + port +"/reportingStructure/{id}";
        employeeUrl =  "http://localhost:" + port +"/employee";


        Employee manager = restTemplate.postForEntity(employeeUrl, dummyEmployees.get("Manager"), Employee.class).getBody();
        Employee subEmp = restTemplate.postForEntity(employeeUrl, dummyEmployees.get("Developer"), Employee.class).getBody();

        ArrayList<Employee> managerReports = new ArrayList<>();
        managerReports.add(subEmp);
        dummyEmployees.get("Manager").setDirectReports(managerReports);

        dummyEmployees.put("Manager", manager);
        dummyEmployees.put("Developer", subEmp);

    }


    /**
     * Asserts that the correct Reporting is returned for an employee with 0 direct reports.
     */
    @Test
    public void testReportingStructureZeroReports() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Reporting reports =
                restTemplate.getForEntity(reportingUrl,
                        Reporting.class,
                        dummyEmployees.get("Developer").getEmployeeId()).getBody();
        assertEquals(0, reports.getNumberOfReports());
    }

    /**
     * Asserts that the correct Reporting is returned for an employee with 1 direct reports.
     */
    @Test
    public void testReportingStructureOneReports() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Reporting reports =
                restTemplate.getForEntity(reportingUrl,
                        Reporting.class,
                        dummyEmployees.get("Manager").getEmployeeId()).getBody();
        assertEquals(1, reports.getNumberOfReports());
    }

    private static void assertReportingEquivalence(Reporting expected, Reporting actual) {
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
        assertEquals(expected.getEmployee(), actual.getEmployee());
    }
}