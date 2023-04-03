import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
 
/**
* @author Aidan Kiser (ark0053@auburn.edu)
* @version 2 April 2023
*/
public class AidansWordGame implements WordSearchGame {  
   private TreeSet<String> lexicon;
   private String[][] board;
   private static final int MAX_NEIGHBORS = 8;
   private int width;
   private int height;
   private boolean[][] visited;
   private ArrayList<Integer> path;
   private String partialWord;
   private SortedSet<String> allWords;
   private ArrayList<Position> path2;
   
   public AidansWordGame() {
      lexicon = null;
      board = new String[4][4];
      board[0][0] = "E"; 
      board[0][1] = "E"; 
      board[0][2] = "C"; 
      board[0][3] = "A"; 
      board[1][0] = "A"; 
      board[1][1] = "L"; 
      board[1][2] = "E"; 
      board[1][3] = "P"; 
      board[2][0] = "H"; 
      board[2][1] = "N"; 
      board[2][2] = "B"; 
      board[2][3] = "O"; 
      board[3][0] = "Q"; 
      board[3][1] = "T"; 
      board[3][2] = "T"; 
      board[3][3] = "Y";  
      width = board.length;
      height = board[0].length;
   }

   public void loadLexicon(String fileName) {
      lexicon = new TreeSet<String>(); 
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         Scanner scan = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (scan.hasNext()) {
            String word = scan.next();
            word = word.toUpperCase();
            lexicon.add(word);
            scan.nextLine();
         }
      }
      catch (java.io.FileNotFoundException e) {
         throw new IllegalArgumentException();
      } 
   }

   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      int n = (int) Math.sqrt(letterArray.length);
      if (n * n != letterArray.length) {
         throw new IllegalArgumentException();
      }
      board = new String[n][n];
      width = n;
      height = n;
      int index = 0;
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            board[i][j] = letterArray[index];
            index++;
         }
      }
   }

   public String getBoard() {
      String boardString = "";
      for (int i = 0; i < height; i++) {
         boardString += "\n";
         for (int j = 0; j < width; j++) {
            boardString += board[i][j] + " ";
         }
      }
      return boardString;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllScorableWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      path2 = new ArrayList<Position>();
      allWords = new TreeSet<String>();
      partialWord = "";
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
            partialWord = board[i][j];  
            if (isValidWord(partialWord) && partialWord.length() >= minimumWordLength) {
               allWords.add(partialWord);
            }
            if (isValidPrefix(partialWord)) {
               Position temp = new Position(i,j);
               path2.add(temp);
               dfs2(i, j, minimumWordLength); 
               path2.remove(temp);
            }
         }
      }
      
      return allWords;
   }

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      int score = 0;
      Iterator<String> itr = words.iterator();
      while (itr.hasNext()) {
         String word = itr.next();
         if (word.length() >= minimumWordLength && isValidWord(word)
             && !isOnBoard(word).isEmpty()) {
            score += (word.length() - minimumWordLength) + 1;
         }
      }
      return score;
   }

   public boolean isValidWord(String wordToCheck) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      wordToCheck = wordToCheck.toUpperCase();
      return lexicon.contains(wordToCheck);
   }

   public boolean isValidPrefix(String prefixToCheck) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      prefixToCheck = prefixToCheck.toUpperCase();
      String word = lexicon.ceiling(prefixToCheck);
      if (word != null) {
         return word.startsWith(prefixToCheck);
      }
      return false;
   }

   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      path2 = new ArrayList<Position>();
      wordToCheck = wordToCheck.toUpperCase();
      partialWord = "";
      path = new ArrayList<Integer>();
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
            if (wordToCheck.equals(board[i][j])) {
               path.add(i * width + j);
               return path;
            }
            if (wordToCheck.startsWith(board[i][j])) {
               Position pos = new Position(i, j);
               path2.add(pos);
               partialWord = board[i][j];
               dfs(i, j, wordToCheck);
               if (!wordToCheck.equals(partialWord)) {
                  path2.remove(pos);
               }
               else {
                  for (Position p: path2) {
                     path.add((p.x * width) + p.y);
                  } 
                  return path;
               }
            }
         }
      }
      return path;
   }
   
   private void dfs(int x, int y, String wordToCheck) {
      Position start = new Position(x, y);
      allUnvisited();
      pathVisited();
      for (Position p: start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (wordToCheck.startsWith(partialWord + board[p.x][p.y])) {
               partialWord += board[p.x][p.y];
               path2.add(p);
               dfs(p.x, p.y, wordToCheck);
               if (wordToCheck.equals(partialWord)) {
                  return;
               } else {
                  path2.remove(p);
                  int endIndex = partialWord.length() - board[p.x][p.y].length();
                  partialWord = partialWord.substring(0, endIndex);
               }
            }
         }
      }
      allUnvisited();
      pathVisited();
   }

   private void dfs2(int x, int y, int min) {
      Position start = new Position(x, y);
      allUnvisited();
      pathVisited();
      for (Position p : start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (isValidPrefix(partialWord + board[p.x][p.y])) {
               partialWord += board[p.x][p.y];
               path2.add(p);
               if (isValidWord(partialWord) && partialWord.length() >= min) {
                  allWords.add(partialWord);
               }
               dfs2(p.x, p.y, min);
               path2.remove(p);
               int endIndex = partialWord.length() - board[p.x][p.y].length();
               partialWord = partialWord.substring(0, endIndex);
            }
         }
      }
      allUnvisited();
      pathVisited();
   }
   
   private void allUnvisited() {
      visited = new boolean[width][height];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }
   
   private void pathVisited() {
      for (int i = 0; i < path2.size(); i ++) {
         visit(path2.get(i));
      }
   }
 
   private class Position {
      int x;
      int y;
   
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
      public Position[] neighbors() {
         Position[] adjValues = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     adjValues[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(adjValues, count);
      }
   }

   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < height);
   }

   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }

}