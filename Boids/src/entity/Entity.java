package entity;

import global.Main;

public class Entity{
    private double x;
    private double y;

    private double vx=0;
    private double vy=0;

    private final int type; //1 boid, 2 obstacle

    public Entity(double x, double y, int type){
        this.x=x;
        this.y=y;
        this.type=type;
    }

    public void behavior(){}

    public int getX_spatial(){
        return (int)(this.x/Main.farthestRadius);
    }
    public int getY_spatial(){
        return (int)(this.y/Main.farthestRadius);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x=x;
    }
    public void setY(double y){
        this.y=y;
    }

    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
    public void setVx(double vx){this.vx=vx;}
    public void setVy(double vy){this.vy=vy;}

    public int getType(){return this.type;}

}
