package br.com.jhonatanserafim.peoplemanager.jpa.repository;

import br.com.jhonatanserafim.peoplemanager.jpa.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    boolean existsByCpf(String cpf);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    boolean existsByCpfAndIdIsNot(String cpf, Long id);

}
