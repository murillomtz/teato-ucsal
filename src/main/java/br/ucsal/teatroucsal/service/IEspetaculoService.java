package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface IEspetaculoService {

    Boolean atualizar( EspetaculoDTO materia);

    Boolean cadastrar( EspetaculoDTO espetaculoDTO);

    Boolean excluir(Long id);


    EspetaculoDTO consultar(Long id);

    //RETORNA ENTIDADE
    List<EspetaculoDTO> listar();

}
