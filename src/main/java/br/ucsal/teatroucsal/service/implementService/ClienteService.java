package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.dto.ClienteDTO;
import br.ucsal.teatroucsal.service.IClienteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "cliente")
@Service
public class ClienteService implements IClienteService {


    @Override
    public Boolean cadastrar(ClienteDTO clienteDTO) {
        return null;
    }

    @Override
    public Boolean excluir(Long id) {
        return null;
    }

    @Override
    public ClienteDTO findById(Long id) {
        return null;
    }

    @Override
    public List<ClienteDTO> listar() {
        return null;
    }
}
