package br.ucsal.teatroucsal.repository;

import br.ucsal.teatroucsal.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
