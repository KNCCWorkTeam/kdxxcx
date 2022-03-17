package com.kdxxcx.util.KDXXCX;

import com.kdxxcx.util.File.FileReader;

import java.io.IOException;

import static com.kdxxcx.util.String.AddText.addText;

public class ReadUpdate {
    public static String readUpdateString() throws IOException {
        String[] Update = new FileReader().readUpdate();
        String outputString = "";
        for (int i = 0;Update[i]!=null;i++) {
            if (Update[i].contains("~")) {
                Update[i] = Update[i].replace("~", "\t新增:");
                Update[i] = Update[i].replace("*", "\n\t\t");
                Update[i] = Update[i].replace("<", "\n");
            }
            if (Update[i].contains("|")) {
                Update[i] = Update[i].replace("|", "\t修复:");
                Update[i] = Update[i].replace("*", "\n\t\t");
                Update[i] = Update[i].replace("<", "\n");
            }
            outputString = addText(outputString, Update[i]);
        }
        return outputString;
    }
}