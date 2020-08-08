package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
/**
 * 负责gui的控制
 * */
public class gui {
    dian dian;
    Inventory xiangzi;
    gui(String name){
        dian = new dian(name);
        xiangzi = org.bukkit.Bukkit.createInventory(null,6*9, main.peizhi.箱子标题.replaceAll("%玩家%",name));
    }
    public void dakai(){
        org.bukkit.Bukkit.getPlayer(dian.getWanjiamingzi()).openInventory(xiangzi);
    }


//set get 方法
    public cn.jji8.Creatorplayer.Creator.dian getDian() {
        return dian;
    }
}
