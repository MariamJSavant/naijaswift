package com.naijaswift.service;

import java.math.BigDecimal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naijaswift.dto.UserRegistrationDTO;
import com.naijaswift.entity.User;
import com.naijaswift.entity.Wallet;
import com.naijaswift.exception.UserAlreadyExistException;
import com.naijaswift.mapper.UserMapper;
import com.naijaswift.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
      private final UserRepository userRepository;
      private final UserMapper userMapper;
      private final BCryptPasswordEncoder passwordEncoder;

      @Transactional
      public void registerUser(UserRegistrationDTO dto){
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistException("Email"+dto.getEmail()+"is already taken");
        }

        User user = userMapper.toEntity(dto);
          
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setTransactionPin(passwordEncoder.encode(dto.getTransactionPin()));

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCurrency("NGN");
        wallet.setUser(user);


        user.setWallet(wallet);
        userRepository.save(user);
        
      }
}