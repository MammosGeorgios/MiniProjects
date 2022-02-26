package lambda.examples.filefilter;

import java.io.File;
import java.io.FileFilter;

public class Examples {
    private static final String FILE_PATH = "d:/tmp";

    public File[] classSolutionExample() {

        FileFilter fileFilter = new JavaFileFilter();
        File dir = new File(FILE_PATH);
        return dir.listFiles(fileFilter);
    }

    public File[] anonymousSolutionExample() {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        };

        File dir = new File(FILE_PATH);
        return dir.listFiles(fileFilter);
    }

    public File[] lambdaSolutionExample(){
        FileFilter fileFilter = (File file) -> file.getName().endsWith(".java");

        File dir = new File(FILE_PATH);
        return dir.listFiles(fileFilter);
    }

}
