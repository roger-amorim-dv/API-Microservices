package com.br.reclameaqui.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.br.reclameaqui.service.EnterpriseService;
import com.br.reclameaqui.web.rest.errors.BadRequestAlertException;
import com.br.reclameaqui.web.rest.util.HeaderUtil;
import com.br.reclameaqui.service.dto.EnterpriseDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Enterprise.
 */
@RestController
@RequestMapping("/api")
public class EnterpriseResource {

    private final Logger log = LoggerFactory.getLogger(EnterpriseResource.class);

    private static final String ENTITY_NAME = "enterprise";

    private final EnterpriseService enterpriseService;

    public EnterpriseResource(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    /**
     * POST  /enterprises : Create a new enterprise.
     *
     * @param enterpriseDTO the enterpriseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enterpriseDTO, or with status 400 (Bad Request) if the enterprise has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/enterprises")
    @Timed
    public ResponseEntity<EnterpriseDTO> createEnterprise(@RequestBody EnterpriseDTO enterpriseDTO) throws URISyntaxException {
        log.debug("REST request to save Enterprise : {}", enterpriseDTO);
        if (enterpriseDTO.getId() != null) {
            throw new BadRequestAlertException("A new enterprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnterpriseDTO result = enterpriseService.save(enterpriseDTO);
        return ResponseEntity.created(new URI("/api/enterprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /enterprises : Updates an existing enterprise.
     *
     * @param enterpriseDTO the enterpriseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enterpriseDTO,
     * or with status 400 (Bad Request) if the enterpriseDTO is not valid,
     * or with status 500 (Internal Server Error) if the enterpriseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/enterprises")
    @Timed
    public ResponseEntity<EnterpriseDTO> updateEnterprise(@RequestBody EnterpriseDTO enterpriseDTO) throws URISyntaxException {
        log.debug("REST request to update Enterprise : {}", enterpriseDTO);
        if (enterpriseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnterpriseDTO result = enterpriseService.save(enterpriseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, enterpriseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /enterprises : get all the enterprises.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of enterprises in body
     */
    @GetMapping("/enterprises")
    @Timed
    public List<EnterpriseDTO> getAllEnterprises() {
        log.debug("REST request to get all Enterprises");
        return enterpriseService.findAll();
    }

    /**
     * GET  /enterprises/:id : get the "id" enterprise.
     *
     * @param id the id of the enterpriseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enterpriseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/enterprises/{id}")
    @Timed
    public ResponseEntity<EnterpriseDTO> getEnterprise(@PathVariable String id) {
        log.debug("REST request to get Enterprise : {}", id);
        Optional<EnterpriseDTO> enterpriseDTO = enterpriseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enterpriseDTO);
    }

    /**
     * DELETE  /enterprises/:id : delete the "id" enterprise.
     *
     * @param id the id of the enterpriseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/enterprises/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnterprise(@PathVariable String id) {
        log.debug("REST request to delete Enterprise : {}", id);
        enterpriseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
