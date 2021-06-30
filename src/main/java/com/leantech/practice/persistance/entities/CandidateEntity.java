package com.leantech.practice.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Candidate object mapped with the database.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "CANDIDATE")
public class CandidateEntity {
    
    /**
     * The primary key in the database.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * The name of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "NAME")
    private String name;

    /**
     * The last name of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * The address of the candidate
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * The cellphone of the candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "CELLPHONE")
    private String cellphone;

    /**
     * The city name where the candidate lives.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "CITY_NAME")
    private String cityName;

    /**
     * Constructor to create an empty candidate.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public CandidateEntity() { }

    /**
     * Constructor to create an candidate with information.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param name The name of the candidate.
     * @param lastName The last name of the candidate.
     * @param address The address of the candidate.
     * @param cellphone The cellphone of the candidate.
     * @param cityName The city name where the candidate lives.
     */
    public CandidateEntity(String name, String lastName, String address, String cellphone, String cityName) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.cellphone = cellphone;
        this.cityName = cityName;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCellphone() { return cellphone; }

    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public String getCityName() { return cityName; }

    public void setCityName(String cityName) { this.cityName = cityName; }

}
