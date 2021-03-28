package br.com.jhonatanserafim.peoplemanager.v1.dto;

import br.com.jhonatanserafim.peoplemanager.core.validation.CPFNonExistent;
import br.com.jhonatanserafim.peoplemanager.jpa.domain.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@ApiModel("PersonEntryV1")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonEntryV1 {

    @NotEmpty
    private String name;

    private Gender gender;

    @Email
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate birthDate;

    private String naturalness;
    private String nationality;

    @NotEmpty
    @Pattern(regexp = "[\\d]+")
    @CPF
    @CPFNonExistent
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNaturalness() {
        return naturalness;
    }

    public void setNaturalness(String naturalness) {
        this.naturalness = naturalness;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
