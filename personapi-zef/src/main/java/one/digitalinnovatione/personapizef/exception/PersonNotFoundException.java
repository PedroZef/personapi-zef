package one.digitalinnovatione.personapizef.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception{

    public PersonNotFoundException(Long id) {

        // super -> para utilozar a hierarquia de excssões do java
        super("Não encontrada pessoa com ID : " + id);
    }
}
