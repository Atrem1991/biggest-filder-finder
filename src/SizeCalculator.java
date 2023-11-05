public class SizeCalculator {
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
        else if(Kb <= size && size < Mb){
            int outResult = (int) Math.round((double)(size/Kb));
            result = outResult + "Kb";
        }
        else if(Mb <= size && size < Gb){
            int outResult = (int) Math.round((double)(size/Mb));
            result = outResult + "Mb";
        }
        else if(Gb <= size && size < Tb){
            int outResult = (int) Math.round((double)(size/Gb));
            result = outResult + "Gb";
        }
        else if(size >= Tb){
            int outResult = (int) Math.round((double)(size/Tb));
            result = outResult + "Tb";
        }
        else {
            System.out.print("");
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
            System.out.print("");
        }
        return result;
    }
}
