package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.service.IBilheteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bilhete")
public class BilheteController {
    private final IBilheteService bilheteService;

    public BilheteController(IBilheteService bilheteService) {
        this.bilheteService = bilheteService;
    }
}
