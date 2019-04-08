package com.schibsted.finder;

import com.schibsted.FileUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TextFileFinderTest {

    private FileFinder fileFinder;
    @Mock
    private FileUtils fileUtils;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        fileFinder = new TextFileFinder();
    }

    @Test
    public void testFileTxtDirectorySearch() throws IOException {
        fileUtils = new FileUtils();
        fileUtils.deployFile();

        List<File> lFiles = fileFinder.find(fileUtils.getFileDir().getPath());

        assertEquals(2, lFiles.size());
    }
}
