package cn.jji8.Creatorplayer.Creator;

import org.bukkit.entity.Player;

public class wanjia {
    gui gui;
    wanjia(Player wanjia){
        gui = new gui(wanjia);
    }


//set get 方法
    public cn.jji8.Creatorplayer.Creator.gui getGui() {
        return gui;
    }
}
