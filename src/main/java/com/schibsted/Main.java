package com.schibsted;

import com.schibsted.file.FileManager;
import com.schibsted.file.Manager;
import com.schibsted.finder.TextFileFinder;
import com.schibsted.finder.FileFinder;
import com.schibsted.ranking.FileRanking;
import com.schibsted.ranking.Ranking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String EXIT = ":quit";

    public static void main(String[] args) {

        final FileFinder finder = new TextFileFinder();
        final Ranking ranking = new FileRanking();

        if (args.length == 0) {
            throw new IllegalArgumentException("No directory provided");
        }

        List<File> files = finder.find(args[0]);
        if (!files.isEmpty()) {
            Scanner keyboard = new Scanner(System.in);
            boolean find = true;
            try {
                while (find) {
                    System.out.print("Search> ");
                    final String line = keyboard.nextLine();
                    if (!line.equals(EXIT)) {
                        List<Manager> lManagers = createFileManager(files, line);
                        ranking.create(lManagers);
                        ranking.showResults();
                    } else {
                        find = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error while processing the files: " + e.getMessage());
            }
        }
    }

    /**
     * Creates the objects to manage the text file search.
     * @param files
     * @param textToFind
     * @return
     * @throws IOException
     */
    private static List<Manager> createFileManager(List<File> files, String textToFind) throws IOException {
        List<Manager> lManagers = new ArrayList<>();
        for (File file : files) {
            Manager manager = new FileManager(file, textToFind);
            manager.findText();
            lManagers.add(manager);

        }
        return lManagers;
    }
}
