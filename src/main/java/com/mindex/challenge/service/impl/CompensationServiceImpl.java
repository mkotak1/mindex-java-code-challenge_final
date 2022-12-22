package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    CompensationRepository compensationRepository;

    /*
     * Service for creating compensation structure for employee
     * @param compensation: POJO class with employee id, salary and effective date
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        if (compensation == null) {
            throw new RuntimeException("Invalid Input");
        }
        compensationRepository.insert(compensation);
        return compensation;
    }

    /*
     * Service method for getting the compensation details of an employee
     * @param id: employeeId for getting the compensation details
     * @return compensation object with employee, salary and effective date
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation details with id [{}]", id);
        if(id == null || id.equals("")) throw new IllegalArgumentException("Employee Id cannot be null");
        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("No compensation record found for this employee Id: " + id);
        }
        return compensation;
    }

}
