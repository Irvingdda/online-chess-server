package com.irvingdda.onlinechess.game;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class GameController {
    
    @Autowired
    private GameService gameService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/")
    public @ResponseBody String Hello() {
        return "Hello world";
    }
    

    @MessageMapping("/game")
    @GetMapping("/play/{userId}")
    public @ResponseBody String enterGame(
        @PathVariable String userId
    ) {
        Optional<Game> game = gameService.putUserInQueue(userId);
        if(game.isPresent()) {
            messagingTemplate.convertAndSendToUser(game.get().getWhiteId(), "/queue/game", game.get());
            messagingTemplate.convertAndSendToUser(game.get().getBlackId(), "/queue/game", game.get());
        }

        return "Ok";
    }

    @MessageMapping("/game/move")
    @PostMapping("/move")
    public void movePiece(
        @RequestBody Move move
    ) {
        Optional<Game> game = gameService.getGameById(move.getGameId());
        if(game.isPresent()) {
            gameService.makeMove(move);
            System.out.println("Making move");
            if(move.getPlayerId().equals(game.get().getWhiteId())) {
                messagingTemplate.convertAndSendToUser(game.get().getBlackId(), "/queue/move", move);
            } else {
                messagingTemplate.convertAndSendToUser(game.get().getWhiteId(), "/queue/move", move);
            }
        }
    }
    

}
