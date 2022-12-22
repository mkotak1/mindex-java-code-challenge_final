package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Reporting;
import com.mindex.challenge.service.ReportingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ReportingServiceImpl implements ReportingService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingServiceImpl.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Reporting read(String id) {
        LOG.debug("Reading employee reporting structure with id [{}]", id);
        if (id == null || id.equals("")) throw new IllegalArgumentException("Employee Id cannot be null");

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        Reporting reportingStructure = new Reporting(employee);

        int numberOfReports = 0;
        Queue<Employee> employeeQueue = new LinkedList<>();
        employeeQueue.add(employee);
        while (!employeeQueue.isEmpty()){
            Employee currEmployee = employeeRepository.findByEmployeeId(employeeQueue.peek().getEmployeeId());
            List<Employee> directReports = currEmployee.getDirectReports();
            if(directReports != null){
                numberOfReports += directReports.size();
                for(Employee emp : directReports){
                    employeeQueue.add(emp);
                }
            }
            employeeQueue.poll();

        }
        reportingStructure.setNumberOfReports(numberOfReports);
        return reportingStructure;
    }
}
