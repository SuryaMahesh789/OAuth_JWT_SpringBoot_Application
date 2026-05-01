package com.example.springbootapplicationwithoauth.service;

import com.example.springbootapplicationwithoauth.entity.User;
import com.example.springbootapplicationwithoauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public User getOrCreateUser(String name,String email)
    {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User user = User.builder()
                            .name(name)
                            .email(email)
                            .role("USER")
                            .build();

                    return userRepository.save(user);
                });

    }


}
