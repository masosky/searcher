package com.schibsted;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileManagerFactory {

    protected static List<Manager> createFileManager(List<File> files, String textToFind) throws IOException {
        List<Manager> managers = new ArrayList<>();
        for (File file : files) {
            Manager manager = new FileManager(file, textToFind);
            manager.findText();
            managers.add(manager);

        }
        return managers;
    }
}
