package br.ucsal.teatroucsal.repository;

import br.ucsal.teatroucsal.entity.BilheteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBilheteRepository extends JpaRepository<BilheteEntity, Long> {
}
