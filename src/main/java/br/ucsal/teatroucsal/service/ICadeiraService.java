package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.CadeiraDTO;

import java.util.List;

public interface ICadeiraService {

    Boolean atualizar(CadeiraDTO cadeiraDTO);

    Boolean cadastrar(CadeiraDTO cadeiraDTO);

    Boolean excluir(Long id);

    CadeiraDTO consultar(Long id);

    //RETORNA ENTIDADE
    List<CadeiraDTO> listar();

}
