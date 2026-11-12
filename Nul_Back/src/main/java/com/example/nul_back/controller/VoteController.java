package com.example.nul_back.controller;

import com.example.nul_back.dto.VoteReceive;
import com.example.nul_back.model.Vote;
import com.example.nul_back.service.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/vote")
public class VoteController {

    private VoteService service;
    private ObjectMapper mapper ;

    public VoteController(VoteService service) {
        this.service = service;
        this.mapper = new ObjectMapper();
    }

    @GetMapping()
    public ResponseEntity<String> testFunc (){
        return ResponseEntity.ok("test passe");
    }

    @PostMapping()
    public ResponseEntity<Boolean> postVote (@RequestBody VoteReceive voteReceive, HttpServletRequest request){
        String userId = getUsername(request);
        return ResponseEntity.ok(service.createVote(voteReceive,userId));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> invalidToken (HttpClientErrorException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private String getUsername (HttpServletRequest request){
        String twitchResponse = (String) request.getAttribute("twitchResponse");
        System.out.println(twitchResponse);
        JsonNode json = mapper.readTree(twitchResponse);
        return json.get("user_id").asText();
    }
}
