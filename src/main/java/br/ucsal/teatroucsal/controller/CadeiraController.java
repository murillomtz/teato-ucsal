package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.config.SwaggerConfig;
import br.ucsal.teatroucsal.service.ICadeiraService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = SwaggerConfig.CADEIRA)
@RestController
@RequestMapping("/cadeira")
public class CadeiraController {
    private final ICadeiraService cadeiraService;

    public CadeiraController(ICadeiraService cadeiraService) {
        this.cadeiraService = cadeiraService;
    }
}
