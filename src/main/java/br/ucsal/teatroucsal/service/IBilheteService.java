package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.BilheteDTO;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface IBilheteService {

    Boolean atualizar( BilheteDTO bilheteDTO);

    Boolean cadastrar(BilheteDTO bilheteDTO);

    Boolean excluir(Long id);

    BilheteDTO consultar(Long id);

    //RETORNA ENTIDADE
    List<BilheteDTO> listar();

}
