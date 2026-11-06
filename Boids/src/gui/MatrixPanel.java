package gui;

import global.Main;
import global.Input;
import entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MatrixPanel extends JPanel implements MouseListener, MouseMotionListener{
    public final int xSize=900;
    public final int ySize=551;

    public JLabel boidCount=new JLabel(Integer.toString(Main.entities.size()));
    public JLabel cellCount=new JLabel(Integer.toString(Main.broadSpatial.size()));

    public MatrixPanel(){
        this.setBounds(3, 3, xSize, ySize);
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(new Color(255, 214, 227));
        this.addMouseListener(this); this.addMouseMotionListener(this);
        Main.panel.add(this);

        this.boidCount.setBounds(0, 0, 900, 20);
        this.add(boidCount);

        this.cellCount.setBounds(0, 531, 900, 20);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //render boids
        for(Entity entry: Main.entities){

            if(entry.getType()==1){
                Graphics2D g2=(Graphics2D)g.create();

                int[] px={Main.size, -Main.size, -Main.size};
                int[] py={0, -Main.size/2, Main.size/2};

                g2.translate(entry.getX(), entry.getY());
                g2.rotate(Math.atan2(entry.getVy(), entry.getVx()));
                g2.drawPolygon(px, py, 3);

                g2.dispose();
            }

        }

        //render radii border
        if(Main.isSpatialShown){
            for(int yy=0; yy<this.ySize/Main.farthestRadius+1; yy++){
                for(int xx=0; xx<this.xSize/Main.farthestRadius+1; xx++){

                    if(Main.broadSpatial.containsKey(Main.cellKey(xx, yy))){
                        g.drawRect(xx*Main.farthestRadius, yy*Main.farthestRadius, Main.farthestRadius, Main.farthestRadius);
                    }

                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getButton()==MouseEvent.BUTTON1){
            Input.newBoidByLeftClick(e.getX(), e.getY());
        }
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


    private int travelTime=0;
    @Override
    public void mouseDragged(MouseEvent e){
        travelTime++;
        if(travelTime>5){
            travelTime=0;
        }

        if(true){
            if((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0){
                Input.newBoidByLeftClick(e.getX(),e.getY());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        //System.out.println(e.getX()+", "+e.getY());
    }
}
