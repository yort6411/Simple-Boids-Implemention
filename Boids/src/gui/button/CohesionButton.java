package gui.button;

import global.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CohesionButton extends JPanel implements MouseListener{
    private Color color=new Color(90, 90, 255);

    public CohesionButton(){
        this.setBounds(910, 70, 63, 15);
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(this);
        this.setLayout(null);
        Main.panel.add(this);

        JLabel textDisplay=new JLabel("Cohesion");
        textDisplay.setBounds(6, 1, 57, 12);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 11));
        textDisplay.setForeground(Color.WHITE);
        this.add(textDisplay);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        Main.cohesionEnabled=!Main.cohesionEnabled;

        if(this.color.equals(new Color(90, 90, 255))){
            this.color=new Color(255, 70, 70);
        }
        else{
            this.color=new Color(90, 90, 255);
        }

        this.setBackground(color);
    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }
}
