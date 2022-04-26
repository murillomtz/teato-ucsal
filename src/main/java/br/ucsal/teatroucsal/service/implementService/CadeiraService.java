package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.constant.MensagensConstant;
import br.ucsal.teatroucsal.controller.BilheteController;
import br.ucsal.teatroucsal.dto.CadeiraDTO;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.entity.BilheteEntity;
import br.ucsal.teatroucsal.entity.CadeiraEntity;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import br.ucsal.teatroucsal.exception.GenericException;
import br.ucsal.teatroucsal.repository.IBilheteRepository;
import br.ucsal.teatroucsal.repository.ICadeiraRepository;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import br.ucsal.teatroucsal.service.ICadeiraService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CacheConfig(cacheNames = "cadeira")
@Service
public class CadeiraService implements ICadeiraService {

    private final IBilheteRepository bilheteRepository;
    private final ICadeiraRepository cadeiraRepository;
    private final IEspetaculoRepository espetaculoRepository;
    private ModelMapper mapper;

    public CadeiraService(IBilheteRepository bilheteRepository, ICadeiraRepository cadeiraRepository,
                          IEspetaculoRepository espetaculoRepository) {
        this.bilheteRepository = bilheteRepository;
        this.cadeiraRepository = cadeiraRepository;
        this.espetaculoRepository = espetaculoRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public Boolean atualizar(CadeiraDTO cadeiraDTO) {
        try {
            this.consultar(cadeiraDTO.getId());
            return this.cadastrarOuAtualizar(cadeiraDTO);
        } catch (GenericException m) {
            throw m;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(CadeiraDTO cadeiraDTO) {
        try {

            if (cadeiraDTO.getId() != null) {

                throw new GenericException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
            }


//            if (this.espetaculoRepository.findById(espetaculoDTO.getId()) != null) {
//
//                throw new GenericException(MensagensConstant.ERRO_SITUACAO_CADASTRADO_ANTERIORMENTE.getValor(),
//                        HttpStatus.BAD_REQUEST);
//            }

            return this.cadastrarOuAtualizar(cadeiraDTO);

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

    @Override
    public CadeiraDTO consultar(Long id) {
        try {
            Optional<CadeiraEntity> bilheteOptional = this.cadeiraRepository.findById(id);
            if (bilheteOptional.isPresent()) {
                return this.mapper.map(bilheteOptional.get(), CadeiraDTO.class);
            }


            throw new GenericException(MensagensConstant.ERRO_SITUACAO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
        } catch (GenericException m) {
            throw m;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CadeiraDTO> listar() {
        try {
            List<CadeiraEntity> cadeiraEntityList = this.cadeiraRepository.findAll();

            List<CadeiraDTO> cadeirasDTO = new ArrayList<>();

            for (CadeiraEntity cadeira : cadeiraEntityList) {
                CadeiraDTO cadeiraDTO = new CadeiraDTO();
                cadeiraDTO.setId(cadeira.getId());
                cadeiraDTO.setBilhete(cadeira.getBilhete().getId());
                cadeiraDTO.setCusto(cadeira.getCusto());
                List<Long> listEspetaculoId= new ArrayList<>();

                if (cadeiraDTO.getEspetaculos() != null && !cadeiraDTO.getEspetaculos().isEmpty()) {

                    cadeiraDTO.getEspetaculos().forEach(espetaulo -> {
                        if (this.espetaculoRepository.findById(espetaulo).isPresent())
                            listEspetaculoId.add(this.espetaculoRepository.findById(espetaulo).get().getId());
                    });
                }
                cadeiraDTO.setEspetaculos(listEspetaculoId);
                cadeiraDTO.setLocal(cadeira.getLocal());
                cadeirasDTO.add(cadeiraDTO);
            }


            cadeirasDTO.forEach(bilhete -> bilhete.add(WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).consultarBilhete(bilhete.getId()))
                    .withSelfRel()));

            return cadeirasDTO;

        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Boolean cadastrarOuAtualizar(CadeiraDTO cadeiraDTO) {
        BilheteEntity bilheteEntity;

        CadeiraEntity cadeiraEntity = new CadeiraEntity();
        if (cadeiraDTO.getId() != null) {
            cadeiraEntity.setId(cadeiraDTO.getId());
        }

        Set<EspetaculoEntity> listEspetaculoEntity = new HashSet<>();

        if (cadeiraDTO.getEspetaculos() != null && !cadeiraDTO.getEspetaculos().isEmpty()) {

            cadeiraDTO.getEspetaculos().forEach(espetaulo -> {
                if (this.espetaculoRepository.findById(espetaulo).isPresent())
                    listEspetaculoEntity.add(this.espetaculoRepository.findById(espetaulo).get());
            });
        }
        cadeiraEntity.setEspetaculos(listEspetaculoEntity);
        bilheteEntity = bilheteRepository.findById(cadeiraDTO.getBilhete()).get();
        cadeiraEntity.setBilhete(bilheteEntity);


        this.bilheteRepository.save(bilheteEntity);
        return Boolean.TRUE;
    }
}
