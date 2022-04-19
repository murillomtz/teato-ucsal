package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.service.IClienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = SwaggerConfig.CLIENTE)
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }
}
