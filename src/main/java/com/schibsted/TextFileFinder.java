package com.schibsted;

import com.schibsted.FileFilter;
import com.schibsted.FileFinder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to find a concrete extension
 */
final class TextFileFinder implements FileFinder {

    private static final String FILE_EXTENSION = ".txt";
    private final FilenameFilter filter;
    private File directory;


    protected TextFileFinder() {
        this.filter = new FileFilter(FILE_EXTENSION);

    }

    @Override
    public List<File> find(String directory) {
        int filesRead = 0;
        List<File> result = new ArrayList<>();
        this.directory = new File(directory);
        File[] files = this.directory.listFiles(filter);
        for (File file : files) {
            if (file.isFile()) {
                filesRead++;
                result.add(file);
            }
        }
        System.out.println(filesRead + " files read in directory " + directory);
        return result;
    }
}
