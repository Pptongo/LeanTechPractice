package com.leantech.practice.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Position object mapped with the database.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "POSITION")
public class PositionEntity {
    
    /**
     * The primary key for the Position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * The name of the position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "NAME")
    private String name;

    /**
     * A constructor to create a new Position with empty data.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public PositionEntity() { }

    /**
     * A constructor to create a new Position with ther infomration.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param name The name of the position.
     */
    public PositionEntity(String name) { this.name = name; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}
