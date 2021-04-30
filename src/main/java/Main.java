import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String           sourcePathOne    = "D:\\Random\\One";
        String           sourcePathTest   = "D:\\Random\\Test";
        try {
            ZipController converterOne  = new ZipController(sourcePathOne);
            ZipController converterTest = new ZipController(sourcePathTest);
            String zipPathOne           = converterOne.convertSourceToZip();
            String zipPathTest          = converterTest.convertSourceToZip();
            System.out.println(zipPathOne);
            System.out.println(zipPathTest);
        }
        catch (IOException ex) {
            System.out.println("[ERROR] Error occurred in the zipping process.");
            ex.printStackTrace();
        }
    }

    public static void traverseSourceContents(final String sourcePath) {
        File currentSource       = new File(sourcePath);
        File[] sourceContents    = currentSource.listFiles();
        for (int fileIndex = 0; fileIndex < sourceContents.length; fileIndex++) {
            File currentFile = sourceContents[fileIndex];
            System.out.println(currentFile.getName());
            if(currentFile.isDirectory() && !currentFile.isHidden()) {
                String tempCurrentPath = currentFile.getAbsolutePath();
                traverseSourceContents(tempCurrentPath);
            }
        }
    }
}
