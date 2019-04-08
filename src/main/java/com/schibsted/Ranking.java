package com.schibsted;

import java.util.List;

interface Ranking {

    void create(List<Manager> managers);
    void printResults();
}
