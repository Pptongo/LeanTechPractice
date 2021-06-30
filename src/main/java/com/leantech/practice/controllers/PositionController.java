package com.leantech.practice.controllers;

import java.util.List;

import com.leantech.practice.response.PositionResponse;
import com.leantech.practice.services.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manage all the web services for position entities.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@RestController()
@RequestMapping(value = "/api/1.0/positions")
public class PositionController {

    /**
     * Create an injection of @see {@link PositionService} to manage all business logic.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private PositionService positionService;
    
    /**
     * Get all positions with their employees list.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return @see {@link PositionResponse} Return a list of all positions with the list of employees in each position.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PositionResponse>> getAll() {
        try {
            return new ResponseEntity<>(positionService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
