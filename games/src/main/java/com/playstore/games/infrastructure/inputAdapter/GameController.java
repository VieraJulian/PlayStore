package com.playstore.games.infrastructure.inputAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;
import com.playstore.games.infrastructure.inputPort.IGameInputPort;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private IGameInputPort gameInputPort;

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @PostMapping("/create")
    public ResponseEntity<GameResponseDTO> createGame(@ModelAttribute GameRequestDTO game,
            @RequestParam("file") MultipartFile file) {
        try {
            GameResponseDTO gameResponseDTO = gameInputPort.createGame(game, file);
            return new ResponseEntity<>(gameResponseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("Error creating Game", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
