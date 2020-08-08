package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.main;
import org.bukkit.Location;

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
    public void dianjizuo(String 玩家名字, Location weizi){
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        wanjia wanjia = biao.get(玩家名字);
        wanjia.getGui().getDian().setWeizi1(weizi);
    }
    /**
     * 玩家点击右边调用
     * */
    public void dianjiyou(String 玩家名字,Location weizi){//点击右键时调用
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        wanjia wanjia = biao.get(玩家名字);
        wanjia.getGui().getDian().setWeizi2(weizi);
    }

    public void anF(String 玩家名字){//按f时调用
        tianjiawanjia(玩家名字);//如果表里没有玩家就添加玩家
        wanjia wanjia = biao.get(玩家名字);
        biao.get(玩家名字).getGui().dakai();
    }

    void tianjiawanjia(String 玩家名字){
        if(!biao.containsKey(玩家名字)){//判断表里是否有此玩家
            biao.put(玩家名字,new wanjia(玩家名字));//没有就添加一个
            if(main.peizhi.debug){System.out.println("表中没有玩家"+玩家名字+"添加玩家");}
        }
    }
}
