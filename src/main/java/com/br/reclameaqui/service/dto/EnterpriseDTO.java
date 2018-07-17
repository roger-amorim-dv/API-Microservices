package com.br.reclameaqui.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Enterprise entity.
 */
public class EnterpriseDTO implements Serializable {

    private String id;

    private String title;

    private String locale;

    private String company;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnterpriseDTO enterpriseDTO = (EnterpriseDTO) o;
        if (enterpriseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enterpriseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnterpriseDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", locale='" + getLocale() + "'" +
            ", company='" + getCompany() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
