package br.ucsal.teatroucsal;

import br.ucsal.teatroucsal.controller.EspetaculoController;
import br.ucsal.teatroucsal.dto.EspetaculoDTO;
import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import br.ucsal.teatroucsal.repository.IEspetaculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TeatroUcsalApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private IEspetaculoRepository espetaculoRepository;

    @Autowired
    private EspetaculoController espetaculoController;

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
        espetaculoRepository.save(espetaculo1);

//        EspetaculoDTO espetaculo2 = new EspetaculoDTO();
//        espetaculo2.setNome("MID - Lacote não é Marca de Ladrão");
//        espetaculo2.setValor(156.00);
//        espetaculoController.cadastrar(espetaculo2);
    }
}
