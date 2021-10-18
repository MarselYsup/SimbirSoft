package ru.simbir.soft.dao;

import java.util.Map;

public interface WordsRepository {
    void save(Map<String,Integer> words,String url);
    void writeByUrl(String url);
    void deleteByUrl(String url);

}
