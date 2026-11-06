package entity;

import java.util.ArrayList;
import global.Main;

public class Boid extends Entity{
    public Boid(double x, double y){
        super(x, y, 1);
    }

    @Override
    public void behavior(){
        double[] separationForce=new double[2];
        int seRaSquared=Main.separationRadius*Main.separationRadius;

        double[] cohesionForce=new double[2];
        int coRaSquared=Main.cohesionRadius*Main.cohesionRadius;
        double sumx1=0; double sumy1=0;
        int total1=0;

        double[] alignmentForce=new double[2];
        int alRaSquared=Main.alignmentRadius*Main.alignmentRadius;
        double sumx2=0; double sumy2=0;
        int total2=0;


        ArrayList<Entity> nearbyEntities=getNearbyEntities();
        for(Entity entry: nearbyEntities){
            if(entry.getType()==2){
                continue;
            }

            double distX=entry.getX()-this.getX();
            double distY=entry.getY()-this.getY();
            double squaredDist=distX*distX+distY*distY;

            //separation
            if(squaredDist<=seRaSquared){
                double sVx=getX()-entry.getX();
                double sVy=getY()-entry.getY();

                double sepDisSquared=sVx*sVx+sVy*sVy;
                if(sepDisSquared>0){
                    separationForce[0]+=sVx/sepDisSquared;
                    separationForce[1]+=sVy/sepDisSquared;
                }
            }

            //cohesion
            if(squaredDist<=coRaSquared){
                sumx1+=entry.getX();
                sumy1+=entry.getY();
                total1++;
            }

            //alignment
            if(squaredDist<=alRaSquared){
                sumx2+=entry.getVx();
                sumy2+=entry.getVy();
                total2++;
            }
        }

        //cohesion alignment post for:each
        if(total1>0){
            cohesionForce[0]=(sumx1/total1)-this.getX();
            cohesionForce[1]=(sumy1/total1)-this.getY();
        }
        if(total2>0){
            alignmentForce[0]=sumx2/total2;
            alignmentForce[1]=sumy2/total2;
        }

        //converting raw force to steer force
        separationForce=steeringForceLogic(separationForce);
        cohesionForce=steeringForceLogic(cohesionForce);
        alignmentForce=steeringForceLogic(alignmentForce);

        //combining the three forces together
        double[] combinedForce=new double[2];
        if(Main.separationEnabled){
            combinedForce[0]+=separationForce[0]*2;
            combinedForce[1]+=separationForce[1]*2;
        }
        if(Main.cohesionEnabled){
            combinedForce[0]+=cohesionForce[0];
            combinedForce[1]+=cohesionForce[1];
        }
        if(Main.alignmentEnabled){
            combinedForce[0]+=alignmentForce[0];
            combinedForce[1]+=alignmentForce[1];
        }

        this.setVx(getVx()+combinedForce[0]);
        this.setVy(getVy()+combinedForce[1]);

        setNewPosition();
    }

    private void setNewPosition(){
        //speed limit
        double speed=Math.sqrt(getVx()*getVx()+getVy()*getVy());
        if(speed>Main.maxSpeed){
            setVx((getVx()/speed)*Main.maxSpeed);
            setVy((getVy()/speed)*Main.maxSpeed);
        }

        double futureX=this.getX()+this.getVx();
        double futureY=this.getY()+this.getVy();

        //change velocity if out of bounds
        if(futureX<0){
            this.setVx(0-futureX);
        }
        if(futureX>Main.matrix.xSize){
            this.setVx(Main.matrix.xSize-futureX);
        }
        if(futureY<0){
            this.setVy(0-futureY);
        }
        if(futureY>Main.matrix.ySize){
            this.setVy(Main.matrix.ySize-futureY);
        }

        //the actual set position
        futureX=this.getX()+this.getVx();
        futureY=this.getY()+this.getVy();
        if(
                !((int)(futureX/Main.farthestRadius)==this.getX_spatial()
                &&(int)(futureY/Main.farthestRadius)==this.getY_spatial())
        ){
            Main.broadSpatial.get(Main.cellKey(this.getX_spatial(), this.getY_spatial())).remove(this);
            this.setX(getX()+getVx());
            this.setY(getY()+getVy());
            Main.addToSpatial(this);
        }
        else{
            this.setX(getX()+getVx());
            this.setY(getY()+getVy());
        }
    }

    private ArrayList<Entity> getNearbyEntities(){
        ArrayList<Entity> nEntities=new ArrayList<>();

        for(int i=getY_spatial()-1; i<getY_spatial()+2; i++){
            for(int j=getX_spatial()-1; j<getX_spatial()+2; j++){

                if(Main.broadSpatial.containsKey(Main.cellKey(j, i))){

                    for(Entity entry: Main.broadSpatial.get(Main.cellKey(j, i))){
                        if(!this.equals(entry)){
                            nEntities.add(entry);
                        }
                    }

                }

            }
        }

        return nEntities;
    }

    private double[] steeringForceLogic(double[] desiredVector){
        double[] steeringForce=new double[2];
        double vx=desiredVector[0];
        double vy=desiredVector[1];

        //normalize desired velocity, so that the boid's speed is predictable
        double magnitude=Math.sqrt(vx*vx+vy*vy);
        if(magnitude==0){
            return steeringForce;
        }
        if(magnitude>0){
            vx=(vx/magnitude)*Main.maxSpeed;
            vy=(vy/magnitude)*Main.maxSpeed;
        }

        //how to steer from current to desired
        vx-=this.getVx();
        vy-=this.getVy();

        //limit how fast it steers
        double steeringStrength=Math.sqrt(vx*vx+vy*vy);
        if(steeringStrength>Main.maxSteer){
            steeringForce[0]=(vx/steeringStrength)*Main.maxSteer;
            steeringForce[1]=(vy/steeringStrength)*Main.maxSteer;
        }
        else{
            steeringForce[0]=vx;
            steeringForce[1]=vy;
        }

        return steeringForce;
    }
}
