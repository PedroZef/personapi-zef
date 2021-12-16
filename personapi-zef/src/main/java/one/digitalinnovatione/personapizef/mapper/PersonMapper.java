package one.digitalinnovatione.personapizef.mapper;

import one.digitalinnovatione.personapizef.dto.request.PersonDTO;
import one.digitalinnovatione.personapizef.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target= "birthDate", source= "birthDate" ,dateFormat = "dd-MM-yyy")
        // toModel para converter de objeto para banco de dados
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
