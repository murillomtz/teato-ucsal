package br.ucsal.teatroucsal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

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
    private List<Long> espetaculos;

    //@NotBlank(message = "Informe o bilhete")
    private Long bilhete;
}
