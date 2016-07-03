package Graph.Models;

import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")

class RandomColor extends Frame
{
   TextField tf = new TextField();
   Button b = new Button("Change");
   int red;
   int green;
   int blue;
   RandomColor()
   {
       add(tf,"Center");
       add(b,"South");
   b.addActionListener(new ActionListener()
   {
   public void actionPerformed(ActionEvent ae)
   {
      red = (int)(Math.random()*256);
      green = (int)(Math.random()*256);
      blue = (int)(Math.random()*256);
      tf.setBackground(new Color(red,green,blue));
   }
   });
   }
 
   public static void main(String s[])
   {
      RandomColor fr = new RandomColor();
      fr.setSize(300,300);
      fr.setVisible(true);
 
   }
}