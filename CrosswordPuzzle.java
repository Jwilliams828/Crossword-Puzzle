import java.util.*;

public class CrosswordPuzzle{
    static final int SIZE = 10;
    static char[][] grid = new char[SIZE][SIZE];
    static List<String> words = Arrays.asList("JAVA", "CODE", "PUZZLE", "GAME", "GRID");
    static boolean[][] used = new boolean[SIZE][SIZE];

    public static void main(String[] args) {
        // Fill grid with blanks
        for (char[] row : grid)
            Arrays.fill(row, '.');

        System.out.println("Generating Crossword Puzzle...\n");

        if (placeWords(0)) {
            printGrid();
        } else {
            System.out.println("Could not generate a puzzle with given words.");
        }
    }

    // Backtracking to place words
    static boolean placeWords(int index) {
        if (index == words.size()) return true;

        String word = words.get(index);
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (canPlaceHorizontally(word, row, col)) {
                    boolean[] temp = placeHorizontally(word, row, col);
                    if (placeWords(index + 1)) return true;
                    removeHorizontally(word, row, col, temp);
                }
                if (canPlaceVertically(word, row, col)) {
                    boolean[] temp = placeVertically(word, row, col);
                    if (placeWords(index + 1)) return true;
                    removeVertically(word, row, col, temp);
                 }
            }
        }
        return false;
    }

    // Word placement check and placing
    static boolean canPlaceHorizontally(String word, int row, int col) {
        if (col + word.length() > SIZE) return false;
        for (int i = 0; i < word.length(); i++) {
            char current = grid[row][col + i];
            if (current != '.' && current != word.charAt(i)) return false;
        }
        return true;
    }

    static boolean canPlaceVertically(String word, int row, int col) {
        if (row + word.length() > SIZE) return false;
        for (int i = 0; i < word.length(); i++) {
            char current = grid[row + i][col];
            if (current != '.' && current != word.charAt(i)) return false;
        }
        return true;
    }

    static boolean[] placeHorizontally(String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (grid[row][col + i] == '.') {
                grid[row][col + i] = word.charAt(i);
                placed[i] = true;
            }
        }
        return placed;
    }

    static boolean[] placeVertically(String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (grid[row + i][col] == '.') {
                grid[row + i][col] = word.charAt(i);
                placed[i] = true;
            }
        }
        return placed;
    }

    static void removeHorizontally(String word, int row, int col, boolean[] placed) {
        for (int i = 0; i < word.length(); i++) {
            if (placed[i]) grid[row][col + i] = '.';
        }
    }

    static void removeVertically(String word, int row, int col, boolean[] placed) {
        for (int i = 0; i < word.length(); i++) {
            if (placed[i]) grid[row + i][col] = '.';
        }
    }

    static void printGrid() {
        for (char[] row : grid) {
            for (char ch : row) System.out.print(ch + " ");
            System.out.println();
        }
    }
}