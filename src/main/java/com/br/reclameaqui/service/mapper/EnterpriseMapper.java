package com.br.reclameaqui.service.mapper;

import com.br.reclameaqui.domain.*;
import com.br.reclameaqui.service.dto.EnterpriseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Enterprise and its DTO EnterpriseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnterpriseMapper extends EntityMapper<EnterpriseDTO, Enterprise> {


}
