package cn.jji8.Creatorplayer.Creator;


import org.bukkit.Location;

/**
 * 点
 * 选择点位置和玩家
 * */
public class dian {
    String wanjiamingzi;//玩家名字
    Location weizi1; //位子1
    Location weizi2; //位子2
    dian(String name){
        wanjiamingzi = name;
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
    public String getWanjiamingzi() {
        return wanjiamingzi;
    }
}
