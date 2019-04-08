package com.schibsted;

import java.io.File;
import java.util.List;

interface FileFinder {

    List<File> find(String directory);
}

