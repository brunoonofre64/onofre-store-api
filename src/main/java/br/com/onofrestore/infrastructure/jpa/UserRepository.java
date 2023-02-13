package br.com.onofrestore.infrastructure.jpa;

import br.com.onofrestore.domain.entities.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
