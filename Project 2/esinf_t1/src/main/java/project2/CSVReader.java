package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    private Scanner scanner;

    private static String filename;

    private final String SEPARATOR = ";";

    public CSVReader(String fileName)  {
        filename = fileName;
    }

    public List<Map<String, String>> read() {
        List<Map<String, String>> list = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(filename))){
            String line = input.readLine();
            String[] header = line.split(",");
            System.out.println(Arrays.toString(header));

            while((line = input.readLine()) != null){
                HashMap<String, String> map = new HashMap<>();
                String separator = ",";
                if (line.contains("\"")) { // se linha tiver aspas ver se campos contém vírgulas
                    boolean flag = false;
                    separator = SEPARATOR;
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '"')
                            flag = !flag;
                        if (line.charAt(i) == ',' && !flag)
                            line = line.substring(0,i) + SEPARATOR + line.substring(i + 1);
                    }
                    line = line.replaceAll("\"", "");
                }
                String[] lineFields = line.split(separator);
                for (int i = 0; i < header.length; i++)
                    map.put(header[i], lineFields[i]);


                list.add(map);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<String> fileReader(String filename) {
        List<String> list = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(filename))){
            String line = input.readLine();
            String[] header = line.split(",");
            System.out.println(Arrays.toString(header));

            while((line = input.readLine()) != null){
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}