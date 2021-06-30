package com.leantech.practice.request;

/**
 * Represents an Employee request for web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class EmployeeRequest {
    
    /**
     * The @see {@link CandidateRequest} data.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private CandidateRequest candidate;

    /**
     * The position id for the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private long position;

    /**
     * The salary ammount for the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private int salary;

    public CandidateRequest getCandidate() { return candidate; }

    public void setCandidate(CandidateRequest candidate) { this.candidate = candidate; }

    public long getPosition() { return position; }

    public void setPosition(long position) { this.position = position; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }
    
}
