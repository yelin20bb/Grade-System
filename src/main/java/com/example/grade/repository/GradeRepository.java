package com.example.grade.repository;

import java.util.List;

public interface GradeRepository {
    public List<Integer> findScores(long id);
}
