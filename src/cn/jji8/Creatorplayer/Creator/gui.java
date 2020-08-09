package cn.jji8.Creatorplayer.Creator;

import cn.jji8.Creatorplayer.Creator.kzq.kzq;
import cn.jji8.Creatorplayer.main;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * 负责gui的控制
 * */
public class gui {

    static ArrayList<kzq> 初始化控制器 = new ArrayList<kzq>();//加载控制器
    /**
     * 添加控制器，会在适合的时间调用控制器的方法
     * */
    public static void addkzq(kzq kzq){
        初始化控制器.add(kzq);
        kzq.peizi();
    }

    ArrayList<kzq> wanjiakzq = new ArrayList<kzq>();//玩家控制器
    dian dian;
    Inventory xiangzi;

    /**
     * 创建一个新的gui
     * */
    gui(Player wanjia){
        dian = new dian(wanjia);
        xiangzi = org.bukkit.Bukkit.createInventory(null,main.peizhi.箱子大小*9, main.peizhi.箱子标题.replaceAll("%玩家%",wanjia.getName()));
        for(int i = 0;i<初始化控制器.size();i++){
            wanjiakzq.add(初始化控制器.get(i).get(dian,xiangzi));
        }
    }
    /**
     * 为gui的主人打开这个gui
     * */
    public void dakai(){
        for(int i = 0;i<wanjiakzq.size();i++){
            wanjiakzq.get(i).jiazai();
        }
        dian.getwanjia().openInventory(xiangzi);
    }

    /**
     * 调用此方法，表示gui的主人点击了gui的某位置
     * */
    public void dianji(InventoryClickEvent a){
        for(int i = 0;i<wanjiakzq.size();i++){
            wanjiakzq.get(i).dianji(a);
        }
        for(int i = 0;i<wanjiakzq.size();i++){
            wanjiakzq.get(i).jiazai();
        }
    }


//set get 方法

    /**
     * 获取gui的箱子
     * */
    public Inventory getXiangzi() {
        return xiangzi;
    }

    /**
     * 获取gui主人的点
     * */
    public cn.jji8.Creatorplayer.Creator.dian getDian() {
        return dian;
    }
}
