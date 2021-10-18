package ru.simbir.soft.services;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HTMLParser implements Parser {
    private Document doc;
    private final static Logger logger = Logger.getLogger(HTMLParser.class);
    @Override
    public String parse(String line) {
        try {
            doc = Jsoup.connect(line).get();
        } catch (IOException e) {
            logger.error("ERROR with url" + line);
            throw new IllegalArgumentException(e);
        }
        Element element = doc.body();
        return element.text();
    }
}