package br.ucsal.teatroucsal.dto;

import br.ucsal.teatroucsal.entity.CadeiraEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BilheteDTO extends RepresentationModel<BilheteDTO> {

    private Long id;

    @NotBlank(message = "Informe o cliente")
    private Long cliente;

    @NotBlank(message = "Informe a cadeira")
    private Long cadeira;
}
