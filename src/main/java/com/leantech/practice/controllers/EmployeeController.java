package com.leantech.practice.controllers;

import java.util.Optional;

import com.leantech.practice.Constants;
import com.leantech.practice.request.EmployeeRequest;
import com.leantech.practice.response.ApiResponse;
import com.leantech.practice.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint for all Employee web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@RestController()
@RequestMapping(value = "/api/1.0/employees")
public class EmployeeController {

    /**
     * Create an injected service for manage Employees operations.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Method to get all employees, also can received the optional paramters to filter the employees list by position and/or name.
     * @param position Optional parameter used to filter by position.
     * @param name Optional parameter used to filter by name.
     * @return @see {@link EmployeeEntity} A list of employees.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAll(@RequestParam("position") Optional<String> position, @RequestParam("name") Optional<String> name) {
        try {
            return new ResponseEntity<>(new ApiResponse(employeeService.getAll(position, name)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a new employee with their person information.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request @see {@link EmployeeRequest} The employee object request in JSON format.
     * @return @see {@link EmployeeEntity} The employee object created.
     */
    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody EmployeeRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse(employeeService.add(request)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponseException(e);
        }
    }

    /**
     * Update the information of an employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request @see {@link EmployeeRequest} The employee object with the new information.
     * @param id The id of the employee to be updated.
     * @return @see {@link EmployeeEntity} Return the updated employee object.
     */
    @PutMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@RequestBody EmployeeRequest request, @PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(employeeService.update(request, id)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponseException(e);
        }
    }

    /**
     * Delete an existing employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The id of the employee to be deleted.
     * @return @see {@link ApiResponse} A boolean to know if the employee was deleted succesfully.
     */
    @DeleteMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") long id) {
        try {
            employeeService.delete(id);
            return new ResponseEntity<>(new ApiResponse(true), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponseException(e);
        }
    }

    /**
     * Manage the response entity to know which @see {@link HttpStatus} return.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param e @see {@link Exception} The exception objected raised by any method.
     * @return @see {@link ApiResponse} The ResponseEntity object with the correct HttpStatus error.
     */
    private ResponseEntity<ApiResponse> manageResponseException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e.getMessage().equals(Constants.BadRequest)) status = HttpStatus.BAD_REQUEST;
            
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), status);
    }

}
