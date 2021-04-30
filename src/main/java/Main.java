import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String            sourcePathOne  = "D:\\Random\\One";
        String            sourcePathTest = "D:\\Random\\Test";
        String          zipSourcePathOne = "D:\\Random\\OneCompressed.zip";

        try {
            ZipController converterOne   = new ZipController(sourcePathOne);
            ZipController converterTest  = new ZipController(sourcePathTest);
            String        zipPathOne     = converterOne.convertSourceToZip();
            String        zipPathTest    = converterTest.convertSourceToZip();
            System.out.println(zipPathOne);
            System.out.println(zipPathTest);

            List<String> trOne = ZipController.getSourceContents(sourcePathOne);
            System.out.println(trOne.toString());

            List<String> trTwo = ZipController.getZipContents(zipSourcePathOne);
            System.out.println(trTwo.toString());
        }
        catch (IOException ex) {
            System.out.println("[ERROR] Error occurred in the zipping process.");
            ex.printStackTrace();
        }
    }
}
