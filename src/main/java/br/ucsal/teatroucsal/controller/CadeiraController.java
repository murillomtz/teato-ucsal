package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.constant.HyperLinkConstant;
import br.ucsal.teatroucsal.dto.CadeiraDTO;
import br.ucsal.teatroucsal.model.Response;
import br.ucsal.teatroucsal.service.ICadeiraService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Api(tags = SwaggerConfig.CADEIRA)
@RestController
@RequestMapping("/cadeira")
public class CadeiraController {
    private final ICadeiraService cadeiraService;

    public CadeiraController(ICadeiraService cadeiraService) {
        this.cadeiraService = cadeiraService;
    }


    //    @ApiOperation(value = "Cadastrar um novo bilhete")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Bilhete criado com sucesso"),
//            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarCadeira(@Valid @RequestBody CadeiraDTO cadeira) {

        Response<Boolean> response = new Response<>();

        response.setData(cadeiraService.cadastrar(cadeira));
        response.setStatusCode(HttpStatus.CREATED.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).
                cadastrarCadeira(cadeira))
                .withSelfRel());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .consultarCadeira(cadeira.getId())).withRel(HyperLinkConstant.CONSULTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .listarCadeiras()).withRel(HyperLinkConstant.LISTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .excluirCadeira(cadeira.getId())).withRel(HyperLinkConstant.EXCLUIR.getValor()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    //    @ApiOperation(value = "Listar todos os bilhetes cadastrados")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Lista de bilhetes exibida com sucesso"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @GetMapping
    public ResponseEntity<Response<List<CadeiraDTO>>> listarCadeiras() {
        Response<List<CadeiraDTO>> response = new Response<>();
        response.setData(this.cadeiraService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .listarCadeiras()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<CadeiraDTO>> consultarCadeira(@PathVariable Long id) {
        Response<CadeiraDTO> response = new Response<>();
        CadeiraDTO cadeira = this.cadeiraService.consultar(id);
        response.setData(cadeira);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .consultarCadeira(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).excluirCadeira(id))
                .withRel(HyperLinkConstant.EXCLUIR.getValor()));
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).atualizarCadeira(cadeira)).
                        withRel(HyperLinkConstant.ATUALIZAR.getValor()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //    @ApiOperation(value = "Excluir bilhete")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "bilhete excluída com sucesso"),
//            @ApiResponse(code = 404, message = "bilhete não encontrada"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity<Response<Boolean>> excluirCadeira(@PathVariable Long idDelete) {
        Response<Boolean> response = new Response<>();
        response.setData(this.cadeiraService.excluir(idDelete));
        response.setStatusCode(HttpStatus.OK.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).
                excluirCadeira(idDelete))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class)
                .listarCadeiras()).withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //    @ApiOperation(value = "Atualizar bilhete")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "bilhete atualizada com sucesso"),
//            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
//            @ApiResponse(code = 404, message = "bilhete não encontrada"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarCadeira(@RequestBody CadeiraDTO cadeira) {
        Response<Boolean> response = new Response<>();
        response.setData(this.cadeiraService.atualizar(cadeira));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).atualizarCadeira(cadeira))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CadeiraController.class).listarCadeiras())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
