package me.chohyeonjae.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //id 반환
    public String getUsername() {
        return email;
    }

    @Override //pw 반환
    public String getPassword() {
        return password;
    }

    @Override //계정만료여부 반환
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //패스워드의 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true;
    }
}
