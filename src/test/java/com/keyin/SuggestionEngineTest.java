//SuggestionEngineTest
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine;
    private SuggestionsDatabase suggestionsDatabase;

    @BeforeEach
    public void setUp() {
        suggestionEngine = new SuggestionEngine();
        suggestionsDatabase = new SuggestionsDatabase();
    }

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertFalse(suggestionEngine.generateSuggestions("hello").contains("hello"));
    }

    @Test
    public void testSuggestionsAsMock() {
        Map<String, Integer> wordMapForTest = new HashMap<>();
        wordMapForTest.put("test", 1);

        ReflectionUtils.setField(suggestionsDatabase, "wordMap", wordMapForTest);

        suggestionEngine.setWordSuggestionDB(suggestionsDatabase);

        Assertions.assertFalse(suggestionEngine.generateSuggestions("test").contains("test"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("tes").contains("test"));
    }
}
