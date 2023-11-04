package Features;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DanbooruAutoCorrect {
    private HashSet<String> tagSet;
    private Map<String, Integer> tagDistances;

    private String filePath = "./danbooru_tags.csv";

    public DanbooruAutoCorrect() {
        tagSet = new HashSet<>();
        tagDistances = new HashMap<>();
        loadTags();
    }

    private void loadTags() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String tag = line.split(",")[0].trim();
                if (!tag.isEmpty()) {
                    tagSet.add(tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String autocorrectTags(String userInput) {
        StringBuilder correctedTags = new StringBuilder();
        String[] tags = userInput.split(",");

        for (String tag : tags) {
            String trimmedTag = tag.trim();
            if (tagSet.contains(trimmedTag)) {
                correctedTags.append(trimmedTag).append(",");
            } else {
                String closestMatch = findClosestMatch(trimmedTag);
                correctedTags.append(closestMatch).append(",");
            }
        }
        return correctedTags.toString().replaceAll(",$", "");
    }

    private String findClosestMatch(String tag) {
        int minDistance = Integer.MAX_VALUE;
        String closestMatch = "";

        for (String validTag : tagSet) {
            int distance = calculateLevenshteinDistance(tag, validTag);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = validTag;
            }
        }

        return closestMatch;
    }

    private int calculateLevenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[m][n];
    }
}

