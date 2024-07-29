package com.playstore.games.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@SQLDelete(sql = "UPDATE games SET enabled=false WHERE id = ?")
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
    @Column(nullable = true)
    private int discount;
    @Column(nullable = false)
    private LocalDate release_date;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ECategory category;
    @Column(nullable = false)
    private boolean enabled;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private GameImage gameImage;
}
