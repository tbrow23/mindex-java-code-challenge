/* 
 * Tester Class for CompensationServiceImpl. CREATEs a new Compensation to be stored in a 
 * CompensationRepository and then calls READ on it to ensure it was stored properly. Upon 
 * successful completion, no errors should be logged in the debug console.
*/
package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testCompensation.setSalary(50000);
        testCompensation.setEffectiveDate("1994-10-10");

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployee());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read Checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getEmployee()).getBody();
        assertEquals(createdCompensation.getEmployee(), readCompensation.getEmployee());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
