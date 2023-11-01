import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {


        String filePath = "C:\\Users\\Artem.Gusev\\Desktop\\For learning";
        File file = new File(filePath);

        long start = System.currentTimeMillis();
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long sum = pool.invoke(calculator);
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - start);

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
