import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipOutputStream;

public class ZipController {
    private  File             sourceFile         = null;
    private  FileOutputStream zipFilePath        = null;
    private  ZipOutputStream  destinationZipPath = null;
    private  String           zipPath            = null;
//    private  boolean          zipExecutionStatus = false;

    public  ZipController(String sourcePath) throws IOException {
        sourceFile         = new File(sourcePath);
        zipPath            = String.format("%s\\%s", sourceFile.getParent(), (sourceFile.getName() + "Compressed.zip"));
        zipFilePath        = new FileOutputStream(zipPath);
        destinationZipPath = new ZipOutputStream(zipFilePath);
    }

    public  void displayContent() throws IOException{
        Files.walk(Paths.get(sourceFile.getAbsolutePath())).
                forEach(content -> System.out.println());
    }

    public String convertSourceToZip() throws  IOException {
        boolean zipExecutionStatus = ZipSource.sourceToZipObject(sourceFile, sourceFile.getName(), destinationZipPath);
        destinationZipPath.close();
        zipFilePath.close();
        if(zipExecutionStatus) {
            System.out.println("[UPDATE] : Execution is finished");
            return zipPath;
        }
        return "";
    }
}