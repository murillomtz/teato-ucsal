package br.ucsal.teatroucsal;

import br.ucsal.teatroucsal.controller.EspetaculoController;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.entity.BilheteEntity;
import br.ucsal.teatroucsal.entity.CadeiraEntity;
import br.ucsal.teatroucsal.entity.ClienteEntity;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import br.ucsal.teatroucsal.repository.IBilheteRepository;
import br.ucsal.teatroucsal.repository.ICadeiraRepository;
import br.ucsal.teatroucsal.repository.IClienteRepository;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import br.ucsal.teatroucsal.service.IBilheteService;
import br.ucsal.teatroucsal.service.ICadeiraService;
import br.ucsal.teatroucsal.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class TeatroUcsalApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private IEspetaculoRepository espetaculoRepository;

    @Autowired
    private IBilheteRepository bilheteRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ICadeiraRepository cadeiraRepository;

    public static void main(String[] args) {
        SpringApplication.run(TeatroUcsalApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TeatroUcsalApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        EspetaculoEntity espetaculo1 = new EspetaculoEntity();
        espetaculo1.setNome("MID - Homens de Preto");
        espetaculo1.setValor(56.00);


        ClienteEntity cliete1 = new ClienteEntity();
        cliete1.setCpf("068.795.288-01");
        cliete1.setEmail("odono@ucsal.edu.br");
        cliete1.setNome("Dono da Porra Toda");
        cliete1.setEndereco("Rua Alagoas");
        cliete1.setIdade(28);

        BilheteEntity bilhete1 = new BilheteEntity();
        bilhete1.setCliente(cliete1);


        CadeiraEntity cadeira1 = new CadeiraEntity();
        List<EspetaculoEntity> espetaculoList = new ArrayList<>();
        cadeira1.setBilhete(bilhete1);
        cadeira1.setEspetaculo(espetaculoList);
        cadeira1.setCusto(20);
        cadeira1.setLocal("a1");

        espetaculoList.add(espetaculo1);
        bilhete1.setCadeira(cadeira1);
        cliete1.setBilhete(bilhete1);

        espetaculoRepository.save(espetaculo1);
        clienteRepository.save(cliete1);
        bilheteRepository.save(bilhete1);
        cadeiraRepository.save(cadeira1);
//        EspetaculoDTO espetaculo2 = new EspetaculoDTO();
//        espetaculo2.setNome("MID - Lacote não é Marca de Ladrão");
//        espetaculo2.setValor(156.00);
//        espetaculoController.cadastrar(espetaculo2);
    }
}
