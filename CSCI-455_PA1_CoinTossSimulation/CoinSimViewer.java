// Name: Chong Chen
// USC NetID: 7460787319
// CS 455 PA1
// Fall 2019

import javax.swing.JFrame;
import java.util.Scanner;

/**
 * class CoinSimViewer
 * 
 * Prompts for the numbers of coin-toss trials, and creats a frame 
 * to draw the bar graph with the information of the simulation results.
 *
 * Note: the entered number of trials must be greater than 0.
 */

public class CoinSimViewer
{
   public static void main(String[] args)
   {
      //Prompt for the number of trials;
      System.out.print("Enter number of trials: ");
      Scanner sc = new Scanner(System.in);
      int numTrials = sc.nextInt();
      
      //Verify validness of the input
      while (numTrials <= 0){
         System.out.println("ERROR: Number entered must be greater than 0.");
         System.out.print("Enter number of trials: ");
         numTrials = sc.nextInt();
      }
      
      //Creat the frame
      JFrame frame = new JFrame();
      frame.setSize(800, 500);
      frame.setTitle("CoinSim");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Draw the component
      CoinSimComponent component = new CoinSimComponent();
      component.runSim(numTrials);
      frame.add(component);
      frame.setVisible(true);
   }
}
