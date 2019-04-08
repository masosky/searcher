package com.schibsted.ranking;

import com.schibsted.file.Manager;

import java.util.*;

/**
 * Implementation that manages the ranking.
 */
public class FileRanking implements Ranking {

    private static final Integer MAX_RESULTS = 10;
    private Map<Integer, List<String>> result;


    /**
     * This method creates the ranking files. The files with score 0 are skipped.
     *
     * @param lManagers
     */
    @Override
    public void create(List<Manager> lManagers) {
        this.result = new LinkedHashMap<>();
        lManagers.sort(Comparator.comparingInt(Manager::getScore).reversed());
        if (lManagers.size() > MAX_RESULTS) {
            lManagers = lManagers.subList(0, MAX_RESULTS);
        }
        for (Manager manager : lManagers) {
            int fileScore = manager.getScore();
            if (fileScore > 0) {
                if (result.containsKey(fileScore)) {
                    result.get(fileScore).add(manager.getFileName());
                } else {
                    List lFileNames = new ArrayList<String>();
                    lFileNames.add(manager.getFileName());
                    result.put(fileScore, lFileNames);
                }
            }
        }
    }

    /**
     * Method that prints the ranking.
     */
    @Override
    public void showResults() {
        if (!result.isEmpty()) {
            for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
                System.out.println(entry.getValue().toString() + ":" + entry.getKey() + "%");
            }
        } else {
            System.out.println("No matches found");
        }
    }
}
