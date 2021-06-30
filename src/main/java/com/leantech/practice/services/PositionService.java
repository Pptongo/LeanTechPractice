package com.leantech.practice.services;

import java.util.List;

import com.leantech.practice.response.PositionResponse;

/**
 * Interface used to specify all availables methods for PositionService.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public interface PositionService {
    
    /**
     * Get all positions with a list of all employess related with each position.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return A list of @see {@link PositionResponse}.
     */
    public List<PositionResponse> getAll();
    
}
