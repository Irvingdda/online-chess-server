package com.irvingdda.onlinechess.game;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MoveRepository moveRepository;
    
    static private String userQueue = "";
    public Optional<Game> putUserInQueue(String userId) {
        Optional<Game> newGameId = Optional.empty();
        
        if(userQueue.equals("")) {
            userQueue = userId;
        } else {
            //Create game and send gameId to both users
            Game newGame = new Game();
            newGame.setWhiteId(userQueue);
            newGame.setBlackId(userId);
            userQueue = "";
            
            newGame = gameRepository.save(newGame);
            newGameId = Optional.of(newGame);
        }

        return newGameId;
    }

    public Move makeMove(Integer gameId, String userId, Integer pieceId, Integer column, Integer row) {
        Move move = new Move();
        move.setGameId(gameId);
        move.setPlayerId(userId);
        move.setPieceId(pieceId);
        move.setColNum(column);
        move.setRowNum(row);

        return moveRepository.save(move);
    }

    public Move makeMove(Move move) {
        return moveRepository.save(move);
    }

    public Optional<Game> getGameById(Integer gameId) {
        return gameRepository.findById(gameId);
    }
}
