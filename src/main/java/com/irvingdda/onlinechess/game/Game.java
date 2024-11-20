package com.irvingdda.onlinechess.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String whiteId;

    private String blackId;
}
