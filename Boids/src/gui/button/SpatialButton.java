package gui.button;

import global.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpatialButton extends JPanel implements MouseListener{
    Color color=new Color(255, 70, 70);

    public SpatialButton(){
        this.setBounds(910, 400, 63, 15);
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(this);
        this.setLayout(null);
        Main.panel.add(this);

        JLabel textDisplay=new JLabel("Radii border");
        textDisplay.setBounds(2, 1, 60, 12);
        textDisplay.setFont(new Font("Arial", Font.PLAIN, 11));
        textDisplay.setForeground(Color.WHITE);
        this.add(textDisplay);
    }

    @Override
    public void mouseClicked(MouseEvent e){
        Main.isSpatialShown=!Main.isSpatialShown;
        if(Main.isSpatialShown){
            Main.matrix.add(Main.matrix.cellCount);
        }
        else{
            Main.matrix.remove(Main.matrix.cellCount);
        }

        if(this.color.equals(new Color(255, 70, 70))){
            this.color=new Color(90, 90, 255);
        }
        else{
            this.color=new Color(255, 70, 70);
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
