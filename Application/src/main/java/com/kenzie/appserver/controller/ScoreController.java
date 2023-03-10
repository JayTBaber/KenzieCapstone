package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.ScoreService;
import com.kenzie.appserver.service.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/")
    public List<Score> getAllScores() {
        return scoreService.getAllScores();
    }

    @PostMapping("/")
    public void addScore(@RequestBody Score score) {
        scoreService.addScore(score);
    }

    @GetMapping("/{id}")
    public Score getScoreById(@PathVariable long id) {
        return scoreService.getScoreById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteScoreById(@PathVariable long id) {
        scoreService.deleteScoreById(id);
    }

    @PutMapping("/{id}")
    public void updateScoreById(@PathVariable long id, @RequestBody Score score) {
        scoreService.updateScoreById(id, score);
    }
}
