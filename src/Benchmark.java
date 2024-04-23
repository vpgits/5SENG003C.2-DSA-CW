import java.util.Random;

public class Benchmark {

    private static String[] benchmarkFileNameStrings = { "benchmark_series/puzzle_10.txt",
            "benchmark_series/puzzle_20.txt", "benchmark_series/puzzle_40.txt", "benchmark_series/puzzle_80.txt",
            "benchmark_series/puzzle_160.txt", "benchmark_series/puzzle_320.txt", "benchmark_series/puzzle_640.txt",
            "benchmark_series/puzzle_1280.txt", "benchmark_series/puzzle_2560.txt" };

    public static void main(String[] args) {
        Random random = new Random();
        int numberOfTimes = 100;
        long[][] executionTimes = new long[benchmarkFileNameStrings.length][numberOfTimes];
        int[] executionCounts = new int[benchmarkFileNameStrings.length];
        boolean[] isBenchmarkSuccessful = new boolean[benchmarkFileNameStrings.length];
        for (int i = 0; i < benchmarkFileNameStrings.length; i++) {
            isBenchmarkSuccessful[i] = false;
        }
        while (!allTrue(isBenchmarkSuccessful)) {
            int index = random.nextInt(benchmarkFileNameStrings.length);
            if (executionCounts[index] < numberOfTimes && !isBenchmarkSuccessful[index]) {
                executionTimes[index][executionCounts[index]] = runBenchmark(benchmarkFileNameStrings[index]);
                executionCounts[index]++;
                if (executionCounts[index] == numberOfTimes) {
                    isBenchmarkSuccessful[index] = true;
                }
            }
        }

        for (int i = 0; i < benchmarkFileNameStrings.length; i++) {
            System.out.println("Benchmark: " + benchmarkFileNameStrings[i]);
            long sum = 0;
            for (int j = 0; j < numberOfTimes; j++) {
                sum += executionTimes[i][j];
            }
            long average = sum / numberOfTimes;
            System.out.println("Average execution time: " + String.format("%,d", average) + " microseconds");
        }

    }

    private static long runBenchmark(String benchmarkFileNameString) {
        Grid grid = Utils.parser(benchmarkFileNameString);
        Cell start = grid.getStart();
        Cell end = grid.getEnd();
        Cell[][] gridArray = grid.getGrid();
        long startTime = System.nanoTime();
        ModifiedAStar.findPath(gridArray, start, end);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000;
    }

    private static boolean allTrue(boolean[] isBenchmarkSuccessful) {
        for (boolean b : isBenchmarkSuccessful) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
