package br.ucsal.teatroucsal.repository;

import br.ucsal.teatroucsal.entity.EspetaculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspetaculoRepository extends JpaRepository<EspetaculoEntity, Long> {


}
