package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.service.IEspetaculoService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "espetaculo")
@Service
public class EspetaculoService implements IEspetaculoService {
}
