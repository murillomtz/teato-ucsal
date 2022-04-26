package br.ucsal.teatroucsal;

import br.ucsal.teatroucsal.entity.BilheteEntity;
import br.ucsal.teatroucsal.entity.CadeiraEntity;
import br.ucsal.teatroucsal.entity.ClienteEntity;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import br.ucsal.teatroucsal.repository.IBilheteRepository;
import br.ucsal.teatroucsal.repository.ICadeiraRepository;
import br.ucsal.teatroucsal.repository.IClienteRepository;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		ClienteEntity cliente1 = new ClienteEntity();
		cliente1.setNome("Dono da Porra Toda");
		cliente1.setCpf("068.795.288-01");
		cliente1.setIdade(28);
		cliente1.setEmail("odono@ucsal.edu.br");
		cliente1.setEndereco("Rua Alagoas");

		CadeiraEntity cadeira1 = new CadeiraEntity();
		cadeira1.setLocal("a1");
		cadeira1.setCusto(20);
		
		BilheteEntity bilhete1 = new BilheteEntity();
		bilhete1.setCliente(cliente1);
		bilhete1.setCadeira(cadeira1);

		Set<EspetaculoEntity> espetaculoList = new HashSet<>();
		espetaculoList.add(espetaculo1);
		cadeira1.setEspetaculos(espetaculoList);

		espetaculoRepository.save(espetaculo1);
		bilheteRepository.save(bilhete1);
		cadeiraRepository.save(cadeira1);
		clienteRepository.save(cliente1);

//        EspetaculoDTO espetaculo2 = new EspetaculoDTO();
//        espetaculo2.setNome("MID - Lacote não é Marca de Ladrão");
//        espetaculo2.setValor(156.00);
//        espetaculoController.cadastrar(espetaculo2); NAO USAR
	}
}
