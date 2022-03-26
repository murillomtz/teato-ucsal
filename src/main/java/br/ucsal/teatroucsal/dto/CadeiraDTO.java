package br.ucsal.teatroucsal.dto;

import br.ucsal.teatroucsal.entity.BilheteEntity;
import br.ucsal.teatroucsal.entity.CadeiraEntity;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CadeiraDTO extends RepresentationModel<CadeiraDTO> {

    private Long id;

    @NotBlank(message = "Informe o local")
    private String local;

    @NotBlank(message = "Informe o custo")
    private Integer custo;

    @NotBlank(message = "Informe o espetaculo")
    private List<Long> espetaculo;

    @NotBlank(message = "Informe o bilhete")
    private BilheteEntity bilhete;
}
