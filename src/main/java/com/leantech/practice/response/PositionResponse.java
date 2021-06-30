package com.leantech.practice.response;

import java.util.List;
import java.util.stream.Collectors;

import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.persistance.entities.PositionEntity;

/**
 * The response for positions web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class PositionResponse {
    
    /**
     * The id of the position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private long id;

    /**
     * The name of the position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String name;

    /**
     * The list of @see {@link EmployeResponse} as employees.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private List<EmployeeResponse> employees;

    /**
     * Received the @see {@link PositionEntity} and a list of @see {@link EmployeeEntity} to generate the PositionResponse.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param position The @see {@link PositionEntity} to be converted.
     * @param employees The list of @see {@link EmployeeEntity} to be added to the @see {@link PositionResponse}.
     */
    public PositionResponse(PositionEntity position, List<EmployeeEntity> employees) {
        this.id = position.getId();
        this.name = position.getName();
        this.employees = employees.stream().map(employee -> new EmployeeResponse(employee)).collect(Collectors.toList());
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public List<EmployeeResponse> getEmployees() { return employees; }

}
