package br.ucsal.teatroucsal.service.implementService;

import br.ucsal.teatroucsal.constant.MensagensConstant;
import br.ucsal.teatroucsal.controller.BilheteController;
import br.ucsal.teatroucsal.controller.EspetaculoController;
import br.ucsal.teatroucsal.dto.BilheteDTO;
import br.ucsal.teatroucsal.entity.BilheteEntity;
import br.ucsal.teatroucsal.entity.CadeiraEntity;
import br.ucsal.teatroucsal.entity.ClienteEntity;
import br.ucsal.teatroucsal.exception.GenericException;
import br.ucsal.teatroucsal.repository.IBilheteRepository;
import br.ucsal.teatroucsal.repository.ICadeiraRepository;
import br.ucsal.teatroucsal.repository.IClienteRepository;
import br.ucsal.teatroucsal.service.IBilheteService;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CacheConfig(cacheNames = "bilhete")
@Service
public class BilheteService implements IBilheteService {

    private final IBilheteRepository bilheteRepository;
    private final ICadeiraRepository cadeiraRepository;
    private final IClienteRepository clienteRepository;
    private ModelMapper mapper;

    public BilheteService(IBilheteRepository bilheteRepository, ICadeiraRepository cadeiraRepository,
                          IClienteRepository clienteRepository) {
        this.mapper = new ModelMapper();
        this.bilheteRepository = bilheteRepository;
        this.cadeiraRepository = cadeiraRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Boolean atualizar(BilheteDTO bilheteDTO) {
        try {
            this.consultar(bilheteDTO.getId());
            return this.cadastrarOuAtualizar(bilheteDTO);
        } catch (GenericException m) {
            throw m;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(BilheteDTO bilheteDTO) {
        try {

            if (bilheteDTO.getId() != null) {

                throw new GenericException(MensagensConstant.ERRO_ID_INFORMADO.getValor(), HttpStatus.BAD_REQUEST);
            }


//            if (this.espetaculoRepository.findById(espetaculoDTO.getId()) != null) {
//
//                throw new GenericException(MensagensConstant.ERRO_SITUACAO_CADASTRADO_ANTERIORMENTE.getValor(),
//                        HttpStatus.BAD_REQUEST);
//            }

            return this.cadastrarOuAtualizar(bilheteDTO);

        } catch (GenericException c) {

            throw c;
        } catch (Exception e) {

            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            if (this.bilheteRepository.findById(id).isPresent()) {
                this.bilheteRepository.deleteById(id);
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
    public BilheteDTO consultar(Long id) {
        try {
            Optional<BilheteEntity> bilheteOptional = this.bilheteRepository.findById(id);
            if (bilheteOptional.isPresent()) {
                return this.mapper.map(bilheteOptional.get(), BilheteDTO.class);
            }
            throw new GenericException(MensagensConstant.ERRO_SITUACAO_NAO_ENCONTRADO.getValor(), HttpStatus.NOT_FOUND);
        } catch (GenericException m) {
            throw m;
        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<BilheteDTO> listar() {
        try {
            List<BilheteEntity> bilheteEntityList = this.bilheteRepository.findAll();

            List<BilheteDTO> bilhetesDTO = new ArrayList<>();

            for (BilheteEntity bilhete : bilheteEntityList) {
                BilheteDTO bilheteDTO = new BilheteDTO();
                bilheteDTO.setId(bilhete.getId());
                bilheteDTO.setCadeira(bilhete.getCadeira().getId());
                bilheteDTO.setCliente(bilhete.getCliente().getId());
                bilhetesDTO.add(bilheteDTO);
            }


            bilhetesDTO.forEach(bilhete -> bilhete.add(WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(BilheteController.class).consultarBilhete(bilhete.getId()))
                    .withSelfRel()));

            return bilhetesDTO;

        } catch (Exception e) {
            throw new GenericException(MensagensConstant.ERRO_GENERICO.getValor(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Boolean cadastrarOuAtualizar(BilheteDTO bilheteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        CadeiraEntity cadeiraEntity = new CadeiraEntity();

        BilheteEntity bilheteEntity = new BilheteEntity();
        if (bilheteDTO.getId() != null) {
            bilheteEntity.setId(bilheteDTO.getId());
        }

        clienteEntity = clienteRepository.findById(bilheteDTO.getCliente()).get();
        bilheteEntity.setCliente(clienteEntity);
        cadeiraEntity = cadeiraRepository.findById(bilheteDTO.getCadeira()).get();
        bilheteEntity.setCadeira(cadeiraEntity);

        this.bilheteRepository.save(bilheteEntity);
        return Boolean.TRUE;
    }
}
