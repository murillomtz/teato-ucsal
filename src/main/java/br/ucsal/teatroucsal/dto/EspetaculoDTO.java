package br.ucsal.teatroucsal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EspetaculoDTO extends RepresentationModel<EspetaculoDTO> {

    private Long id;

//    @Size(min = 1 , message = "Informe o valor")
    private Double valor;

    @NotBlank(message = "Informe o nome")
    private String nome;

}
