/* 
 * Tester Class for ReportingStructureServiceImpl. READs a new ReportingStructure,
 * the values of which are generated on the fly and not stored in any sort of 
 * repository. One with known correct values has been created within the test to 
 * validate the results. Upon successful completion, no errors should appear in the 
 * debug console.
*/
package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    
    private String reportingStructureUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingstructure/16a596ae-edd3-4847-99fe-c4518e82c86f";
    }

    @Test
    public void testRead() {
        ReportingStructure createdReportingStructure = new ReportingStructure();
        createdReportingStructure.setEmployee("16a596ae-edd3-4847-99fe-c4518e82c86f");
        createdReportingStructure.setNumberOfReports(4);

        // Read checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdReportingStructure.getEmployee()).getBody();
        assertEquals(createdReportingStructure.getEmployee(), readReportingStructure.getEmployee());
        assertReportingStructureEquivalence(createdReportingStructure, readReportingStructure);
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}
