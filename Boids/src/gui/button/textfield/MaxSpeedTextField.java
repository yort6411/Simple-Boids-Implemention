package gui.button.textfield;

import global.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MaxSpeedTextField extends JPanel implements MouseListener{
    Color color=new Color(204-50, 157-50, 172-50);
    public JTextField speedText=new JTextField(10);

    public int buffered=3;
    public JLabel speedValue=new JLabel(Integer.toString(buffered));

    public MaxSpeedTextField(){
        this.setBounds(910, 335+130, 63, 15);
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(this);
        this.setLayout(null);
        Main.panel.add(this);

        JLabel textDisplay=new JLabel("Max Speed");
        textDisplay.setBounds(910, 305+130, 63, 15);
        Main.panel.add(textDisplay);

        this.speedValue.setBounds(910, 320+130, 63, 15);
        Main.panel.add(this.speedValue);

        speedText.setBounds(0, 0, 63, 15);
        this.add(speedText);

        speedText.addActionListener(e -> {
            try{
                this.buffered=Integer.parseInt(speedText.getText());
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
