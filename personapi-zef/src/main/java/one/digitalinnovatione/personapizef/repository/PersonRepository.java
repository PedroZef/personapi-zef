package one.digitalinnovatione.personapizef.repository;

import lombok.AllArgsConstructor;
import one.digitalinnovatione.personapizef.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
