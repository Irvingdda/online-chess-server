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
public class Move {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer gameId;

    private String playerId;

    private Integer pieceId;

    private Integer colNum;

    private Integer rowNum;

    private Boolean isSpecial;
    private Boolean enPassant;
    private Boolean isCastle;
    private Boolean isCastleReverse;
}
