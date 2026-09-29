package com.example.grade.service;

import com.example.grade.repository.GradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository repo;
    @InjectMocks
    private GradeService service;

    @Test
    public void testAverage_normal() {
        when(repo.findScores(1)).thenReturn(List.of(80, 90, 100));
        double average = service.getAverage(1);
        assertEquals(90, average, 0.001);
        }


    @Test
    void testAverage_empty() {
        when(repo.findScores(1)).thenReturn(List.of());
        assertThrows(IllegalArgumentException.class,
                () -> service.getAverage(1));
    }

    @Test
    void testAverage_null() {
        when(repo.findScores(1)).thenReturn(null);
        assertThrows(IllegalArgumentException.class,
                () -> service.getAverage(1));
    }

    @ParameterizedTest
    @CsvSource({"95,优秀", "90,优秀", "75,及格", "60,及格", "50,不及格"})
    void testLevel(int score, String expected) {
        when(repo.findScores(1)).thenReturn(List.of(score));
        assertEquals(expected, service.level(1));
    }
}
