package br.com.onofrestore.infrastructure.jpa.specifications;

import br.com.onofrestore.domain.dto.util.SearchDTO;
import br.com.onofrestore.domain.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {

    public Specification<UserEntity> byFilter(SearchDTO dto) {
        if (ObjectUtils.isEmpty(dto.getSearch())) {
            return null;
        }
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("email"), "%" + dto.getSearch() + "%"));
            predicates.add(cb.like(root.get("username"), "%" + dto.getSearch() + "%"));

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
