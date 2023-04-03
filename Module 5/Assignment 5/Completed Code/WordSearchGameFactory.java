
/**
 * Provides a factory method for creating word search games. 
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    *
    * @author Aidan Kiser (ark0053@auburn.edu)
    * @version 2 April 2023
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here.
      return new AidansWordGame();
   }

}
