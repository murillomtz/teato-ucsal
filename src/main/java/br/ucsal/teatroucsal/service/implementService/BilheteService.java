package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.dto.BilheteDTO;
import br.ucsal.teatroucsal.service.IBilheteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "bilhete")
@Service
public class BilheteService implements IBilheteService {
    @Override
    public Boolean cadastrar(BilheteDTO bilheteDTO) {
        return null;
    }

    @Override
    public Boolean excluir(Long id) {
        return null;
    }

    @Override
    public BilheteDTO findById(Long id) {
        return null;
    }

    @Override
    public List<BilheteDTO> listar() {
        return null;
    }
}
