package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

/**
 * 选区控制器
 * 控制玩家有效选区
 * 实现多玩家分开控制
 * */
public class xuanqukongzhiqi{
    HashMap<String,wanjia> biao = new HashMap();//玩家选区表
    /**
     * 玩家左键调用
     * */
    public void dianjizuo(Player 玩家名字, Location weizi){
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        wanjia wanjia = biao.get(玩家名字.getName());
        wanjia.getGui().getDian().setWeizi1(weizi);
        wanjia.getGui().getDian().xianshi();

    }
    /**
     * 玩家点击右边调用
     * */
    public void dianjiyou(Player 玩家名字,Location weizi){//点击右键时调用
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        wanjia wanjia = biao.get(玩家名字.getName());
        wanjia.getGui().getDian().setWeizi2(weizi);
        wanjia.getGui().getDian().xianshi();
    }

    /**
     * 代表这个玩家使用选区物品按f
     * */
    public void anF(Player 玩家名字){//按f时调用
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        biao.get(玩家名字.getName()).getGui().dakai();
    }

    /**
     * 当有点击事件时被调用
     * */
    public void dianjibb(InventoryClickEvent a){//点击箱子时调用
        if(a.getClickedInventory()==null){//判断点击的箱子是否为空
            return;
        }
        wanjia wanjia = biao.get(a.getWhoClicked().getName());
        if(!a.getClickedInventory().equals(wanjia.getGui().getXiangzi())){//判断点击的箱子是否是gui
            return;
        }
        wanjia.getGui().dianji(a);
        if(main.peizhi.debug){System.out.println("玩家"+a.getWhoClicked().getName()+"点击了gui");}
    }

    /**
     * 添加玩家，调用此方法会判断表里是否有此玩家，如果没有就添加并且自动初始化
     * */
    void tianjiawanjia(Player 玩家名字){
        if(!biao.containsKey(玩家名字.getName())){//判断表里是否有此玩家
            biao.put(玩家名字.getName(),new wanjia(玩家名字));//没有就添加一个
            if(main.peizhi.debug){System.out.println("表中没有玩家"+玩家名字+"添加玩家");}
        }
    }
    /**
     * 当玩家离开服务器时调用此方法删除玩家
     * */
    public void likai(Player a){
        biao.remove(a.getName());
    }
}
