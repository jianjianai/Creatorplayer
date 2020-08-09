package cn.jji8.Creatorplayer.Creator.kzq;

import cn.jji8.Creatorplayer.Creator.dian;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
* kzq 所有控制器的接口
* */
public interface kzq {
    /**
     * 用于加载配置，被添加到控制器中时自动调用
     * */
    public void peizi();
    /**
     * 获取一个新的自己，加载一个新gui时调用，每个gui存一个控制器
     * */
    public kzq get(dian 选择点,Inventory 箱子);
    /**
     * 玩家打开gui时调用，或者玩家点击gui后调用
     * */
    public void jiazai();
    /**
     * 玩家点击gui后调用
     * */
    public void dianji(InventoryClickEvent 点击位置);
}
