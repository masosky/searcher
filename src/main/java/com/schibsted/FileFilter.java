package com.schibsted;

import java.io.File;
import java.io.FilenameFilter;

final class FileFilter implements FilenameFilter {

    private final String extension;

    protected FileFilter(String extension){
        this.extension = extension;
    }

    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(extension);
    }
}
