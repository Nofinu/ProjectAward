package com.example.nul_back.model;

import com.example.nul_back.dto.VoteReceive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int game;
    private int voteMonth;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return game == vote.game && voteMonth == vote.voteMonth && Objects.equals(name, vote.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, game, voteMonth);
    }
}
