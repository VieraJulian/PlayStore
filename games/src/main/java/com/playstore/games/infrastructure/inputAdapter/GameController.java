package com.playstore.games.infrastructure.inputAdapter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> findGame(@PathVariable Long id) {
        try {
            GameResponseDTO gameResponseDTO = gameInputPort.findGameById(id);
            return new ResponseEntity<>(gameResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error getting Game", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameResponseDTO>> findAllGames(@RequestParam int page, @RequestParam int size) {
        try {
            List<GameResponseDTO> gameResponseDTO = gameInputPort.findAllGames(page, size);
            return new ResponseEntity<>(gameResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error getting Games", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<GameResponseDTO> createGame(@ModelAttribute GameRequestDTO game,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            GameResponseDTO gameResponseDTO = gameInputPort.createGame(game, file);
            return new ResponseEntity<>(gameResponseDTO, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("Error creating Game", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        try {
            String msj = gameInputPort.deleteGameById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error deleting Game", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
