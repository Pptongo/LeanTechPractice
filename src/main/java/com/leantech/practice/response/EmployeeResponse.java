package com.leantech.practice.response;

import com.leantech.practice.persistance.entities.EmployeeEntity;

/**
 * The response used for employees web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class EmployeeResponse {
    
    /**
     * The id of the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private long id;

    /**
     * The salary amount of the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private int salary;

    /**
     * The @see {@link CandidateResponse} linked with the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private CandidateResponse person;

    /**
     * Received an @see {@link EmployeeEntity} to convert it into a EmployeeResponse.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param employee The @see {@link EmployeeEntity} to be converted.
     */
    public EmployeeResponse(EmployeeEntity employee) {
        this.id = employee.getId();
        this.salary = employee.getSalary();
        this.person = new CandidateResponse(employee.getPerson());
    }

    public long getId() { return id; }

    public int getSalary() { return salary; }

    public CandidateResponse getPerson() { return person; }

}
