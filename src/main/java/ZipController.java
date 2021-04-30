import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public class ZipController {
    private File             sourceFile         = null;
    private FileOutputStream zipFilePath        = null;
    private ZipOutputStream  destinationZipPath = null;

    public ZipController(String sourcePath) throws IOException {
        sourceFile         = new File(sourcePath);
        String zipPath     = String.format("%s\\%s", sourceFile.getParent(), (sourceFile.getName() + "Compressed.zip"));
        zipFilePath        = new FileOutputStream(zipPath);
        destinationZipPath = new ZipOutputStream(zipFilePath);
    }

    public void displayContent(){
        File[] contents = sourceFile.listFiles();
        for(File content: contents) {
            System.out.println(content.getName());
            if(content.isDirectory()) {
                sourceFile = content.getAbsoluteFile();
                displayContent();
            }
        }
    }

    public void convertSourceToZip() throws  IOException {
        ZipSource.sourceToZipObject(sourceFile, sourceFile.getName(), destinationZipPath);
    }

    public void cleanUp() throws IOException{
        zipFilePath.close();
        destinationZipPath.close();
    }
}


//echo "# ZipConverter" >> README.md
//        git init
//        git add README.md
//        git commit -m "first commit"
//        git branch -M main
//        git remote add origin https://github.com/codeasus/ZipConverter.git
//        git push -u origin main