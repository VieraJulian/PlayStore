package com.playstore.users.infrastructure.inputAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playstore.users.infrastructure.dto.UserRequestDTO;
import com.playstore.users.infrastructure.dto.UserRequestUpdateDTO;
import com.playstore.users.infrastructure.dto.UserResponseDTO;
import com.playstore.users.infrastructure.inputport.IUserInputport;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserInputport userInputport;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userInputport.save(userRequestDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating User", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
            @RequestBody UserRequestUpdateDTO updateDTO) {
        try {
            UserResponseDTO userResponseDTO = userInputport.update(id, updateDTO);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error editing User", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        try {
            UserResponseDTO userResponseDTO = userInputport.findById(id);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting User", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        try {
            String msj = userInputport.deleteById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error editing User", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
