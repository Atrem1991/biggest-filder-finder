import java.io.File;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\dell\\Desktop";
        File file = new File(filePath);

        System.out.println(getFolderSize(file));
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
