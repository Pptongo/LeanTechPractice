package com.leantech.practice.persistance.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents the Employee object mapped with the database.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    /**
     * The primary key of the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * The @see {@link CandidateEntity} linked as person with the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @JoinColumn(name = "PERSON")
    @OneToOne(cascade = CascadeType.REMOVE)
    private CandidateEntity person;

    /**
     * The @see {@link PositionEntity} linked with the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @JoinColumn(name = "POSITION")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PositionEntity position;

    /**
     * The salary of the employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "SALARY")
    private int salary;

    /**
     * A constructor to create a new employee object.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public EmployeeEntity() { }

    /**
     * A constructor to create a new employee with their information.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param person @see {@link CandidateEntity} The person information to be linked with the employee.
     * @param position @see {@link PositionEntity} The position for the employee.
     * @param salary The salary amount for the employee.
     */
    public EmployeeEntity(CandidateEntity person, PositionEntity position, int salary) {
        this.person = person;
        this.position = position;
        this.salary = salary;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public CandidateEntity getPerson() { return person; }

    public void setPerson(CandidateEntity person) { this.person = person; }

    public PositionEntity getPosition() { return position; }

    public void setPosition(PositionEntity position) { this.position = position; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }
    
}
