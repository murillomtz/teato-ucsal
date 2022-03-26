package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.constant.MensagensConstant;
import br.ucsal.teatroucsal.controller.EspetaculoController;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import br.ucsal.teatroucsal.exception.GenericException;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import br.ucsal.teatroucsal.service.IEspetaculoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "espetaculo")
@Service
public class EspetaculoService implements IEspetaculoService {

    private final IEspetaculoRepository espetaculoRepository;
    private ModelMapper mapper;

    public EspetaculoService(IEspetaculoRepository espetaculoRepository) {
        this.mapper = new ModelMapper();
        this.espetaculoRepository = espetaculoRepository;
    }

    @Override
    public Boolean cadastrar(EspetaculoDTO espetaculoDTO) {
        try {

            if (espetaculoDTO.getId() != null) {

                throw new GenericException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
            }

            if (this.espetaculoRepository.findById(espetaculoDTO.getId()) != null) {

                throw new GenericException(MensagensConstant.ERRO_SITUACAO_CADASTRADO_ANTERIORMENTE.getValor(),
                        HttpStatus.BAD_REQUEST);
            }

            return this.cadastrarOuAtualizar(espetaculoDTO);

        } catch (GenericException c) {

            throw c;
        } catch (Exception e) {

            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            if (this.espetaculoRepository.findById(id).isPresent()) {
                this.espetaculoRepository.deleteById(id);
                return Boolean.TRUE;
            }
            throw new GenericException(MensagensConstant.ERRO_SITUACAO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
        } catch (GenericException c) {
            throw c;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(key = "#id")
    @Override
    public EspetaculoDTO findById(Long id) {
        try {
            Optional<EspetaculoEntity> espetaculoOptional = this.espetaculoRepository.findById(id);
            if (espetaculoOptional.isPresent()) {
                return this.mapper.map(espetaculoOptional.get(), EspetaculoDTO.class);
            }
            throw new GenericException(MensagensConstant.ERRO_SITUACAO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
        } catch (GenericException m) {
            throw m;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(unless = "#result.size()<3")
    @Override
    public List<EspetaculoDTO> listar() {
        try {
            List<EspetaculoDTO> espetaculosDTO = this.mapper.map(this.espetaculoRepository.findAll(),
                    new TypeToken<List<EspetaculoDTO>>() {
                    }.getType());

            espetaculosDTO.forEach(materia -> materia.add(WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(EspetaculoController.class).consultarEspetaculo(materia.getId()))
                    .withSelfRel()));

            return espetaculosDTO;

        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Boolean cadastrarOuAtualizar(EspetaculoDTO espetaculoDTO) {

        EspetaculoEntity espetaculoEntity = new EspetaculoEntity();
        if (espetaculoDTO.getId() != null) {
            espetaculoEntity.setId(espetaculoDTO.getId());
        }
        espetaculoEntity.setValor(espetaculoDTO.getValor());
        espetaculoEntity.setNome(espetaculoDTO.getNome());

        this.espetaculoRepository.save(espetaculoEntity);
        return Boolean.TRUE;
    }
}
