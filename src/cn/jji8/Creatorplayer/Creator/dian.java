package cn.jji8.Creatorplayer.Creator;


import org.bukkit.*;
import org.bukkit.entity.Player;

/**
 * 点
 * 选择点位置和玩家
 * */
public class dian {
    public Player wanjia;//玩家名字
    Location weizi1 = null; //位子1
    Location weizi2 = null; //位子2
    dian(Player w){
        wanjia = w;
    }

    public void xianshi(){
        Thread T = new Thread(){
            @Override
            public void run() {
                if(weizi1==null|weizi2==null){
                    return;
                }
                if(!weizi1.getWorld().equals(weizi2.getWorld())){
                    return;
                }
                Player Player = wanjia;
                World World = weizi1.getWorld();

                double x1 = weizi1.getBlockX();
                double y1 = weizi1.getBlockY();
                double z1 = weizi1.getBlockZ();

                double x2 = weizi2.getBlockX();
                double y2 = weizi2.getBlockY();
                double z2 = weizi2.getBlockZ();
                if(x1>x2){
                    x1++;
                }else {
                    x2++;
                }
                if(y1>y2){
                    y1++;
                }else {
                    y2++;
                }
                if(z1>z2){
                    z1++;
                }else {
                    z2++;
                }
                xianshix(Player,World,x1,y1,z1,x2);
                xianshiy(Player,World,x1,y1,z1,y2);
                xianshiz(Player,World,x1,y1,z1,z2);

                xianshix(Player,World,x2,y2,z2,x1);
                xianshiy(Player,World,x2,y2,z2,y1);
                xianshiz(Player,World,x2,y2,z2,z1);

                xianshix(Player,World,x2,y1,z2,x1);
                xianshiz(Player,World,x2,y1,z2,z1);

                xianshix(Player,World,x1,y2,z1,x2);
                xianshiz(Player,World,x1,y2,z1,z2);

                xianshiy(Player,World,x2,y1,z1,y2);
                xianshiy(Player,World,x1,y1,z2,y2);
            }
        };
        T.start();
    }
    public void xianshix(Player player, World world, double x1, double y, double z, double x2){
        if(x1>x2){
            double a = x1;
            x1 = x2;
            x2 = a;
        }
        for(double i = x1;i<=x2;i++){
            Location a = new Location(world,i,y,z);
            player.spawnParticle(Particle.FLAME,a,0);
        }
    }
    public void xianshiy(Player player, World world, double x, double y1, double z, double y2){
        if(y1>y2){
            double a = y1;
            y1 = y2;
            y2 = a;
        }
        for(double i = y1;i<=y2;i++){
            Location a = new Location(world,x,i,z);
            player.spawnParticle(Particle.FLAME,a,0);
        }
    }
    public void xianshiz(Player player, World world, double x, double y, double z1, double z2){
        if(z1>z2){
            double a = z1;
            z1 = z2;
            z2 = a;
        }
        for(double i = z1;i<=z2;i++){
            Location a = new Location(world,x,y,i);
            player.spawnParticle(Particle.FLAME,a,0);
        }
    }



//set get方法
    public void setWeizi1(Location weizi1) {
        this.weizi1 = weizi1;
    }
    public void setWeizi2(Location weizi2) {
        this.weizi2 = weizi2;
    }
    public Location getWeizi1() {
        return weizi1;
    }
    public Location getWeizi2() {
        return weizi2;
    }
    public Player getwanjia() {
        return wanjia;
    }
}
