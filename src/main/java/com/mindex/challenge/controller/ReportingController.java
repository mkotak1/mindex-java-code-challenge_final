package com.mindex.challenge.controller;

import com.mindex.challenge.data.Reporting;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private ReportingService reportingService;

    /*
     * GET HTTP method for getting the number of reporting employees
     * @param id: employee id to get number of employees
     */
    @GetMapping("/reportingStructure/{id}")

    public Reporting read(@PathVariable String id) {

        LOG.debug("Received reporting structure read request for employee id [{}]", id);

        return reportingService.read(id);

    }
}
