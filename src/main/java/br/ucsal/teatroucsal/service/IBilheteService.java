package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.BilheteDTO;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface IBilheteService {

    Boolean cadastrar(BilheteDTO bilheteDTO);

    Boolean excluir(Long id);

    BilheteDTO findById(Long id);

    //RETORNA ENTIDADE
    List<BilheteDTO> listar();
}
