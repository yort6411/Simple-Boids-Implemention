package gui.button.textfield;

import global.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CohesionRadiusTextField extends JPanel implements MouseListener{
    Color color=new Color(204-50, 157-50, 172-50);
    public JTextField cohesionText=new JTextField(10);

    public int buffered=50;
    public JLabel radiusValue=new JLabel(Integer.toString(buffered));

    public CohesionRadiusTextField(){
        this.setBounds(910, 215, 63, 15);
        this.setOpaque(true);
        this.setBackground(color);
        this.addMouseListener(this);
        this.setLayout(null);
        Main.panel.add(this);

        JLabel textDisplay=new JLabel("COH radius");
        textDisplay.setBounds(910, 185, 65, 15);
        Main.panel.add(textDisplay);

        this.radiusValue.setBounds(910, 200, 63, 15);
        Main.panel.add(this.radiusValue);

        cohesionText.setBounds(0, 0, 63, 15);
        this.add(cohesionText);

        cohesionText.addActionListener(e -> {
            try{
                this.buffered=Integer.parseInt(cohesionText.getText());
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
