package game.bullet;

import asciiPanel.AsciiPanel;
import game.Nothing;
import game.Thing;
import game.World;
import game.creature.Creature;
import game.creature.Player;
import game.weapon.Weapon;

public class Lightning extends Bullet{
    public static final int flyInterval=500;
    public Lightning(World world, int damage,int range,int direction,int x,int y){
        super(world, damage, range, direction, x, y,flyInterval);
        
    }
    @Override
    public void run() {
        //out of map? game finished?
        if(!isVisible()){ 
            cancel();
            return;
        }
        //HIT Something?
        Thing thing=world.get(x, y);
        if( !(thing instanceof Nothing)){
            if(thing instanceof Player)thing.getAttacked(damage);
        } 
        //move
        switch(direction){
            case Creature.up:
                y-=1;
                break;
            case Creature.down:
                y+=1;
                break;
            case Creature.left:
                x-=1;
                break;
            case Creature.right:
                x+=1;
                break;
        }
        range--;
        //out of map?
        if(!isVisible()){ 
            cancel();
            return;
        }
        //expired?
        if(range<=0){
            x=-1;
            y=-1;
            cancel();
            return;
        }
    }
}
