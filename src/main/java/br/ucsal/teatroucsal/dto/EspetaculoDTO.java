package br.ucsal.teatroucsal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EspetaculoDTO extends RepresentationModel<EspetaculoDTO> {

    private Long id;

    @NotBlank(message = "Informe o valor")
    private Integer valor;

    @NotBlank(message = "Informe o nome")
    private String nome;

}
