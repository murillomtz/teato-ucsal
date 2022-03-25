package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.service.IClienteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "cliente")
@Service
public class ClienteService implements IClienteService {
}
