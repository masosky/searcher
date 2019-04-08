package com.schibsted.file;

import java.io.File;
import java.io.FilenameFilter;

public final class FileFilter implements FilenameFilter {

    private final String extension;

    public FileFilter(String extension){
        this.extension = extension;
    }

    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
