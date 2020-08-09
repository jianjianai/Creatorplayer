package cn.jji8.Creatorplayer.Creator;

import org.bukkit.entity.Player;
/**
 * 代表一个玩家
 * */
public class wanjia {
    gui gui;
    /**
     * 创建一个玩家
     * */
    wanjia(Player wanjia){
        gui = new gui(wanjia);
    }


//set get 方法
    /**
     * 获取玩家的gui
     * */
    public cn.jji8.Creatorplayer.Creator.gui getGui() {
        return gui;
    }
}
