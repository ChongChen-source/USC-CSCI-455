// Name: Chong Chen
// USC NetID: 7460787319
// CS 455 PA1
// Fall 2019

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * class CoinSimComponent
 * 
 * A component to draw three labeled bars with different colors,
 * showing the results of the coin-toss simulation.
 * 
 * The placement of each of the bars evenly across the window.
 *
 * The bar graph would get resized to match the window if the window got resized.
 * Note: Resizing the window does not change the results of the simulation
 *
 * The biggest possible height of a bar, with a frequency of 100%, 
 * would nearly fill up the height of the window.
 * 
 */

public class CoinSimComponent extends JComponent{  
   
   private int numTrials;
   private int numTwoHeads;
   private int numTwoTails;
   private int numHeadTails;
   
   /**
      Creat a CoinSimComponent with none data yet.
   */
   public CoinSimComponent(){
      numTrials = 0;
      numTwoHeads = 0;
      numTwoTails = 0;
      numHeadTails = 0;
   }
   
   
   /**
      Run the coin-toss simulator for trials.
      @param trials  number of trials for simulation
   */
   public void runSim(int trials){
      
      //get number of trials and run the simulation
      CoinTossSimulator coinToss = new CoinTossSimulator();
      numTrials = trials;
      coinToss.run(numTrials);
      
      //Get outcomes of the trials
      numTwoHeads = coinToss.getTwoHeads();
      numTwoTails = coinToss.getTwoTails();
      numHeadTails = coinToss.getHeadTails();
      
      //Reset the simulator
      coinToss.reset();
   }
   
   
   /**
      This component draws 3 labeled bars with different colors to show the outcomes of the coin-toss simulation.
      @param g  the Graphics class object to store the graphics state (current color, font and so on)
   */
   public void paintComponent(Graphics g){  
      
      //Calculate the percentages
      final int HUNDRED_PER_CENT = 100;
      int perTwoHeads = (int)Math.round((numTwoHeads / (double)numTrials) * HUNDRED_PER_CENT);
      int perTwoTails = (int)Math.round((numTwoTails / (double)numTrials) * HUNDRED_PER_CENT);
      int perHeadTails = (int)Math.round((numHeadTails / (double)numTrials) * HUNDRED_PER_CENT);
      
      //Set three label strings
      String labelTwoHeads = "Two Head: " + numTwoHeads + "(" + perTwoHeads + "%)";
      String labelTwoTails = "Two Tails: " + numTwoTails + "(" + perTwoTails + "%)";
      String labelHeadTails = "A Head and a Tail: " + numHeadTails + "(" + perHeadTails + "%)";
      
      //Get the height of the labels
      Graphics2D g2 = (Graphics2D) g;
      Font font = g2.getFont();
      FontRenderContext context = g2.getFontRenderContext();
      Rectangle2D labelBounds = font.getStringBounds(labelTwoHeads, context);
      int label_Height = (int) labelBounds.getHeight();
      
      //Set the layout of the window
      
      //Fixed values of the graph
      final int BAR_WIDTH = 40;
      final int VERTICAL_BLANK = 30;
      
      //Changable values depending on the window size
      int xIntervals = getWidth()/4;
      int graph_Bottom = getHeight() - VERTICAL_BLANK;
      double scale = (double)(getHeight() - label_Height - 2 * VERTICAL_BLANK) / (double)numTrials; 
      
      //Set the three labeled bars
      final Color TWO_HEAD_COLOR = Color.RED;                 
      final Color HEAD_TAIL_COLOR = Color.GREEN;                
      final Color TWO_TAIL_COLOR = Color.BLUE;                
      
      //Set the three colored labeled bars
      Bar barTwoHeads = new Bar(graph_Bottom, xIntervals - BAR_WIDTH / 2, BAR_WIDTH, numTwoHeads, scale, TWO_HEAD_COLOR, labelTwoHeads);
      Bar barHeadTails = new Bar(graph_Bottom, 2 * xIntervals - BAR_WIDTH / 2, BAR_WIDTH, numHeadTails, scale, HEAD_TAIL_COLOR, labelHeadTails);
      Bar barTwoTails = new Bar(graph_Bottom, 3 * xIntervals - BAR_WIDTH / 2, BAR_WIDTH, numTwoTails, scale, TWO_TAIL_COLOR, labelTwoTails);
      
      //Draw the three labeled bars
      g2.setColor(TWO_HEAD_COLOR);
      barTwoHeads.draw(g2);
      g2.setColor(HEAD_TAIL_COLOR);
      barHeadTails.draw(g2);
      g2.setColor(TWO_TAIL_COLOR);
      barTwoTails.draw(g2); 
      
   }
}
