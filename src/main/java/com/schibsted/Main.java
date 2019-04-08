package com.schibsted;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String EXIT = ":quit";

    public static void main(String[] args) {

        final FileFinder finder = new TextFileFinder();
        final Ranking ranking = new FileRanking();
        final FileManagerFactory fileManagerFactory = new FileManagerFactory();

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
                        List<Manager> lManagers = fileManagerFactory.createFileManager(files, line);
                        ranking.create(lManagers);
                        ranking.printResults();
                    } else {
                        find = false;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error while processing the files: " + e.getMessage());
            }
        }
    }

}
