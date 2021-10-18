package ru.simbir.soft.services;

import java.util.Arrays;
import java.util.List;

public class StringParser implements Parser{
    private final static List<String> separators = Arrays.asList(".","!","?",",","\"","\'",
            ";",":","[","]","(",")","\t","\r","\n","«","»","—","-","–","`",
            "©","&","|","1","2","3","4","5","6","7","8","9","0","+"
            ,"$","%","@","#","*","№","/","\\");
    @Override
    public String parse(String line) {
        if (line == null || line.isEmpty()) {
            return line;
        }
        for (String separator :
                separators) {
            line = line.replace(separator, " ");
        }
        return line;
    }
}
