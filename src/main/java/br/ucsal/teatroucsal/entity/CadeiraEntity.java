package br.ucsal.teatroucsal.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_cadeira")
@Data
@NoArgsConstructor
public class CadeiraEntity implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "local")
    private String local;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "custo")
    private Integer custo;

    @ManyToMany(mappedBy = "cadeiras")
    private Set<EspetaculoEntity> espetaculos;

    @OneToOne(mappedBy = "cadeira")
    private BilheteEntity bilhete;
}