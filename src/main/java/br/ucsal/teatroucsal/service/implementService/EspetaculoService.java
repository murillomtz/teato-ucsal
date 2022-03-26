package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import br.ucsal.teatroucsal.service.IEspetaculoService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "espetaculo")
@Service
public class EspetaculoService implements IEspetaculoService {

    private final IEspetaculoRepository espetaculoRepository;

    public EspetaculoService(IEspetaculoRepository espetaculoRepository) {
        this.espetaculoRepository = espetaculoRepository;
    }

    @Override
    public Boolean cadastrar(EspetaculoDTO espetaculoDTO) {
        return null;
    }

    @Override
    public Boolean excluir(Long id) {
        return null;
    }

    @Override
    public EspetaculoDTO findById(Long id) {
        return null;
    }

    @Override
    public List<EspetaculoDTO> listar() {
        return null;
    }

    @Override
    public List<EspetaculoDTO> listaPorData(String email) {
        return null;
    }
}
