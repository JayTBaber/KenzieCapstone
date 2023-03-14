package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ScoreRepository;
import com.kenzie.appserver.service.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAllScores() {
        return (List<Score>) scoreRepository.findAll();
    }

    public Optional<Score> getScoreById(long id) {
        return scoreRepository.findById(id);
    }

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public void deleteScore(Score score) {
        scoreRepository.delete(score);
    }

    public void addScore(Score score) {
        scoreRepository.save(score);
    }

    public void updateScoreById(long id, Score score) {
        Optional<Score> scoreOptional = scoreRepository.findById(id);
        if (scoreOptional.isPresent()) {
            Score scoreToUpdate = scoreOptional.get();
            scoreToUpdate.setScoreValue(score.getScoreValue());
            scoreToUpdate.setPlayerId(score.getPlayerId());
            scoreToUpdate.setCard(score.getCard());
            scoreRepository.save(scoreToUpdate);
        }
    }

    public void deleteScoreById(long id) {
        scoreRepository.deleteById(id);
    }
}
