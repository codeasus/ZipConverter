import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String             sourcePathOne = "D:\\Random\\One";
        String            sourcePathTest = "D:\\Random\\Test";
        String           sourcePathCovid = "D:\\Random\\covid.txt";

        try {
            ZipController   converterOne = new ZipController(sourcePathOne);
            ZipController  converterTest = new ZipController(sourcePathTest);
            ZipController converterCovid = new ZipController(sourcePathCovid);
            String            zipPathOne = converterOne.convertSourceToZip();
            String           zipPathTest = converterTest.convertSourceToZip();
            String          zipPathCovid = converterCovid.convertSourceToZip();
            System.out.println(zipPathOne);
            System.out.println(zipPathTest);
            System.out.println(zipPathCovid);


//            Example test for the source One
            List<String> one    = ZipController.getSourceContents(sourcePathOne);
            System.out.println(one.toString());
            List<String> oneZip = ZipController.getZipContents(zipPathOne);
            System.out.println(oneZip.toString());

//            Example test for the source Test
            List<String> test    = ZipController.getSourceContents(sourcePathTest);
            System.out.println(test.toString());
            List<String> testZip = ZipController.getZipContents(zipPathTest);
            System.out.println(testZip.toString());

//            Example test for the source covid.txt
            List<String> covid    = ZipController.getSourceContents(sourcePathCovid);
            System.out.println(covid.toString());
            List<String> covidZip = ZipController.getZipContents(zipPathCovid);
            System.out.println(covidZip.toString());

        }
        catch (IOException ex) {
            System.out.println("[ERROR] Error occurred in the zipping process.");
            ex.printStackTrace();
        }
    }
}
