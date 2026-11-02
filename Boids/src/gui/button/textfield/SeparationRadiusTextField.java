package gui.button.textfield;

import global.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SeparationRadiusTextField extends JPanel implements MouseListener{
    Color color=new Color(204-50, 157-50, 172-50);
    public JTextField separationText=new JTextField(10);

    public int buffered=15;
    public JLabel radiusValue=new JLabel(Integer.toString(buffered));

    public SeparationRadiusTextField(){
        this.setBounds(910, 155, 63, 15);
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(this);
        this.setLayout(null);
        Main.panel.add(this);

        JLabel textDisplay=new JLabel("SEP radius");
        textDisplay.setBounds(910, 125, 63, 15);
        Main.panel.add(textDisplay);

        this.radiusValue.setBounds(910, 140, 63, 15);
        Main.panel.add(this.radiusValue);

        separationText.setBounds(0, 0, 63, 15);
        this.add(separationText);

        separationText.addActionListener(e -> {
            try{
                this.buffered=Integer.parseInt(separationText.getText());
            }
            catch(NumberFormatException er){

            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e){

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
