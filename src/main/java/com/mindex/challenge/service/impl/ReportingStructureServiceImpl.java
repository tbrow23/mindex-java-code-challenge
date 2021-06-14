/**
 * Service class for ReportingStructure requests. Impliments the corresponding interface. 
 * It has a single read method which contains the logic of the GET request.
 */
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Takes in an employee id as a string and searches the employeeRepository for the 
     * corresponding Employee object. Adds the number of direct reports to numOfReports, 
     * then iterates over those reports. For each direct report, it adds those reports 
     * to numOfReports as well. If an Employee has no direct reports, no action is taken.
     * @param   id  id corresponding to an employee in the employeeRepository
     * @return      reportingStructure generated from id
     */
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        int numberOfReports = 0;
        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportingStructure reportingStructure = new ReportingStructure();
        
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        
        reportingStructure.setEmployee(id);
        
        if(employee.getDirectReports() != null){
            numberOfReports += employee.getDirectReports().size();

            //to iterate over employees in for each loop, send the List to an array of the correct size
            Employee[] a = new Employee[employee.getDirectReports().size()];
            Employee[] employeeArry = employee.getDirectReports().toArray(a);
            
            for(Employee i : employeeArry){
                i = employeeRepository.findByEmployeeId(i.getEmployeeId());
                if(i.getDirectReports() != null)
                    numberOfReports += i.getDirectReports().size();
            }
        }
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }
}