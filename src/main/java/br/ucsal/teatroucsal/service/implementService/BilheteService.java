package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.service.IBilheteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "bilhete")
@Service
public class BilheteService implements IBilheteService {
}
