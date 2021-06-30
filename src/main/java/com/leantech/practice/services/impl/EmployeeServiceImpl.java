package com.leantech.practice.services.impl;

import java.util.List;
import java.util.Optional;

import com.leantech.practice.Constants;
import com.leantech.practice.Messages;
import com.leantech.practice.persistance.entities.CandidateEntity;
import com.leantech.practice.persistance.entities.EmployeeEntity;
import com.leantech.practice.persistance.entities.PositionEntity;
import com.leantech.practice.persistance.repositories.CandidateRepository;
import com.leantech.practice.persistance.repositories.EmployeeRepository;
import com.leantech.practice.persistance.repositories.PositionRepository;
import com.leantech.practice.request.CandidateRequest;
import com.leantech.practice.request.EmployeeRequest;
import com.leantech.practice.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All services for Employees business logic.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Inject the employee repository to manage the employees database methods.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Inject the position repository to manage the position database methods.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private PositionRepository positionRepository;

    /**
     * Inject the candidate repository to manage the candidate database methods.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private CandidateRepository candidateRepository;
    
    /**
     * Get a list of all @see {@link EmployeeEntity}, also can be filtered by name and/or position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param position Optional position to filter employee list.
     * @param name Optiona name to filter employee list.
     * @return A list of @see {@link EmployeeEntity}.
     */
    @Override
    public List<EmployeeEntity> getAll(Optional<String> position, Optional<String> name) {
        if (position.isPresent() && name.isPresent()) {
            return employeeRepository.findFilteredByNameAndPosition(name.get(), position.get());
        } else if (name.isPresent()) {
            return employeeRepository.findFilteredByName(name.get());
        } else if (position.isPresent()) {
            return employeeRepository.findFilteredByPosition(position.get());
        } else {
            return (List<EmployeeEntity>) employeeRepository.findAll();
        }
    }

    /**
     * Add a new employee and create their candidate object also.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link EmployeeRequest} used to create the candidate and employee.
     * @return The @see {@link EmployeeEntity} created.
     * @throws Exception For any error throw the error to the controller.
     */
    @Override
    public EmployeeEntity add(EmployeeRequest request) throws Exception {
        if (request == null || request.getCandidate() == null) throw new Exception(Constants.BadRequest);

        PositionEntity position = positionRepository.findById(request.getPosition()).orElse(null);

        if (position == null) throw new Exception(Messages.PositionNotFound);

        CandidateRequest candidateRequest = request.getCandidate();
        CandidateEntity candidate = new CandidateEntity(candidateRequest.getName(), candidateRequest.getLastName(),
            candidateRequest.getAddress(), candidateRequest.getCellphone(), candidateRequest.getCityName());
        
        candidateRepository.save(candidate);

        try {
            EmployeeEntity employee = new EmployeeEntity(candidate, position, request.getSalary());
            employeeRepository.save(employee);

            return employee;
        } catch (Exception e) {
            candidateRepository.delete(candidate);
            throw e;
        }
    }

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
    @Override
    public EmployeeEntity update(EmployeeRequest request, long id) throws Exception {
        if (request == null) throw new Exception(Constants.BadRequest);

        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) throw new Exception(Messages.EmployeeNotFound);

        PositionEntity position = positionRepository.findById(request.getPosition()).orElse(null);

        if (position == null) throw new Exception(Messages.PositionNotFound);

        if (request.getCandidate() != null) {
            CandidateEntity candidate = employee.getPerson();
            candidate.setName(request.getCandidate().getName());
            candidate.setLastName(request.getCandidate().getLastName());
            candidate.setAddress(request.getCandidate().getAddress());
            candidate.setCellphone(request.getCandidate().getCellphone());
            candidate.setCityName(request.getCandidate().getCityName());

            candidateRepository.save(candidate);
        }

        employee.setPosition(position);
        employee.setSalary(request.getSalary());

        employeeRepository.save(employee);

        return employee;
    }

    /**
     * Delete an existing employee.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The id of the employee wanted to delete.
     * @throws Exception For any error throw the error to the controller.
     */
    @Override
    public void delete(long id) throws Exception {
        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) throw new Exception(Messages.EmployeeNotFound);

        employeeRepository.delete(employee);
    }

}
