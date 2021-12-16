package one.digitalinnovatione.personapizef.controller;


import lombok.AllArgsConstructor;
import one.digitalinnovatione.personapizef.dto.request.PersonDTO;
import one.digitalinnovatione.personapizef.dto.response.MessageResponseDTO;
import one.digitalinnovatione.personapizef.exception.PersonNotFoundException;
import one.digitalinnovatione.personapizef.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    // injecao de dependencia da classe person service
    private PersonService personService;

    // Substituido por @AllArgs....
/*    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }*/


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }


    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.lastAll();
    }

    // Get para pegar uma pessoa especifica no Banco de Dados
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    // delete -  apagar uma pessoa
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

    // criar metodo PUt
    // @PathVariable para mapear no http o id da variavel
    // @RequestBody - passar o corpo da requisicao
    // @Valid  - pega mapeamento feito na person DTO
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }
}

// versão 1
/*
import lombok.AllArgsConstructor;
import one.digitalinnovation.fj.apiperson.dto.response.MessageResponseDTO;
import one.digitalinnovation.fj.apiperson.entity.Person;
import one.digitalinnovation.fj.apiperson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// controlador que sera acessao a partir de uma API REST
@RestController

// caminho principal da API
// atender nivel 1 de maturidade REST
// recursos bem definidos
// java permite definir a nivl de classe o recurso
// sempre versionar a api
@RequestMapping("/api/v1/people")


public class PersonController {

    // mapeando uma  operação http do tipo GET
    // tudo que acessamos no browse o fazemos a partir do metodo GET

//    usado para teste incial do projeto
//    @GetMapping
//    public String getBook() {
//        return "API Test!";
//    }


    // injetar a interface PersonRepository
    private PersonRepository personRepository;

    // Injeção de dependencias pelo Spring
    // e criaçãO DO Construtor
    // Inversão de controle : O Spring é quem vai instanciar todos os objetos tipo repository
    // vai gerenciar todo o ciclo de vida


    // Spring injete uma implementação do tipo repositorio aqui para dentro
    // somente o contrato personrepository
    // a vantagem de injetar dentro do construtor é que facilita a construção
    // de teste unitarios futuramente

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // informar que dados vem de uma requisição - RequestBody
    // builder permite fazer de uma forma mais encapsulada
    // o tratamento dos dados de entrada
    // O Lombok vai criar automaicamente uma classe message.builder
    // e com base nos atributos vai criar todos metodos para preencher os dados
    // muito util na criação de metodos unitários

    // metodo Post de REST
    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder().message("Pessoa criada com ID : " + savedPerson.getId()).build();

    }


}
 */