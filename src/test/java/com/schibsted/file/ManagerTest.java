package com.schibsted.file;

import com.schibsted.FileUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ManagerTest {

    private static final String TEXT = "Hello world";
    private static final String FILE_NAME_1 = "test1.txt";
    private static final String FILE_NAME_2 = "test2.txt";
    private Manager manager;
    private FileUtils fileUtils;
    @Mock
    private File file;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFile1Search() throws IOException {
        fileUtils = new FileUtils();
        fileUtils.deployFile();
        manager = new FileManager(fileUtils.getTest1File(), TEXT);
        manager.findText();
        assertEquals(FILE_NAME_1, manager.getFileName());
        assertEquals(100, manager.getScore());
    }

    @Test
    public void testFile2Search() throws IOException {
        fileUtils = new FileUtils();
        fileUtils.deployFile();
        manager = new FileManager(fileUtils.getTest2File(), TEXT);
        manager.findText();
        assertEquals(FILE_NAME_2, manager.getFileName());
        assertEquals(50, manager.getScore());
    }

    @Test
    public void testFileName() {
        when(file.getName()).thenReturn(FILE_NAME_1);

        manager = new FileManager(file, TEXT);

        assertEquals(FILE_NAME_1, manager.getFileName());
    }

    @Test
    public void testScore() {
        manager = new FileManager(file, TEXT);

        assertEquals(0, manager.getScore());
    }

}
