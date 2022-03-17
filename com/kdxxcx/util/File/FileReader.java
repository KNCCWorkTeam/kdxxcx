package com.kdxxcx.util.File;

import com.kdxxcx.util.Array.ArrayListToArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class FileReader {

    String[] result;

    public String[] readResult() {
        return result;
    }

    public String[] readUpdate() throws IOException {
        return new FileReader(this.getClass().getClassLoader().getResourceAsStream("kdxxcx.update")).readResult();
    }

    public FileReader() {

    }

    public FileReader(File file) throws IOException {
        this(file.getPath());
    }


    public FileReader(InputStream path) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(path), StandardCharsets.UTF_8));
        String line = "";
        int StringLine = 0;
        String[] output = new String[10000];
        while ((line = in.readLine()) != null){
            output[StringLine] = line;
            StringLine++;
        }
        this.result = output;
    }

    public FileReader(String path) throws IOException {
        InputStreamReader fReader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(fReader);
        String line = "";
        ArrayList<String> arrayList = new ArrayList<>();
        while ((line = in.readLine()) != null){
            arrayList.add(line);
        }
        this.result = ArrayListToArray.arrayListToArray(arrayList);
    }
}
