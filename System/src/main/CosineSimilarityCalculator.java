package main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.Test1;
public class CosineSimilarityCalculator {
    public static double calculateCosineSimilarity(List<String> list1, List<String> list2) {
        Map<String, Integer> counter1 = getWordCount(list1);
        Map<String, Integer> counter2 = getWordCount(list2);

        Set<String> words = new HashSet<>();
        words.addAll(counter1.keySet());
        words.addAll(counter2.keySet());

        int[] vector1 = getWordVector(counter1, words);
        int[] vector2 = getWordVector(counter2, words);

        double dotProduct = dotProduct(vector1, vector2);
        double norm1 = calculateNorm(vector1);
        double norm2 = calculateNorm(vector2);

        return dotProduct / (norm1 * norm2);
    }

    private static Map<String, Integer> getWordCount(List<String> list1) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : list1) {
            counter.putIfAbsent(word, 0);
            counter.put(word, counter.get(word) + 1);
        }
        return counter;
    }

    private static int[] getWordVector(Map<String, Integer> counter, Set<String> words) {
        int[] vector = new int[words.size()];
        int index = 0;

        for (String word : words) {
            vector[index++] = counter.getOrDefault(word, 0);
        }
        return vector;
    }

    private static double dotProduct(int[] v1, int[] v2) {
        int product = 0;

        for (int i = 0; i < v1.length; i++) {
            product += v1[i] * v2[i];
        }
        return product;
    }

    private static double calculateNorm(int[] vector) {
        int sumOfSquares = 0;

        for (int value : vector) {
            sumOfSquares += value * value;
        }
        return Math.sqrt(sumOfSquares);
    }
}