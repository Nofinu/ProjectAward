package com.example.nul_back.service;

import com.example.nul_back.dto.VoteReceive;
import com.example.nul_back.model.Vote;
import com.example.nul_back.repository.VoteRepository;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class VoteService {
    private VoteRepository repository;

    RestClient restClient ;

    public VoteService(VoteRepository repository) {
        this.repository = repository;

    }

    public boolean createVote (VoteReceive voteReceive,String username){

        Vote vote = dtoToEntity(voteReceive,username);

        Vote voteFound = repository.getByNameAndMonth(vote.getName(), vote.getVoteMonth());
        if (voteFound == null){
            repository.save(vote);
            return true;
        }
        if(voteFound.equals(vote)){
            return false;
        }
        if(voteFound.getName().equals(vote.getName()) && voteFound.getVoteMonth() == vote.getVoteMonth()){
            voteFound.setGame(vote.getGame());
            repository.save(voteFound);
            return true;
        }
        return false;
    }


    private Vote dtoToEntity (VoteReceive voteReceive,String username){
        return Vote.builder().name(username).game(voteReceive.getGame()).voteMonth(voteReceive.getVoteMonth()).build();
    }
}
