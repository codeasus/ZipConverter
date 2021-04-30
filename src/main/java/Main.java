import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String           sourcePath    = "D:\\Random\\One";
        try {

//            ZipController converter   = new ZipController(sourcePath);
//            converter.displayContent();
//            converter.convertSourceToZip();
//            converter.cleanUp();

            String convertedZipPath = convertSourceToZip(sourcePath);
            System.out.println(convertedZipPath);

        }
        catch (IOException ex) {
            System.out.println("[ERROR] Error occurred in the zipping process.");
            ex.printStackTrace();
        }
    }
    public static String convertSourceToZip(String sourcePath) throws IOException {
        File fileToZip     = new File(sourcePath);
        String           zipPath       = String.format("%s\\%s", fileToZip.getParent(), (fileToZip.getName() + "Compressed.zip"));
        FileOutputStream zipFilePath   = new FileOutputStream(zipPath);
        ZipOutputStream zipOutputPath = new ZipOutputStream(zipFilePath);
        ZipSource.sourceToZipObject(fileToZip, fileToZip.getName(), zipOutputPath);
        zipOutputPath.close();
        zipFilePath.close();
        return  zipPath;
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
