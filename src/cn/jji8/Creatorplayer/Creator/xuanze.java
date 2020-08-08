package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

/**
 * 选择
 * 选择点的方法
 * 用于确定玩家选择地点
 * */
public class xuanze implements Listener {
    xuanqukongzhiqi xuanqukongzhiqi = new xuanqukongzhiqi();
    @EventHandler
    public void wanjiadianji(PlayerInteractEvent a){//玩家点击时触发
        if(a.getItem()==null){//判断玩家手中是否有物品
            return;
        }else if(!a.getItem().getType().equals(main.peizhi.xuanquwupin)){//判断玩家手中物品是否是选取物品
            return;
        }else if(a.getClickedBlock()==null){//判断点击方块是否是空
            return;
        }
        a.setCancelled(true);
        if(a.getAction().equals(Action.LEFT_CLICK_BLOCK)){//玩家左键一个方块
            xuanqukongzhiqi.dianjizuo(a.getPlayer(),a.getClickedBlock().getLocation());
            a.getPlayer().sendTitle("","已选择第一个点",0,10,40);
            if(main.peizhi.debug){System.out.println("玩家"+a.getPlayer().getName()+"选择第一个点"+a.getClickedBlock().getLocation());}
        }else if(a.getAction().equals(Action.RIGHT_CLICK_BLOCK)){//玩家右键一个方块
            xuanqukongzhiqi.dianjiyou(a.getPlayer(),a.getClickedBlock().getLocation());
            a.getPlayer().sendTitle("","已选择第二个点",0,10,40);
            if(main.peizhi.debug){System.out.println("玩家"+a.getPlayer().getName()+"选择第二个点"+a.getClickedBlock().getLocation());}
        }
    }

    @EventHandler
    public void wanjiaF(PlayerSwapHandItemsEvent a){//玩家按f时触发
        if(a.getOffHandItem()==null){
            return;
        }else if(a.getOffHandItem().getType().equals(main.peizhi.xuanquwupin)){
            a.setCancelled(true);
            xuanqukongzhiqi.anF(a.getPlayer());
            if(main.peizhi.debug){System.out.println("玩家"+a.getPlayer().getName()+"手持选择物品按下F");}
        }
    }

    @EventHandler
    public void dianjixiangzi(InventoryClickEvent a){//玩家点击物品栏时触发
        xuanqukongzhiqi.dianjibb(a);
    }

    @EventHandler
    public void tuicu(PlayerQuitEvent a){//玩家点击物品栏时触发
        xuanqukongzhiqi.likai(a.getPlayer());
    }
}
