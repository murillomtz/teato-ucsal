package br.ucsal.teatroucsal.controller;

import br.ucsal.teatroucsal.constant.HyperLinkConstant;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.model.Response;
import br.ucsal.teatroucsal.service.IEspetaculoService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@Api(tags = SwaggerConfig.ESPETACULO)
@RestController
@RequestMapping("/espetaculo")
public class EspetaculoController {
    private final IEspetaculoService espetaculoService;

    public EspetaculoController(IEspetaculoService espetaculoService) {
        this.espetaculoService = espetaculoService;
    }


//    @ApiOperation(value = "Cadastrar um novo espetaculo")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Espetaculo criado com sucesso"),
//            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarEspetaculo(@Valid @RequestBody EspetaculoDTO espetaculo) {

        Response<Boolean> response = new Response<>();

        response.setData(espetaculoService.cadastrar(espetaculo));
        response.setStatusCode(HttpStatus.CREATED.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).
                cadastrarEspetaculo(espetaculo))
                .withSelfRel());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .consultarEspetaculo(espetaculo.getId())).withRel(HyperLinkConstant.CONSULTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculos()).withRel(HyperLinkConstant.LISTAR.getValor()));

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .excluirEspetaculo(espetaculo.getId())).withRel(HyperLinkConstant.EXCLUIR.getValor()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

//    @ApiOperation(value = "Listar todos os espetaculos cadastrados")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Lista de espetaculos exibida com sucesso"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @GetMapping
    public ResponseEntity<Response<List<EspetaculoDTO>>> listarEspetaculos() {
        Response<List<EspetaculoDTO>> response = new Response<>();
        response.setData(this.espetaculoService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculos()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @ApiOperation(value = "Consultar espetaculo por código")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Espetaculo encontrado com sucesso"),
//            @ApiResponse(code = 404, message = "Espetaculo não encontrado"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<EspetaculoDTO>> consultarEspetaculo(@PathVariable Long id) {
        Response<EspetaculoDTO> response = new Response<>();
        EspetaculoDTO espetaculo = this.espetaculoService.consultar(id);
        response.setData(espetaculo);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .consultarEspetaculo(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).excluirEspetaculo(id))
                .withRel(HyperLinkConstant.EXCLUIR.getValor()));
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).atualizarMateria(espetaculo)).
                        withRel(HyperLinkConstant.ATUALIZAR.getValor()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @ApiOperation(value = "Excluir espetaculo")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Espetaculo excluída com sucesso"),
//            @ApiResponse(code = 404, message = "Espetaculo não encontrada"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity<Response<Boolean>> excluirEspetaculo(@PathVariable Long idDelete) {
        Response<Boolean> response = new Response<>();
        response.setData(this.espetaculoService.excluir(idDelete));
        response.setStatusCode(HttpStatus.OK.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).
                excluirEspetaculo(idDelete))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculos()).withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @ApiOperation(value = "Atualizar espetaculo")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Espetaculo atualizada com sucesso"),
//            @ApiResponse(code = 400, message = "Erro na requisição enviada pelo cliente"),
//            @ApiResponse(code = 404, message = "Espetaculo não encontrada"),
//            @ApiResponse(code = 500, message = "Erro interno no serviço"),
//    })
    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarMateria(@RequestBody EspetaculoDTO espetaculo) {
        Response<Boolean> response = new Response<>();
        response.setData(this.espetaculoService.atualizar(espetaculo));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).atualizarMateria(espetaculo))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).listarEspetaculos())
                .withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
