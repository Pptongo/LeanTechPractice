package com.leantech.practice.persistance.repositories;

import com.leantech.practice.persistance.entities.PositionEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the database sentences for @see {@link PositionEntity}
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PositionRepository extends CrudRepository<PositionEntity, Long> {
    
}
