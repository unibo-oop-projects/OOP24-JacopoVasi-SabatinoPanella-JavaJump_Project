package view;

import javax.swing.*;
import java.awt.*;

public interface AbstractView{

     void updateG();
     void paint(Graphics g);
     void paintComponent(Graphics g);
     void repaint();
     void toggleVisibility();
}
