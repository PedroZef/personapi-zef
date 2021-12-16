package one.digitalinnovatione.personapizef.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import one.digitalinnovatione.personapizef.enums.PhoneType;
import javax.persistence.*;

@Entity // anotações para a classe ser reconhecida como entidade do banco de dados

// usar Lombok elimina a necessidade de ficar gerando get e set
// deixa o codigo mais enxuto e facil de dar manutenção

// automaticamente insere os get e set
@Data


@Builder  // da um padrão de projeto para construção de objeto


@AllArgsConstructor // insere os construtores

@NoArgsConstructor
public class Phone {

    // sera usado jpa para fazer o mapeamento de dados
    // como indicquei que a classe é uma entidade
    // devo informar o id e como será gerado
    // aqui informado que sera sequencial
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    // dados virão de uma enum
    @Enumerated( EnumType.STRING)

    // indicar como dado obrigatorio pela api
    @Column(nullable = false)
    private PhoneType type;


    // indicar como dado obrigatorio pela api
    @Column(nullable = false)
    private String number;
}
