package com.playstore.games.domain;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 300)
    private String description;
    @Column(nullable = false, scale = 2)
    private BigDecimal original_price;
    @Column(nullable = false, scale = 2)
    private BigDecimal final_price;
    @Column(nullable = false, scale = 2)
    private BigDecimal discount_price;
    private int discount;
    @Column(nullable = false)
    private Date release_date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ECategory category;
}