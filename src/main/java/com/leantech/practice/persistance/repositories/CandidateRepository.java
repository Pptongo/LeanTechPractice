package com.leantech.practice.persistance.repositories;

import com.leantech.practice.persistance.entities.CandidateEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the database sentences for @see {@link CandidateEntity}
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface CandidateRepository extends CrudRepository<CandidateEntity, Long> {
    
}
