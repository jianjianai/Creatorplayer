package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 选择点的方法
 * 用于确定玩家选择地点
 * */
public class xuanze implements Listener {
    @EventHandler
    public void wanjiadianji(PlayerInteractEvent a){
        if(a.getItem()==null){//判断玩家手中是否有物品
            return;
        }else if(!a.getItem().getType().equals(main.peizhi.xuanquwupin)){//判断玩家手中物品是否是选取物品
            return;
        }else if(a.getClickedBlock()==null){//判断点击方块是否是空
            return;
        }
        System.out.println(a.getClickedBlock().getLocation());
    }
}
