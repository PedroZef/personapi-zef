package one.digitalinnovatione.personapizef.service;

import lombok.AllArgsConstructor;
import one.digitalinnovatione.personapizef.dto.request.PersonDTO;
import one.digitalinnovatione.personapizef.dto.response.MessageResponseDTO;
import one.digitalinnovatione.personapizef.entity.Person;
import one.digitalinnovatione.personapizef.exception.PersonNotFoundException;
import one.digitalinnovatione.personapizef.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import one.digitalinnovatione.personapizef.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    // Substituido por @AllArgs....
/*
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
*/

    // codigo repetido em findById e Delete
    // foi extraido via refactor e criado um metodo
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        // convertar todo DTO em uma unica entidade
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    // metodo para listar todas as pessoas
    public List<PersonDTO> lastAll() {
        // metodo para listar todo mundo
        List<Person> allPeople = personRepository.findAll();

        // converter o objeto allPeple em um objeto PersonDTO
        // chamar a api stream() usada para transformação e manipulação de listas
        // map() mapeia daca um dos registr de allPeople
        return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }


    // diferença entre melhoria e refatorar
    // para refator devo ter teste unitario para garantor que
    // ao alterar o codigo , não quebrei a aplicação
    public PersonDTO findById(Long id) throws PersonNotFoundException  {
        Person person = verifyIfExists(id);
        return personMapper.toDTO( person );
    }

    public void delete(Long id) throws PersonNotFoundException  {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    // pareceido com criar pessoa
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }
}
