package br.com.onofrestore.infrastructure.jpa.repositories;

import br.com.onofrestore.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Set<RoleEntity> findByUuidIn(Set<String> profiles);
}

