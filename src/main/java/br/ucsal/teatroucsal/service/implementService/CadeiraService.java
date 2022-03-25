package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.service.ICadeiraService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "cadeira")
@Service
public class CadeiraService implements ICadeiraService {
}
