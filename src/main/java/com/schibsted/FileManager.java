package com.schibsted;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class that manages the file information.
 */
class FileManager implements Manager {

    private final String fileName;
    private final String absolutePath;
    private List<String[]> searchingList;
    private String[] words;
    private int score = 0;

    protected FileManager(File file, String textToFind) {
        this.searchingList = new ArrayList<>();
        this.absolutePath = file.getAbsolutePath();
        this.fileName = file.getName();
        this.words = textToFind.split("\\s");
        splitSentence(words.length);
    }

    public void findText() throws IOException {
        for (String[] arrayText : searchingList) {
            String textToFind = Arrays.stream(arrayText).collect(Collectors.joining(" "));
            if (streamService(textToFind)) {
                calculatePercentage(arrayText.length);
                return;
            }
        }
    }

    /**
     * Method that adds to the top a the searching list the most valuable sentence.
     *
     * @param position
     */
    private void splitSentence(int position) {
        if (position > 0) {
            searchingList.add(Arrays.copyOfRange(words, 0, position));
            splitSentence(position - 1);
        }
    }

    /**
     * Method that searches the sentence in a line.
     *
     * @param text
     * @return
     * @throws IOException
     */
    private boolean streamService(String text) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(absolutePath));
        return stream.anyMatch(lines -> lines.contains(text));
    }

    private void calculatePercentage(int length) {
        score = 100 * length / words.length;
    }

    public int getScore() {
        return score;
    }

    public String getFileName() {
        return fileName;
    }
}
