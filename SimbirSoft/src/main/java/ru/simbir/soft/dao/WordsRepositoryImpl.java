package ru.simbir.soft.dao;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

    public class WordsRepositoryImpl implements WordsRepository{
    private final JdbcTemplate jdbcTemplate;
    private final static Logger logger = Logger.getLogger(WordsRepositoryImpl.class);
    public WordsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<String> rowMapperForUrl = (resultSet, i) -> {
        String name = resultSet.getString("word");
        int frequency = resultSet.getInt("frequency");
        return name+" - "+frequency;
    };
    private final static int MAX_LENGTH_WORD = 100;
    private final static String DELETE_BY_URL = "Delete from word_statistic where url = ?";
    private final static String SELECT_BY_URL = "Select * from word_statistic where url = ? order by frequency DESC ";
    private final static String INSERT = "Insert into word_statistic(url, word, frequency) VALUES(?,?,?) " +
            "on conflict (word,url) do update set frequency = word_statistic.frequency+excluded.frequency; ";
    @Override
    public void save(Map<String, Integer> words, String url) {
        logger.info("starting saving words");
        for (Map.Entry<String, Integer> word :
                words.entrySet()) {
            if(word.getKey().length()<=MAX_LENGTH_WORD) {
                try {

                jdbcTemplate.update(INSERT, url, word.getKey(), word.getValue());
                }catch (DataAccessException e) {
                    logger.error("Error with data exception  "+e+" URL = "+url+" Word = "+word.getKey()+" Frequency =  "+word.getValue());
                }
            }
            else {
                logger.warn("word has more max length! word - "+word.getKey());
            }

        }
        logger.info("finish saving words");
    }


    @Override
    public void writeByUrl(String url) {
        List<String> statistic = jdbcTemplate.query(SELECT_BY_URL,rowMapperForUrl,url);
        for (String word:
             statistic) {
            System.out.println(word);
        }
    }


    @Override
    public void deleteByUrl(String url) {
        jdbcTemplate.update(DELETE_BY_URL,url);
    }
}
