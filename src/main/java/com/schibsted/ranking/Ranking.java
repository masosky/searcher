package com.schibsted.ranking;

import com.schibsted.file.Manager;

import java.util.List;

public interface Ranking {

    void create(List<Manager> lManagers);
    void showResults();
}
