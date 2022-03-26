package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.CadeiraDTO;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface ICadeiraService {

    Boolean cadastrar(CadeiraDTO cadeiraDTO);

    Boolean excluir(Long id);

    CadeiraDTO findById(Long id);

    //RETORNA ENTIDADE
    List<CadeiraDTO> listar();

}
