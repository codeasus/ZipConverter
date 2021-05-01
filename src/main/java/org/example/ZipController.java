package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipController {
    private File             sourceFile         = null;
    private FileOutputStream zipFilePath        = null;
    private ZipOutputStream  destinationZipPath = null;
    private String           zipPath            = null;

    public ZipController(String sourcePath) throws IOException {
        sourceFile         = new File(sourcePath);
        zipPath            = sourceFile.isFile()?String.format("%s\\%s", sourceFile.getParent(), (sourceFile.getName().split("\\.")[0] + "Compressed.zip")):
                                                 String.format("%s\\%s", sourceFile.getParent(), (sourceFile.getName() + "Compressed.zip"));
        zipFilePath        = new FileOutputStream(zipPath);
        destinationZipPath = new ZipOutputStream(zipFilePath);
    }

    public  void displayContent() throws IOException{
        Files.walk(Paths.get(sourceFile.getAbsolutePath())).
                forEach(System.out::println);
    }

    public String convertSourceToZip() throws  IOException {
        boolean zipExecutionStatus = ZipSource.sourceToZipObject(sourceFile, sourceFile.getName(), destinationZipPath);
        destinationZipPath.close();
        zipFilePath.close();
        if(zipExecutionStatus) {
            System.out.printf("[UPDATE] ==> %s: Execution is finished%n", sourceFile.getName());
            return zipPath;
        }
        return "";
    }

    public static List<String> getSourceContents(final String sourcePath) throws IOException {
        List<String>           fileStructure  = new ArrayList<String>();
        Path path   = Paths.get(sourcePath);

        try (Stream<Path> walk = Files.walk(path)) {
            fileStructure = walk.map(file -> file.getFileName().toString()).collect(Collectors.toList());
        }
        return fileStructure;
    }

    public static List<String> getZipContents(final String zipPath) throws  IOException {
        List<String>           fileStructure  = new ArrayList<String>();

        try (ZipFile zipFile = new ZipFile(zipPath)) {
            fileStructure = zipFile.stream().map(file -> {
                String[] pathList = file.getName().split("/");
                return pathList[pathList.length - 1];
            }).collect(Collectors.toList());
        }
        return fileStructure;
    }
}