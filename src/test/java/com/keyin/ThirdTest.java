
//ThirdTest
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ThirdTest {

    private SuggestionEngine suggestionEngine;
    private SuggestionsDatabase suggestionsDatabase;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
        suggestionsDatabase = new SuggestionsDatabase();
    }

    @Test
    public void testLoadDictionaryData() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Map<String, Integer> wordDB = suggestionEngine.getWordSuggestionDB();
        Assertions.assertTrue(wordDB.size() > 0);
    }

    @Test
    public void testKnownWords() {
        Map<String, Integer> wordMap = new HashMap<>();
        wordMap.put("known", 1);
        suggestionsDatabase.setWordMap(wordMap);

        suggestionEngine.setWordSuggestionDB(suggestionsDatabase);

        Assertions.assertTrue(suggestionEngine.generateSuggestions("known").isEmpty());
    }

    @Test
    public void testUnknownWords() {
        Map<String, Integer> wordMap = new HashMap<>();
        wordMap.put("known", 1);
        suggestionsDatabase.setWordMap(wordMap);

        suggestionEngine.setWordSuggestionDB(suggestionsDatabase);

        Assertions.assertFalse(suggestionEngine.generateSuggestions("unknwn").isEmpty());
    }

    @Test
    public void testSuggestionsDatabase() {
        Map<String, Integer> wordMap = new HashMap<>();
        wordMap.put("test", 1);
        suggestionsDatabase.setWordMap(wordMap);

        Assertions.assertEquals(wordMap, suggestionsDatabase.getWordMap());
    }
}
