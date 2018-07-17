package com.br.reclameaqui.service;

import com.br.reclameaqui.service.dto.EnterpriseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Enterprise.
 */
public interface EnterpriseService {

    /**
     * Save a enterprise.
     *
     * @param enterpriseDTO the entity to save
     * @return the persisted entity
     */
    EnterpriseDTO save(EnterpriseDTO enterpriseDTO);

    /**
     * Get all the enterprises.
     *
     * @return the list of entities
     */
    List<EnterpriseDTO> findAll();


    /**
     * Get the "id" enterprise.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnterpriseDTO> findOne(String id);

    /**
     * Delete the "id" enterprise.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
