package global;

import gui.*;
import entity.Entity;
import gui.button.*;
import gui.button.textfield.AlignmentRadiusTextField;
import gui.button.textfield.CohesionRadiusTextField;
import gui.button.textfield.MaxSpeedTextField;
import gui.button.textfield.SeparationRadiusTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    public static volatile boolean paused=false;
    public static volatile boolean isSpatialShown=false;

    //boid specification
    private static volatile int scaleSpecs= 1;
    public static volatile int size=scaleSpecs* 10;
    public static volatile double maxSteer;

    public static volatile int maxSpeed;
    public static volatile int separationRadius=0;
    public static volatile int cohesionRadius=0;
    public static volatile int alignmentRadius=0;
    public static int farthestRadius;

    public static volatile boolean separationEnabled=true;
    public static volatile boolean cohesionEnabled=true;
    public static volatile boolean alignmentEnabled=true;

    //entity storage
    public static ArrayList<Entity> entities=new ArrayList<>();

    //boids temporarily stored in a patial hash for boid detection optimization
    public static HashMap<String, ArrayList<Entity>> broadSpatial=new HashMap<>();

    //for buffering newly created boids
    public static ArrayList<Entity> entitiesBuffer=new ArrayList<>();

    //GUI initialization
    static JFrame frame=new JFrame();
    //the actual super container
    public static JPanel panel=new JPanel();
    //matrix panel
    public static MatrixPanel matrix=new MatrixPanel();
    //button panels
    public static PauseButton pauseButton=new PauseButton();
    public static SeparationButton separationButton=new SeparationButton();
    public static CohesionButton cohesionButton=new CohesionButton();
    public static AlignmentButton alignmentButton=new AlignmentButton();
    public static SeparationRadiusTextField separationRadiusTextField=new SeparationRadiusTextField();
    public static CohesionRadiusTextField cohesionRadiusTextField=new CohesionRadiusTextField();
    public static AlignmentRadiusTextField alignmentRadiusTextField=new AlignmentRadiusTextField();
    public static SpatialButton spatialButton=new SpatialButton();
    public static MaxSpeedTextField maxSpeedTextField=new MaxSpeedTextField();









    public static void main(String[] args)throws InterruptedException{
        frame.setVisible(true);

        while(true){

            long start1=System.nanoTime();

            addCreatedEntities();
            updateGUI();

            delayMainThread(start1);

            while(!paused){
                long start2=System.nanoTime();

                globalUpdate();
                addCreatedEntities();
                updateGUI();
                entitiesBehavior();

                delayMainThread(start2);
            }

        }
    }

    private static void globalUpdate(){
        if(separationRadiusTextField.buffered!=separationRadius){
            separationRadius=separationRadiusTextField.buffered*scaleSpecs;
        }
        if(cohesionRadiusTextField.buffered!=cohesionRadius){
            cohesionRadius=cohesionRadiusTextField.buffered*scaleSpecs;
        }
        if(alignmentRadiusTextField.buffered!=alignmentRadius){
            alignmentRadius=alignmentRadiusTextField.buffered*scaleSpecs;
        }

        //if the biggest radius changes
        int previousFarthestRadius=farthestRadius;
        setFarthestRadius();
        if(previousFarthestRadius!=farthestRadius){
            broadSpatial.clear();
            for(Entity entry: entities){
                addToSpatial(entry);
            }
        }

        if(maxSpeedTextField.buffered!=maxSpeed){
            maxSpeed=maxSpeedTextField.buffered;
            maxSteer=0.016*maxSpeed;
        }

    }

    private static void delayMainThread(long start)throws InterruptedException{
        long remaining=16_666_667-(System.nanoTime()-start);
        if(remaining>0){
            Thread.sleep(remaining/1000000);
        }
    }

    private static void addCreatedEntities(){
        synchronized(Main.entitiesBuffer){
            for(Entity entry: Main.entitiesBuffer){
                entities.add(entry);
                Main.addToSpatial(entry);
            }
        }

        entitiesBuffer.clear();
    }

    private static void updateGUI(){
        //boid count display
        if(!(matrix.boidCount.getText().equals(Integer.toString(entities.size())))){
            matrix.boidCount.setText(Integer.toString(entities.size()));
        }

        //cell count display
        if(!(matrix.cellCount.getText().equals(Integer.toString(broadSpatial.size())))){
            matrix.cellCount.setText(Integer.toString(broadSpatial.size()));
        }

        //radii values display
        if(!(separationRadiusTextField.radiusValue.getText().equals(Integer.toString(separationRadius)))){
            separationRadiusTextField.radiusValue.setText(Integer.toString(separationRadius));
        }
        if(!cohesionRadiusTextField.radiusValue.getText().equals(Integer.toString(cohesionRadius))){
            cohesionRadiusTextField.radiusValue.setText(Integer.toString(cohesionRadius));
        }
        if(!alignmentRadiusTextField.radiusValue.getText().equals(Integer.toString(alignmentRadius))){
            alignmentRadiusTextField.radiusValue.setText(Integer.toString(alignmentRadius));
        }
        if(!(maxSpeedTextField.speedValue.getText().equals(Integer.toString(maxSpeed)))){
            maxSpeedTextField.speedValue.setText(Integer.toString(maxSpeed));
        }

        frame.revalidate(); frame.repaint();

    }

    private static void entitiesBehavior(){
        for(Entity entry: entities) entry.behavior();
    }











    static{
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(110, 50, 69));

        panel.setBounds(2, 2, 980, 557);
        panel.setLayout(null);
        panel.setOpaque(true);
        panel.setBackground(new Color(204, 157, 172));
        Main.frame.add(panel);
    }

    public static String cellKey(int x, int y){
        return x+", "+y;
    }

    public static void addToSpatial(Entity entity){
        int x_Rad=(entity.getX_spatial());
        int y_Rad=(entity.getY_spatial());
        if(!broadSpatial.containsKey(cellKey(x_Rad, y_Rad))){
            broadSpatial.put(
                    cellKey(x_Rad, y_Rad),
                    new ArrayList<>()
            );
        }
        broadSpatial.get(cellKey(x_Rad, y_Rad)).add(entity);
    }

    private static void setFarthestRadius(){
        int[] radi={separationRadius, cohesionRadius, alignmentRadius};
        int max=0;
        for(int entry: radi){
            max=entry>max?entry:max;
        }
        farthestRadius=max;
    }
}
