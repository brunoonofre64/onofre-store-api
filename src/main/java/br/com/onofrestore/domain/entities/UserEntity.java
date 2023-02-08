package br.com.onofrestore.domain.entities;

import br.com.onofrestore.domain.enums.Profiles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_USER")
@SequenceGenerator(name = "sequenceUser", sequenceName = "SQ_USER", allocationSize = 1)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceUser")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USUARIO", unique = true)
    private String username;

    @Column(name = "SENHA")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Profiles> profiles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles
                .stream()
                .map(x -> new SimpleGrantedAuthority(x.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
