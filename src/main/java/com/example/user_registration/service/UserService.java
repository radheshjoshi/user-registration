package com.example.user_registration.service;

import com.example.user_registration.dto.UserDto;
import com.example.user_registration.entity.User;
import com.example.user_registration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(User user) throws Exception {
        try{
            String password=passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            repository.save(user);
            return createUserDtoFromUser(user);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public UserDto getUserByUserName(String userName) throws Exception {
        try {
            Optional<User> user = repository.findByUsername(userName);
            return createUserDtoFromUser(user.get());
        } catch (UsernameNotFoundException e) {
            throw new Exception(e.getMessage());
        }
    }

    private UserDto createUserDtoFromUser(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}
