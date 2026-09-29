package com.example.grade.service;

import com.example.grade.repository.GradeRepository;

import java.util.List;

public class GradeService {
    private GradeRepository repo;

    public GradeService(GradeRepository repo) {
        this.repo = repo;
    }

    public double getAverage(long id) {
        List<Integer> scores = repo.findScores(id);
            if (scores == null || scores.isEmpty()) {
                throw new IllegalArgumentException("无成绩");
            }
        int sum = 0;
        for (Integer score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public String level(long id) {
        double average = getAverage(id);
        if (average >= 90) {
            return "优秀";
        }
        else if (average >= 60) {
            return "及格";
        }
        else {
            return "不及格";
        }
    }
}