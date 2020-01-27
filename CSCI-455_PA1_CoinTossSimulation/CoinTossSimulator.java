// Name: Chong Chen
// USC NetID: 7460787319
// CS 455 PA1
// Fall 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {
   
   private Random generator; 
   private int trials;
   private int numTwoHeads;
   private int numTwoTails;
   private int numHeadTails;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
      this.generator = new Random();
      this.trials = 0;
      this.numTwoHeads = 0;
      this.numTwoTails = 0;
      this.numHeadTails = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      
      //No need to verify the validness of the input data here
      /*
         System.out.print("Enter number of trials: ");
         System.out.println(numTrials);
         if (numTrials >= 1){
            this.trials = this.trials + numTrials;
         }
         else{
            System.out.println("ERROR: Number entered must be great than 0.");
            System.out.println("The programm would not execute this time.");
         }
      */
      
      this.trials = this.trials + numTrials;
      
      //Simulate the coin-toss process for trials times
      final int HEAD_TURNS_UP = 0;
      final int TAIL_TURNS_UP = 1;
      final int FACES_PER_COIN = 2;
      int state_Coin1;
      int state_Coin2;
      
      for (int n = 0; n < numTrials; n ++){
         state_Coin1 = generator.nextInt(FACES_PER_COIN);
         state_Coin2 = generator.nextInt(FACES_PER_COIN);
         
         if ((state_Coin1 == HEAD_TURNS_UP) && (state_Coin2 == HEAD_TURNS_UP)){
            this.numTwoHeads ++;
         }
         else if ((state_Coin1 == TAIL_TURNS_UP) && (state_Coin2 == TAIL_TURNS_UP)){
            this.numTwoTails ++;
         }
         else {
            this.numHeadTails ++;
         }
      }
   }


   /**
      Get number of trials performed since last reset.
      @return the number of trials
   */
   public int getNumTrials() {
      return trials; 
   }


   /**
      Get number of trials that came up two heads since last reset.
      @return the number of outcomes with two heads
   */
   public int getTwoHeads() {
      return numTwoHeads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
     @return the number of outcomes with two tails
   */  
   public int getTwoTails() {
      return numTwoTails;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
     @return the number of outcomes with one head and one tail
   */
   public int getHeadTails() {
      return numHeadTails;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      this.generator = new Random();
      this.trials = 0;
      this.numTwoHeads = 0;
      this.numTwoTails = 0;
      this.numHeadTails = 0;
   }
}
