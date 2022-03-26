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

@RestController
@RequestMapping("/espetaculo")
public class EspetaculoController {
    private final IEspetaculoService espetaculoService;

    public EspetaculoController(IEspetaculoService espetaculoService) {
        this.espetaculoService = espetaculoService;
    }


    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarEspetaculo(@Valid @RequestBody EspetaculoDTO espetaculo) {

        Response<Boolean> response = new Response<>();

        response.setData(espetaculoService.cadastrar(espetaculo));
        response.setStatusCode(HttpStatus.CREATED.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).
                cadastrarEspetaculo(espetaculo))
                .withSelfRel());

        /*response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .atualizarMateria(materia)).withRel(HyperLinkConstant.ATUALIZAR.getValor()));*/

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculo()).withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<Response<List<EspetaculoDTO>>> listarEspetaculo() {
        Response<List<EspetaculoDTO>> response = new Response<>();
        response.setData(this.espetaculoService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculo()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<EspetaculoDTO>> consultarEspetaculo(@PathVariable Long id) {
        Response<EspetaculoDTO> response = new Response<>();
        EspetaculoDTO espetaculo = this.espetaculoService.findById(id);
        response.setData(espetaculo);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .consultarEspetaculo(id))
                .withSelfRel());
//        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).excluirMateria(id))
//                .withRel(HyperLinkConstant.EXCLUIR.getValor()));
//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).atualizarMateria(materia)).
//                        withRel(HyperLinkConstant.ATUALIZAR.getValor()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{idDelete}")
    public ResponseEntity<Response<Boolean>> excluirEspetaculo(@PathVariable Long idDelete) {
        Response<Boolean> response = new Response<>();
        response.setData(this.espetaculoService.excluir(idDelete));
        response.setStatusCode(HttpStatus.OK.value());

        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).
                excluirEspetaculo(idDelete))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class)
                .listarEspetaculo()).withRel(HyperLinkConstant.LISTAR.getValor()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
