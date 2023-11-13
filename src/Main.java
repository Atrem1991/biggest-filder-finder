import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        ParametersBag parametersBag = new ParametersBag(args);

        String filePath = parametersBag.getPath();
        long sizeLimit = parametersBag.getLimit();

        File file = new File(filePath);
        Node root = new Node(file, sizeLimit);


        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);
        System.out.println(System.currentTimeMillis() - start + " mlsec.");

    }
    public static long getFolderSize(File path){
        if(path.isFile()){
            return path.length();
        }
        long sum = 0;
        File[] files = path.listFiles();
        for(File file : files){
            sum+=getFolderSize(file);
        }
        return sum;
    }
}