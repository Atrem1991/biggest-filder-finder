import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

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

    //TODO: 24B, 234Kb, 36Mb, 34GB, 42Tb
    public static String getHumanReadableSize(long size){
        long Kb = 1024;
        long Mb = 1048576;
        long Gb = 1073741824;
        long Tb = 1099511627776l;

        String result = null;

        if (0 < size && size < Kb){
            result = size + "B";
        }
        else if(Kb < size && size < Mb){
            int outResult = (int) Math.round((double)(size/1024));
            result = outResult + "Kb";
        }
        else if(Mb < size && size < Gb){
            int outResult = (int) Math.round((double)(size/1048576));
            result = outResult + "Mb";
        }
        else if(Gb < size && size < Tb){
            int outResult = (int) Math.round((double)(size/1073741824));
            result = outResult + "Gb";
        }
        else if(size >= Tb){
            int outResult = (int) Math.round((double)(size/1099511627776l));
            result = outResult + "Tb";
        }
        else {
            System.out.println("Введены неверные данные");
        }
        return result;
    }

    public static long getSizeFromHumanReadable(String size){
        String digitStr = size.replaceAll("[^0-9]+","");
        long digits = Long.parseLong(digitStr);
        String measureUnit = size.replaceAll("[0-9]+", "");

        long Kb = 1024;
        long Mb = 1048576;
        long Gb = 1073741824;
        long Tb = 1099511627776l;

        long result = 0;

        if(measureUnit.equalsIgnoreCase("b")){
            result = digits;
        } else if(measureUnit.equalsIgnoreCase("k")
                || measureUnit.equalsIgnoreCase("Kb")){
            result = digits * Kb;
        } else if(measureUnit.equalsIgnoreCase("m")
                || measureUnit.equalsIgnoreCase("mb")){
            result = digits * Mb;
        } else if (measureUnit.equalsIgnoreCase("g")
                || measureUnit.equalsIgnoreCase("gb")){
            result = digits * Gb;
        } else if (measureUnit.equalsIgnoreCase("t")
                || measureUnit.equalsIgnoreCase("tb")){
            result = digits * Tb;
        } else {
            System.out.println("Введены неверные данные");
        }
        return result;
    }
}
