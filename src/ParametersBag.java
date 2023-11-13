public class ParametersBag {
    private long limit = -1;
    private String path = "";
    String[]args;
    public ParametersBag(String[] args) {
        this.args = args;
        boolean pathBoolean = false;
        for(String str : args){
            if(str.equals("-d")){
                pathBoolean = true;
                break;
            }
        }
        if(!pathBoolean){
            throw new IllegalArgumentException("Нет адреса к файлу");
        }
        limitSearch();
        pathSearch();
    }

    public long getLimit(){
        return limit;
    }

    public String getPath(){
        return path;
    }

    private void limitSearch(){
        for(int i = 0; i < args.length; i++){
            String txt = args[i];
            if(txt.equalsIgnoreCase("-l")){
                limit = SizeCalculator.getSizeFromHumanReadable(args[i+1]);
                break;
            }
        }
    }

    private void pathSearch(){
        for(int i = 0; i < args.length; i++){
            String finalStr = "";
            String itemStr = args[i];
            String regex = "^[0-9]+[a-zA-z]+";
            if(itemStr.equalsIgnoreCase("-d")){
                for(int j = i+1; j < args.length; j++){
                    if(!args[j].equalsIgnoreCase("-l") && !args[j].matches(regex)){
                        finalStr += args[j] + " ";
                    }
                }
                path = finalStr.strip();
            }
            }
    }
}
