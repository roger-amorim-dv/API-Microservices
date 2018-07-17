package com.br.reclameaqui.service.mapper;

import com.br.reclameaqui.domain.Enterprise;
import com.br.reclameaqui.service.dto.EnterpriseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-15T20:00:38-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"
)
@Component
public class EnterpriseMapperImpl implements EnterpriseMapper {

    @Override
    public Enterprise toEntity(EnterpriseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Enterprise enterprise = new Enterprise();

        enterprise.setId( dto.getId() );
        enterprise.setTitle( dto.getTitle() );
        enterprise.setLocale( dto.getLocale() );
        enterprise.setCompany( dto.getCompany() );
        enterprise.setDescription( dto.getDescription() );

        return enterprise;
    }

    @Override
    public EnterpriseDTO toDto(Enterprise entity) {
        if ( entity == null ) {
            return null;
        }

        EnterpriseDTO enterpriseDTO = new EnterpriseDTO();

        enterpriseDTO.setId( entity.getId() );
        enterpriseDTO.setTitle( entity.getTitle() );
        enterpriseDTO.setLocale( entity.getLocale() );
        enterpriseDTO.setCompany( entity.getCompany() );
        enterpriseDTO.setDescription( entity.getDescription() );

        return enterpriseDTO;
    }

    @Override
    public List<Enterprise> toEntity(List<EnterpriseDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Enterprise> list = new ArrayList<Enterprise>( dtoList.size() );
        for ( EnterpriseDTO enterpriseDTO : dtoList ) {
            list.add( toEntity( enterpriseDTO ) );
        }

        return list;
    }

    @Override
    public List<EnterpriseDTO> toDto(List<Enterprise> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EnterpriseDTO> list = new ArrayList<EnterpriseDTO>( entityList.size() );
        for ( Enterprise enterprise : entityList ) {
            list.add( toDto( enterprise ) );
        }

        return list;
    }
}
