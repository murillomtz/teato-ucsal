package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.dto.CadeiraDTO;
import br.ucsal.teatroucsal.service.ICadeiraService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "cadeira")
@Service
public class CadeiraService implements ICadeiraService {

    @Override
    public Boolean cadastrar(CadeiraDTO cadeiraDTO) {
        return null;
    }

    @Override
    public Boolean excluir(Long id) {
        return null;
    }

    @Override
    public CadeiraDTO findById(Long id) {
        return null;
    }

    @Override
    public List<CadeiraDTO> listar() {
        return null;
    }
}
