package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface IEspetaculoService {

    Boolean cadastrar(EspetaculoDTO espetaculoDTO);

    Boolean excluir(Long id);

    EspetaculoDTO findById(Long id);

    //RETORNA ENTIDADE
    List<EspetaculoDTO> listar();

}
