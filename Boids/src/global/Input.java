package global;

import entity.Boid;

public class Input{

    public static void newBoidByLeftClick(int x, int y){
        synchronized(Main.entitiesBuffer){
                Main.entitiesBuffer.add(new Boid(x,y));
        }
    }
}
