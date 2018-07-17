package com.br.reclameaqui.web.rest;

import com.br.reclameaqui.ProjetoReclameApp;

import com.br.reclameaqui.domain.Enterprise;
import com.br.reclameaqui.repository.EnterpriseRepository;
import com.br.reclameaqui.service.EnterpriseService;
import com.br.reclameaqui.service.dto.EnterpriseDTO;
import com.br.reclameaqui.service.mapper.EnterpriseMapper;
import com.br.reclameaqui.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static com.br.reclameaqui.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EnterpriseResource REST controller.
 *
 * @see EnterpriseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjetoReclameApp.class)
public class EnterpriseResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALE = "AAAAAAAAAA";
    private static final String UPDATED_LOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnterpriseRepository enterpriseRepository;


    @Autowired
    private EnterpriseMapper enterpriseMapper;
    

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEnterpriseMockMvc;

    private Enterprise enterprise;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnterpriseResource enterpriseResource = new EnterpriseResource(enterpriseService);
        this.restEnterpriseMockMvc = MockMvcBuilders.standaloneSetup(enterpriseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Enterprise createEntity() {
        Enterprise enterprise = new Enterprise()
            .title(DEFAULT_TITLE)
            .locale(DEFAULT_LOCALE)
            .company(DEFAULT_COMPANY)
            .description(DEFAULT_DESCRIPTION);
        return enterprise;
    }

    @Before
    public void initTest() {
        enterpriseRepository.deleteAll();
        enterprise = createEntity();
    }

    @Test
    public void createEnterprise() throws Exception {
        int databaseSizeBeforeCreate = enterpriseRepository.findAll().size();

        // Create the Enterprise
        EnterpriseDTO enterpriseDTO = enterpriseMapper.toDto(enterprise);
        restEnterpriseMockMvc.perform(post("/api/enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseDTO)))
            .andExpect(status().isCreated());

        // Validate the Enterprise in the database
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        assertThat(enterpriseList).hasSize(databaseSizeBeforeCreate + 1);
        Enterprise testEnterprise = enterpriseList.get(enterpriseList.size() - 1);
        assertThat(testEnterprise.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testEnterprise.getLocale()).isEqualTo(DEFAULT_LOCALE);
        assertThat(testEnterprise.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testEnterprise.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createEnterpriseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enterpriseRepository.findAll().size();

        // Create the Enterprise with an existing ID
        enterprise.setId("existing_id");
        EnterpriseDTO enterpriseDTO = enterpriseMapper.toDto(enterprise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnterpriseMockMvc.perform(post("/api/enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Enterprise in the database
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        assertThat(enterpriseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEnterprises() throws Exception {
        // Initialize the database
        enterpriseRepository.save(enterprise);

        // Get all the enterpriseList
        restEnterpriseMockMvc.perform(get("/api/enterprises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enterprise.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].locale").value(hasItem(DEFAULT_LOCALE.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    

    @Test
    public void getEnterprise() throws Exception {
        // Initialize the database
        enterpriseRepository.save(enterprise);

        // Get the enterprise
        restEnterpriseMockMvc.perform(get("/api/enterprises/{id}", enterprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enterprise.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.locale").value(DEFAULT_LOCALE.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    public void getNonExistingEnterprise() throws Exception {
        // Get the enterprise
        restEnterpriseMockMvc.perform(get("/api/enterprises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEnterprise() throws Exception {
        // Initialize the database
        enterpriseRepository.save(enterprise);

        int databaseSizeBeforeUpdate = enterpriseRepository.findAll().size();

        // Update the enterprise
        Enterprise updatedEnterprise = enterpriseRepository.findById(enterprise.getId()).get();
        updatedEnterprise
            .title(UPDATED_TITLE)
            .locale(UPDATED_LOCALE)
            .company(UPDATED_COMPANY)
            .description(UPDATED_DESCRIPTION);
        EnterpriseDTO enterpriseDTO = enterpriseMapper.toDto(updatedEnterprise);

        restEnterpriseMockMvc.perform(put("/api/enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseDTO)))
            .andExpect(status().isOk());

        // Validate the Enterprise in the database
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        assertThat(enterpriseList).hasSize(databaseSizeBeforeUpdate);
        Enterprise testEnterprise = enterpriseList.get(enterpriseList.size() - 1);
        assertThat(testEnterprise.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testEnterprise.getLocale()).isEqualTo(UPDATED_LOCALE);
        assertThat(testEnterprise.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testEnterprise.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingEnterprise() throws Exception {
        int databaseSizeBeforeUpdate = enterpriseRepository.findAll().size();

        // Create the Enterprise
        EnterpriseDTO enterpriseDTO = enterpriseMapper.toDto(enterprise);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEnterpriseMockMvc.perform(put("/api/enterprises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Enterprise in the database
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        assertThat(enterpriseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEnterprise() throws Exception {
        // Initialize the database
        enterpriseRepository.save(enterprise);

        int databaseSizeBeforeDelete = enterpriseRepository.findAll().size();

        // Get the enterprise
        restEnterpriseMockMvc.perform(delete("/api/enterprises/{id}", enterprise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        assertThat(enterpriseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Enterprise.class);
        Enterprise enterprise1 = new Enterprise();
        enterprise1.setId("id1");
        Enterprise enterprise2 = new Enterprise();
        enterprise2.setId(enterprise1.getId());
        assertThat(enterprise1).isEqualTo(enterprise2);
        enterprise2.setId("id2");
        assertThat(enterprise1).isNotEqualTo(enterprise2);
        enterprise1.setId(null);
        assertThat(enterprise1).isNotEqualTo(enterprise2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnterpriseDTO.class);
        EnterpriseDTO enterpriseDTO1 = new EnterpriseDTO();
        enterpriseDTO1.setId("id1");
        EnterpriseDTO enterpriseDTO2 = new EnterpriseDTO();
        assertThat(enterpriseDTO1).isNotEqualTo(enterpriseDTO2);
        enterpriseDTO2.setId(enterpriseDTO1.getId());
        assertThat(enterpriseDTO1).isEqualTo(enterpriseDTO2);
        enterpriseDTO2.setId("id2");
        assertThat(enterpriseDTO1).isNotEqualTo(enterpriseDTO2);
        enterpriseDTO1.setId(null);
        assertThat(enterpriseDTO1).isNotEqualTo(enterpriseDTO2);
    }
}
