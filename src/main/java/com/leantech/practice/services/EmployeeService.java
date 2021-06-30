package com.leantech.practice.services;

import java.util.List;
import java.util.Optional;

import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.request.EmployeeRequest;

/**
 * Interface used to specify all availables methods for EmployeeService.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public interface EmployeeService {
    
    /**
     * Get a list of all @see {@link EmployeeEntity}, also can be filtered by name and/or position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param position Optional position to filter employee list.
     * @param name Optiona name to filter employee list.
     * @return A list of @see {@link EmployeeEntity}.
     */
    public List<EmployeeEntity> getAll(Optional<String> position, Optional<String> name);

    /**
     * Add a new employee and create their candidate object also.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link EmployeeRequest} used to create the candidate and employee.
     * @return The @see {@link EmployeeEntity} created.
     * @throws Exception For any error throw the error to the controller.
     */
    public EmployeeEntity add(EmployeeRequest request) throws Exception;

    /**
     * Update an existing employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link EmployeeRequest} used to update Employee data.
     * @param id The id of the employee wanted to be updated.
     * @return The @see {@link EmployeeEntity} updated.
     * @throws Exception For any error throw the error to the controller.
     */
    public EmployeeEntity update(EmployeeRequest request, long id) throws Exception;

    /**
     * Delete an existing employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The id of the employee wanted to delete.
     * @throws Exception For any error throw the error to the controller.
     */
    public void delete(long id) throws Exception;

}
