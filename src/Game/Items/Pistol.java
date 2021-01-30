package Game.Items;

import Game.Collisons.PistolPickup;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Pistol extends StaticBody {
    private static  Shape pistolShape = new PolygonShape(0.105f,-0.435f, 0.459f,-0.447f, 0.666f,-0.135f, 0.543f,0.381f, 0.123f,0.468f, -0.585f,0.468f, -0.639f,0.102f);
    private static BodyImage pistolImage = new BodyImage("assets/images/pistol.png",1.5f);
    private int ammo;
    private boolean picked=false;

    /**
     * This constructor creates an abstract Pistol object which will allow the character to shoot.
     * It's created because when picked up (collided) the CollisionDetector calls for destroy().
     * @param world The world that the pistol object is created in
     * @param picked Sets picked value to true
     */
    public Pistol(World world, boolean picked){
        super(world);
        this.setName("PickedPistol");
        this.ammo=30;
        this.picked=true;
    }
    /**
     * Constructor created for creating new Pistol when new level is loaded. the ammo stat is going to be read from file.
     */
    /*public Pistol(int ammo){ //add World world in the constructor and in super but when the pistol is going to be created on new level
        super();
        this.ammo=ammo; //ammo read from file
        this.setName("PickedPistol");
        this.picked=true;
    }*/
    /**
     * This constructor creates a visual Pistol object on the map
     * It gets destroyed after the collision (pick up) is detected
     * This constructs pistol object on the map, after picked it gets destroyed and abstract pistol gets created
     * @param world The world that the pistol object is created in
     */
    public Pistol (World world){
        super(world,pistolShape);
        addImage(pistolImage);
        setName("Pistol");
        addCollisionListener(new PistolPickup());
        setAlwaysOutline(true);
    }
    /**
     * Returns the amount of ammo
     * @return the amount of ammo
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * Returns the value of picked
     * @return Value of picked
     */
    public boolean getPicked(){
        return picked;
    }
    /**
     * Set the ammo of a pistol [by finding ammo crate or killing an enemy]
     * @param addAmmo the amount of ammo that will be added to the ammo field
     */
    public void setAmmo(int addAmmo) {
        this.ammo+=addAmmo;
    }

    public void setPistolImage(BodyImage pistolImage) {
        Pistol.pistolImage = pistolImage;
    }

    public void setPistolShape(Shape pistolShape) {
        Pistol.pistolShape = pistolShape;
    }

    /**
     * Shooting method
     */
    public void shoot(World w, DynamicBody db, float charHeight){
        System.out.println("before shoot: "+this.getAmmo());
        if (this.getAmmo()>0){
            Bullet b = new Bullet(w);

            b.setPosition(new Vec2(db.getPosition().x+(charHeight),db.getPosition().y+(charHeight/2))); //MAKE IT DEPENDENT ON MOUSEPOSITION charHeight is for tests
            b.setLinearVelocity(new Vec2(3f,0.5f)); //set angle of the shoot when mouse controller is applied
            this.setAmmo(this.getAmmo()-1);
            System.out.println("after shoot: "+this.getAmmo());
        }
    }

}
