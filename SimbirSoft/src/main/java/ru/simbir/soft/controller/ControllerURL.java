package ru.simbir.soft.controller;

import ru.simbir.soft.dao.WordsRepositoryImpl;
import ru.simbir.soft.services.HTMLParser;
import ru.simbir.soft.services.StringParser;

import java.util.HashMap;
import org.apache.log4j.Logger;

public class ControllerURL implements Controller<String>{
    private final static Logger logger = Logger.getLogger(ControllerURL.class);
    private final static int maxSizeMap = 1000;
    private final static int maxSizeOperation = 10000;
    private int countOfOperation = 0;
    private final HTMLParser htmlParser;
    private final StringParser stringParser;
    private final WordsRepositoryImpl wordsRepository;

    public ControllerURL(HTMLParser htmlParser, StringParser stringParser, WordsRepositoryImpl wordsRepository) {
        this.htmlParser = htmlParser;
        this.stringParser = stringParser;
        this.wordsRepository = wordsRepository;
    }


    @Override
    public void countWords(String source) {
        logger.info("Starting program process");
        HashMap<String,Integer> wordsStatistic = new HashMap<>();
        String inputLine = htmlParser.parse(source);
        inputLine = stringParser.parse(inputLine);
        String[] lines = inputLine.split(" ");
            for(String strings:lines) {
                if(wordsStatistic.size()>=maxSizeMap||countOfOperation>maxSizeOperation) {
                    logger.info("Updating Map with "+maxSizeMap+" size and count of operation " +countOfOperation);
                    countOfOperation=0;
                    wordsRepository.save(wordsStatistic,source);
                    wordsStatistic = new HashMap<>();
                }
                if (!strings.isEmpty()) {
                        countOfOperation++;
                        wordsStatistic.put(strings, wordsStatistic.containsKey(strings) ? wordsStatistic.get(strings) + 1 : 1);
                }
            }
            if(wordsStatistic.size()>0) {
                logger.info("Last save from map with "+wordsStatistic.size()+" size");
                wordsRepository.save(wordsStatistic,source);
                wordsStatistic = null;
            }
            wordsRepository.writeByUrl(source);
            logger.info("Finishing program process");

    }
}
