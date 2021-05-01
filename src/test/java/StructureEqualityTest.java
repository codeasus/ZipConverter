import org.example.ZipController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;


public class StructureEqualityTest {
    private final String   sourcePathOne = "D:\\Random\\Files\\One";
    private final String   sourcePathTwo = "D:\\Random\\Files\\Two";
    private final String  sourcePathTest = "D:\\Random\\Files\\Test";
    private final String sourcePathCovid = "D:\\Random\\Files\\covid.txt";

    private final String imposterPathOne = "D:\\Random\\Files\\Special\\One";

    @Test
    @DisplayName("Test a minimal test case.")
    void exampleUnitTest() {
        Assertions.assertEquals(1,1);
    }

    @Test
    @DisplayName("Test equality of the source structure with the structure of the generated zip file.")
    void checkFileStructure() throws IOException {
        ZipController       converterOne = new ZipController(sourcePathOne);
        ZipController       converterTwo = new ZipController(sourcePathTwo);
        ZipController      converterTest = new ZipController(sourcePathTest);
        ZipController     converterCovid = new ZipController(sourcePathCovid);

        String                zipPathOne = converterOne.convertSourceToZip();
        String                zipPathTwo = converterTwo.convertSourceToZip();
        String               zipPathTest = converterTest.convertSourceToZip();
        String              zipPathCovid = converterCovid.convertSourceToZip();

        Assertions.
                assertEquals(ZipController.getSourceContents(sourcePathOne), ZipController.getZipContents(zipPathOne));
        Assertions.
                assertEquals(ZipController.getSourceContents(sourcePathTwo), ZipController.getZipContents(zipPathTwo));
        Assertions.
                assertEquals(ZipController.getSourceContents(sourcePathTest), ZipController.getZipContents(zipPathTest));
        Assertions.
                assertEquals(ZipController.getSourceContents(sourcePathCovid), ZipController.getZipContents(zipPathCovid));

//      Assert that deliberately fails
        Assertions.
                assertNotEquals(ZipController.getSourceContents(imposterPathOne), ZipController.getZipContents(zipPathOne));

    }
}
