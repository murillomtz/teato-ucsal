package br.ucsal.teatroucsal.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_bilhete")
@Data
@NoArgsConstructor
public class BilheteEntity implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private ClienteEntity cliente;

    @OneToOne()
    @JoinColumn(name = "cadeira_id")
    @JsonIgnore
    private CadeiraEntity cadeira;
}