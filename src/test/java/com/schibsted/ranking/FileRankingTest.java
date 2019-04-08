package com.schibsted.ranking;

import com.schibsted.file.Manager;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FileRankingTest {

    private static final String FILE_NAME_1 = "text1.txt";
    private static final String FILE_NAME_2 = "text2.txt";
    private static final Integer FILE_SCORE_1 = 100;
    private static final Integer FILE_SCORE_2 = 50;

    private Ranking ranking;
    private List<Manager> lManagers;
    @Mock
    private Manager fileManager1;
    @Mock
    private Manager fileManager2;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        ranking = new FileRanking();
        lManagers = new ArrayList<>();

    }

    @Test
    public void testRankingCreation() {
        when(fileManager1.getFileName()).thenReturn(FILE_NAME_1);
        when(fileManager2.getFileName()).thenReturn(FILE_NAME_2);
        when(fileManager1.getScore()).thenReturn(FILE_SCORE_1);
        when(fileManager2.getScore()).thenReturn(FILE_SCORE_2);

        lManagers.add(fileManager1);
        lManagers.add(fileManager2);

        ranking.create(lManagers);

        verify(fileManager1, times(1)).getFileName();
        verify(fileManager2, times(1)).getFileName();

        verify(fileManager1, times(2)).getScore();
        verify(fileManager2, times(2)).getScore();
    }

    @Test
    public void testShowResult() {
        when(fileManager1.getFileName()).thenReturn(FILE_NAME_1);
        when(fileManager2.getFileName()).thenReturn(FILE_NAME_2);
        when(fileManager1.getScore()).thenReturn(FILE_SCORE_1);
        when(fileManager2.getScore()).thenReturn(FILE_SCORE_2);

        lManagers.add(fileManager1);
        lManagers.add(fileManager2);

        ranking.create(lManagers);

        ranking.showResults();
    }
}
