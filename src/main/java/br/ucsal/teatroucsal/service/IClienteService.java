package br.ucsal.teatroucsal.service;

import br.ucsal.teatroucsal.dto.ClienteDTO;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;

import java.util.List;

public interface IClienteService {

    Boolean cadastrar(ClienteDTO clienteDTO);

    Boolean excluir(Long id);

    ClienteDTO findById(Long id);

    List<ClienteDTO> listar();
}
