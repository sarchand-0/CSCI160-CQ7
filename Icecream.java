import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import javafx.util.Pair;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     * 1. INTEGER m
     * 2. INTEGER_ARRAY arr
     */

    public static int icecreamParlor(int m, List<Integer> arr) {

        int[][][] dp = new int[arr.size() + 1][4][m + 1];

        dp[0][0][0] = 1;

        for (int i = 1; i <= arr.size(); i++) {
            int cost = arr.get(i - 1);
            for (int j = 0; j <= 3; j++) {
                for (int k = 0; k <= m; k++) {

                    dp[i][j][k] = dp[i - 1][j][k];

                    if (j > 0 && k >= cost) {
                        dp[i][j][k] += dp[i - 1][j - 1][k - cost];
                    }
                }
            }
        }

        return dp[arr.size()][3][m];

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Integer result = Result.icecreamParlor(m, arr);
                bufferedWriter.write(result.toString() + "\n");

                // bufferedWriter.write(
                // result.stream()
                // .map(Object::toString)
                // .collect(joining(" "))
                // + "\n"
                // );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}