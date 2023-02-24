package br.com.onofrestore.infrastructure.jpa.repositories;

import br.com.onofrestore.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    UserEntity findByUuid(String uuidUser);
    @Query("select u from UserEntity u where u.username = :username")
    UserEntity findByUsername(@Param("username") String username);
    void deleteByUuid(String uuid);
}
