package enn.monitor.log.event.extraction.pattern;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by weize on 18-6-21.
 */
public class Dictionary implements Serializable {
    private Map<String, Set<Pattern>> wordPatternIndex;
    private Set<String> eventTypes;

    public Dictionary() {
        wordPatternIndex = new HashMap<>();
        eventTypes = new HashSet<>();
    }

    public void addPatterns(Map<String, String> patterns) {
        for (Map.Entry<String, String> entry : patterns.entrySet()) {
            String str = entry.getKey();
            String eventType = entry.getValue();
            eventTypes.add(eventType);
            String[] words = str.split(" ");
            for (String word : words) {
                addIndex(word, new Pattern(words, eventType));
            }
        }
    }

    public Set<String> getEventTypes() {
        return this.eventTypes;
    }

    public Set<Pattern> getPattern(String word) {
        return wordPatternIndex.get(word);
    }

    public void addIndex(String word, Pattern pattern) {
        if (!wordPatternIndex.containsKey(word)) {
            wordPatternIndex.put(word, new HashSet<>());
        }
        wordPatternIndex.get(word).add(pattern);
    }

}
