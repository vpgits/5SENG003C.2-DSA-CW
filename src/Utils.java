import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private Utils() {
    }

    public static Grid parser(String filePathString) {
        Grid grid = null;
        int width, height;
        List<String> lines = new ArrayList<>();
        Path filePath = Paths.get(filePathString);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath.toFile()))) {

            String line;
            for (height = 0; (line = bufferedReader.readLine()) != null; height++) {
                if (line.isEmpty())
                    break;
                lines.add(line);
            }
            width = lines.get(0).length();
            grid = new Grid(height, width);

            for (int j = 0; j < lines.size(); j++) {
                String l = lines.get(j);
                if (l.length() != width) {
                    throw new Exception("Invalid file. Line " + " has invalid dimensions");
                }
                for (int i = 0; i < width; i++) {
                    char c = l.charAt(i);
                    grid.setCell(j, i, new Cell(j, i, c == '0'));
                    if (c == 'S') {
                        grid.setStart(grid.getCell(j, i));

                    } else if (c == 'F') {
                        grid.setEnd(grid.getCell(j, i));

                    }

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grid;
    }
}
