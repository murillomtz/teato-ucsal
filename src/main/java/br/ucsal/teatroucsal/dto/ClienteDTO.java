package br.ucsal.teatroucsal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

    private Long id;

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Informe o cpf")
    private String cpf;

    @NotBlank(message = "Informe a idade")
    private Integer idade;

    @NotBlank(message = "Informe o email")
    @Email
    private String email;

    @NotBlank(message = "Informe o endereco")
    private String endereco;

    @NotBlank(message = "Informe o bilhete")
    private Long bilhete;

}
