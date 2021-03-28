package br.com.jhonatanserafim.peoplemanager.v2.dto;

import br.com.jhonatanserafim.peoplemanager.jpa.domain.Gender;
import io.swagger.annotations.ApiModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApiModel("PersonResponseV2")
public class PersonResponseV2 extends PersonEntryV2 {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class Builder {

        private final PersonResponseV2 personResponse;

        private Builder() {
            this.personResponse = new PersonResponseV2();
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder withName(String name) {
            personResponse.setName(name);
            return this;
        }

        public Builder withGender(Gender gender) {
            personResponse.setGender(gender);
            return this;
        }

        public Builder withEmail(String email) {
            personResponse.setEmail(email);
            return this;
        }

        public Builder withBirthDate(LocalDate birthDate) {
            personResponse.setBirthDate(birthDate);
            return this;
        }

        public Builder withNaturalness(String naturalness) {
            personResponse.setNaturalness(naturalness);
            return this;
        }

        public Builder withNationality(String nationality) {
            personResponse.setNationality(nationality);
            return this;
        }

        public Builder withCpf(String cpf) {
            personResponse.setCpf(cpf);
            return this;
        }

        public Builder withAddress(String address) {
            personResponse.setAddress(address);
            return this;
        }

        public Builder withId(Long id) {
            personResponse.setId(id);
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            personResponse.setCreatedAt(createdAt);
            return this;
        }

        public Builder withUpdatedAt(LocalDateTime updatedAt) {
            personResponse.setUpdatedAt(updatedAt);
            return this;
        }

        public PersonResponseV2 build() {
            return personResponse;
        }
    }
}
