package com.leantech.practice.persistance.repositories;

import java.util.List;

import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.persistance.entities.PositionEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the database sentences for @see {@link EmployeeEntity}
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

    /**
     * Return a list of all employees mapped with the given position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param position The @see {@link PositionEntity} to filter employees.
     * @return A list of @see {@link EmployeeEntity}.
     */
    public List<EmployeeEntity> findByPosition(PositionEntity position);

    /**
     * Return a list of employees filtered by name.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param name The name to be used for filtering.
     * @return A list of @see {@link EmployeeEntity}.
     */
    @Query("SELECT e FROM EmployeeEntity e WHERE e.person.name LIKE %:name%")
    public List<EmployeeEntity> findFilteredByName(String name);

    /**
     * Return a list of employees filtered by position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param position The position name used to filter the employees list.
     * @return A list of @see {@link EmployeeEntity}.
     */
    @Query("SELECT e FROM EmployeeEntity e WHERE e.position.name LIKE %:position%")
    public List<EmployeeEntity> findFilteredByPosition(String position);

    /**
     * Return a list of employees filtered by name and position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param name The name used to filtered the employees list.
     * @param position The position name used to filtered the employees list.
     * @return A list of @see {@link EmployeeEntity}.
     */
    @Query("SELECT e FROM EmployeeEntity e WHERE e.person.name LIKE %:name% AND e.position.name LIKE %:position%")
    public List<EmployeeEntity> findFilteredByNameAndPosition(String name, String position);
    
}
