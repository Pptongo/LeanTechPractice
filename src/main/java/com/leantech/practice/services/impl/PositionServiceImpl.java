package com.leantech.practice.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.persistance.entities.PositionEntity;
import com.leantech.practice.persistance.repositories.EmployeeRepository;
import com.leantech.practice.persistance.repositories.PositionRepository;
import com.leantech.practice.response.PositionResponse;
import com.leantech.practice.services.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All services for Positions business logic.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Service
public class PositionServiceImpl implements PositionService {

    /**
     * Inject the position repository to manage database methods.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private PositionRepository positionRepository;
    
    /**
     * Inject the employee repository to manage the database methods.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get all positions with a list of all employess related with each position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return A list of @see {@link PositionResponse}.
     */
    @Override
    public List<PositionResponse> getAll() {
        List<PositionResponse> response = new ArrayList<>();
        List<PositionEntity> positions = (List<PositionEntity>) positionRepository.findAll();

        for (PositionEntity position : positions) {
            List<EmployeeEntity> employees = employeeRepository.findByPosition(position);
            PositionResponse positionResponse = new PositionResponse(position, employees);

            response.add(positionResponse);
        }

        return response;
    }

}
