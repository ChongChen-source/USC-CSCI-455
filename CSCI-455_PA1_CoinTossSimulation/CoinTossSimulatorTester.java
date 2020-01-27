// Name: Chong Chen
// USC NetID: 7460787319
// CS 455 PA1
// Fall 2019

/**
 * class CoinTossSimulatorTester
 *
 * This class test the CoinTossSimulator class.
 * You can do multiple calls to the run method of class CoinTossSimulator to add more trials to the ones already made.
 * Only if you call the method reset does this tester reset the simulation to its beginning state.
 *
 */

public class CoinTossSimulatorTester{
   public static void main(String[] args){
      CoinTossSimulator coinToss = new CoinTossSimulator();
      
      System.out.println("After constructor:");
      
      System.out.println("Number of trials [exp:0]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Run for 1 trial
      coinToss.run(1);
      System.out.println("After run(1):");
      
      System.out.println("Number of trials [exp:1]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Run for 10 trials
      coinToss.run(10);
      System.out.println("After run(10):");
      
      System.out.println("Number of trials [exp:11]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Run for 40 trials
      coinToss.run(40);
      System.out.println("After run(40):");
      
      System.out.println("Number of trials [exp:51]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Reset the trials to 0
      coinToss.reset();
      System.out.println("After reset:");
      
      System.out.println("Number of trials [exp:0]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Run for 79 trials
      coinToss.run(79);
      System.out.println("After run(79):");
      
      System.out.println("Number of trials [exp:79]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
      
      //Run for 1000 trials
      coinToss.run(1000);
      System.out.println("After run(1000):");
      
      System.out.println("Number of trials [exp:1079]: " + coinToss.getNumTrials());
      System.out.println("Two-head tosses: " + coinToss.getTwoHeads());
      System.out.println("Two-tail tosses: " + coinToss.getTwoTails());
      System.out.println("One-head one-tail tosses: " + coinToss.getHeadTails());

      System.out.print("Tosses add up correctly? ");
      if (coinToss.getNumTrials() == coinToss.getTwoHeads() + coinToss.getTwoTails() + coinToss.getHeadTails()){
         System.out.println(true);
      }
      else{
         System.out.println(false);
      }
      System.out.println();
   }
}