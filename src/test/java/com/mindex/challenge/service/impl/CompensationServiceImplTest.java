package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

/* Test cases for Employee Compensation service REST endpoints */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    /*
     * Test method for testing create and read endpoints of compensation
     */
    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testCompensation.setSalary(1000.00f);
        testCompensation.setEffectiveDate(new Date(20222212));

        Compensation testCreateCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertCompensationEquivalence(testCompensation, testCreateCompensation);

        Compensation testReadCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, testCreateCompensation.getEmployeeId()).getBody();
        assertCompensationEquivalence(testCreateCompensation, testReadCompensation);

    }

    /*
     * Utility method to compare equivalence of compensation
     */
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary(), 0f);
    }
}
