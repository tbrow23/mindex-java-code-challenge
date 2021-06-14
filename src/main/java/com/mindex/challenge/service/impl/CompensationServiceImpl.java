/**
 * Service class for Compensation. Implements the corresponding interface. Has 
 * a read method which contains the GET logic and a create method which 
 * contains the POST logic
 */
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Takes in a compensation object and inserts it into the compensationRepository
     * @param   compensation    compensation object created by the controller
     * @return                  same object is returned to the controller
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * Takes in a the employee id as a string and searches the repository for it. 
     * Throws and error if employee id isn't found in the repository.
     * @param   employee    id of employee
     * @return              compensation object with matching employee field
     */
    @Override
    public Compensation read(String employee) {
        LOG.debug("Creating compensation with id [{}]", employee);

        Compensation compensation = compensationRepository.findByEmployee(employee);
        
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employee);
        }

        return compensation;
    }
}

