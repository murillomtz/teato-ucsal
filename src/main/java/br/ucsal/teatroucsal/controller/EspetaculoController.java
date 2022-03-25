package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.service.IEspetaculoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/espetaculo")
public class EspetaculoController {
    private final IEspetaculoService espetaculoService;

    public EspetaculoController(IEspetaculoService espetaculoService) {
        this.espetaculoService = espetaculoService;
    }
}
