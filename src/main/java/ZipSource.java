import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipSource {
    /*
    There is no need to instantiate or create an object from the class and since
    we can't EXPLICITLY type a static class, we have to somehow notify other developers that this class
    should not be instantiated and because of that I make the CONSTRUCTOR private so every time someone
    who is not familiar with the codebase tries to create an object, it will throw an EXCEPTION.
   */
    private ZipSource() {}

    public static void sourceToZipObject(File sourceFile, String fileName, ZipOutputStream destinationZipPath) throws IOException {
        if (sourceFile.isHidden()) {
            return;
        }
        if (sourceFile.isDirectory()) {
            if (fileName.endsWith("/")) {
                destinationZipPath.putNextEntry(new ZipEntry(fileName));
            } else {
                destinationZipPath.putNextEntry(new ZipEntry(fileName + "/"));
            }
            destinationZipPath.closeEntry();
            File[] sourceContents = sourceFile.listFiles();
            for (File file : sourceContents) {
                sourceToZipObject(file, fileName + "/" + file.getName(), destinationZipPath);
            }
            return;
        }
        FileInputStream fileInput = new FileInputStream(sourceFile);
        ZipEntry         zipEntry = new ZipEntry(fileName);
        destinationZipPath.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInput.read(bytes)) >= 0) {
            destinationZipPath.write(bytes, 0, length);
        }
        fileInput.close();
    }
}