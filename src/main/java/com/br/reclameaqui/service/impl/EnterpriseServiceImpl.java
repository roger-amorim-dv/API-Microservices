package com.br.reclameaqui.service.impl;

import com.br.reclameaqui.service.EnterpriseService;
import com.br.reclameaqui.domain.Enterprise;
import com.br.reclameaqui.repository.EnterpriseRepository;
import com.br.reclameaqui.service.dto.EnterpriseDTO;
import com.br.reclameaqui.service.mapper.EnterpriseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Enterprise.
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final Logger log = LoggerFactory.getLogger(EnterpriseServiceImpl.class);

    private final EnterpriseRepository enterpriseRepository;

    private final EnterpriseMapper enterpriseMapper;

    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository, EnterpriseMapper enterpriseMapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.enterpriseMapper = enterpriseMapper;
    }

    /**
     * Save a enterprise.
     *
     * @param enterpriseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnterpriseDTO save(EnterpriseDTO enterpriseDTO) {
        log.debug("Request to save Enterprise : {}", enterpriseDTO);
        Enterprise enterprise = enterpriseMapper.toEntity(enterpriseDTO);
        enterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toDto(enterprise);
    }

    /**
     * Get all the enterprises.
     *
     * @return the list of entities
     */
    @Override
    public List<EnterpriseDTO> findAll() {
        log.debug("Request to get all Enterprises");
        return enterpriseRepository.findAll().stream()
            .map(enterpriseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one enterprise by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<EnterpriseDTO> findOne(String id) {
        log.debug("Request to get Enterprise : {}", id);
        return enterpriseRepository.findById(id)
            .map(enterpriseMapper::toDto);
    }

    /**
     * Delete the enterprise by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Enterprise : {}", id);
        enterpriseRepository.deleteById(id);
    }
}
