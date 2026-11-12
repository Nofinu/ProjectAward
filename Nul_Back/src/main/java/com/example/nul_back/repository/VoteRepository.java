package com.example.nul_back.repository;

import com.example.nul_back.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote,Long> {
    @Query("select v from Vote v where v.name = ?1 and v.voteMonth = ?2")
    public Vote getByNameAndMonth (String name, int voteMonth);
}
