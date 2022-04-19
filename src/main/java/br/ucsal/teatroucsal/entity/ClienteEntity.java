package br.ucsal.teatroucsal.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "tb_cliente")
@Data
@NoArgsConstructor
public class ClienteEntity implements Serializable {

    @JsonInclude(Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "nome")
    private String nome;

    @JsonInclude(Include.NON_EMPTY)
    @Column(unique = true, name = "cpf")
    private String cpf;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "idade")
    private Integer idade;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(unique = true, name = "email")
    @Email
    private String email;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "endereco")
    private String endereco;

    @OneToOne(mappedBy = "cliente" ,cascade=CascadeType.PERSIST)
    private BilheteEntity bilhete;
}
