package me.chohyeonjae.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.chohyeonjae.springbootdeveloper.domain.User;
import me.chohyeonjae.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String email) { //loadUserByUsername 필수로 구현해야함
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
