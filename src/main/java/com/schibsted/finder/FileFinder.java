package com.schibsted.finder;

import java.io.File;
import java.util.List;

public interface FileFinder {

    List<File> find(String directory);
}

