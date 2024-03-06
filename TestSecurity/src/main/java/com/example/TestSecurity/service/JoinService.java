package com.example.TestSecurity.service;

import com.example.TestSecurity.controller.JoinController;
import com.example.TestSecurity.dto.JoinDTO;
import com.example.TestSecurity.entity.UserEntity;
import com.example.TestSecurity.repository.JoinRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final JoinRepository joinRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(JoinRepository joinRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.joinRepository = joinRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(joinDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        userEntity.setRole("ROLE_USER");

        joinRepository.save(userEntity);
    }
}
