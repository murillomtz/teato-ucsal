package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.config.SwaggerConfig;
import br.ucsal.teatroucsal.constant.HyperLinkConstant;
import br.ucsal.teatroucsal.dto.BilheteDTO;
import br.ucsal.teatroucsal.model.Response;
import br.ucsal.teatroucsal.service.IBilheteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = SwaggerConfig.BILHETE)
@RestController
@RequestMapping("/bilhete")
public class BilheteController {
    private final IBilheteService bilheteService;

    public BilheteController(IBilheteService bilheteService) {
        this.bilheteService = bilheteService;
    }

    @ApiOperation(value = "Cadastrar um novo bilhete")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bilhete criado com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
            @ApiResponse(code = 500, message = "Erro interno no serviço"),
    })
    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarBilhete(@Valid @RequestBody BilheteDTO bilhete) {

        Response<Boolean> response = new Response<>();

        response.setData(bilheteService.cadastrar(bilhete));
        response.setStatusCode(HttpStatus.CREATED.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).
                cadastrarBilhete(bilhete))
                .withSelfRel());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .consultarEspetaculo(bilhete.getId())).withRel(HyperLinkConstant.CONSULTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculos()).withRel(HyperLinkConstant.LISTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .excluirEspetaculo(bilhete.getId())).withRel(HyperLinkConstant.EXCLUIR.getValor()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ApiOperation(value = "Listar todos os bilhetes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de bilhetes exibida com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno no serviço"),
    })
    @GetMapping
    public ResponseEntity<Response<List<BilheteDTO>>> listarBilhetes() {
        Response<List<BilheteDTO>> response = new Response<>();
        response.setData(this.bilheteService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class)
                .listarBilhetes()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Consultar bilhete por código")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bilhete encontrado com sucesso"),
            @ApiResponse(code = 404, message = "bilhete não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no serviço"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<BilheteDTO>> consultarBilhete(@PathVariable Long id) {
        Response<BilheteDTO> response = new Response<>();
        BilheteDTO bilhete = this.bilheteService.consultar(id);
        response.setData(bilhete);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class)
                .consultarBilhete(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).excluirBilhete(id))
                .withRel(HyperLinkConstant.EXCLUIR.getValor()));
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).atualizarMateria(bilhete)).
                        withRel(HyperLinkConstant.ATUALIZAR.getValor()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Excluir bilhete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bilhete excluída com sucesso"),
            @ApiResponse(code = 404, message = "bilhete não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno no serviço"),
    })
    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity<Response<Boolean>> excluirBilhete(@PathVariable Long idDelete) {
        Response<Boolean> response = new Response<>();
        response.setData(this.bilheteService.excluir(idDelete));
        response.setStatusCode(HttpStatus.OK.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).
                excluirBilhete(idDelete))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class)
                .listarBilhetes()).withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Atualizar bilhete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bilhete atualizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
            @ApiResponse(code = 404, message = "bilhete não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno no serviço"),
    })
    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarMateria(@RequestBody BilheteDTO bilhete) {
        Response<Boolean> response = new Response<>();
        response.setData(this.bilheteService.atualizar(bilhete));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).atualizarMateria(bilhete))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).listarBilhetes())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
