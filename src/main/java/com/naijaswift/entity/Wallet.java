package com.naijaswift.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private BigDecimal balance = BigDecimal.ZERO;

   @Column(nullable = false)
   private String currency = "NGN";

   @OneToOne
   @JoinColumn(name = "user_id",referencedColumnName = "id")
   private User user;
    
}
