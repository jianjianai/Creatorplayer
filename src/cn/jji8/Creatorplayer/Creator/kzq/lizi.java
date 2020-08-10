package cn.jji8.Creatorplayer.Creator.kzq;

import cn.jji8.Creatorplayer.Creator.dian;
import cn.jji8.Creatorplayer.main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class lizi implements kzq{
    /**
     * 用于加载配置，被添加到控制器中时自动调用
     * */
    @Override
    public void peizi() {
        YamlConfiguration pz = YamlConfiguration.loadConfiguration(new File(main.getmian().getDataFolder(),"粒子配置.yml"));
        boolean baocun = false;
        if(!pz.contains("最大显示时间")){
            pz.set("最大显示时间",120);
            baocun = true;
        }
        最大显示时间 = pz.getInt("最大显示时间");

        //加载填充按钮位置
        if(!pz.contains("按钮位置")){
            pz.set("按钮位置",1);
            baocun = true;
        }
        按钮位置 = pz.getInt("按钮位置");

        //加载填充按钮
        String s;
        if(pz.contains("按钮")){
            s = pz.getString("按钮");
        }else {
            main.getmian().getLogger().warning("未找到按钮配置，已重新生成");
            pz.set("按钮","STICK");
            s = "STICK";
            baocun =true;
        }
        ItemStack wp = new ItemStack(Material.getMaterial(s));
        ItemMeta wpsj = wp.getItemMeta();
        String y;
        if(pz.contains("按钮名字")){
            y = pz.getString("按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            pz.set("按钮名字","显示粒子效果");
            y = "显示粒子效果";
            baocun =true;
        }
        wpsj.setDisplayName(y);
        List<String> k;
        if(pz.contains("按钮简介")){
            k = pz.getStringList("按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到按钮简介配置，已重新生成");
            k = new ArrayList<String>();
            k.add("点击可以打开粒子效果");
            k.add("持续显示");
            pz.set("按钮简介",k);
            baocun =true;
        }
        wpsj.setLore(k);
        wp.setItemMeta(wpsj);
        按钮 = wp;

        //填充物品放置位置
        if(!pz.contains("填充物品放置位置")){
            pz.set("填充物品放置位置",10);
            baocun = true;
        }
        填充物品放置位置 = pz.getInt("填充物品放置位置");

        //加载填充按钮
        String q;
        if(pz.contains("填充按钮")){
            q = pz.getString("填充按钮");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮配置，已重新生成");
            pz.set("填充按钮","REDSTONE");
            q = "REDSTONE";
            baocun =true;
        }
        ItemStack wp1 = new ItemStack(Material.getMaterial(q));
        ItemMeta wpsj1 = wp1.getItemMeta();
        String y1;
        if(pz.contains("填充按钮名字")){
            y1 = pz.getString("填充按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            pz.set("填充按钮名字","粒子效果已经打开");
            y1 = "显示粒子效果";
            baocun =true;
        }
        wpsj1.setDisplayName(y1);
        List<String> k1;
        if(pz.contains("填充按钮简介")){
            k1 = pz.getStringList("填充按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮简介配置，已重新生成");
            k1 = new ArrayList<String>();
            k1.add("会持续显示");
            k1.add("持续显示");
            pz.set("填充按钮简介",k1);
            baocun =true;
        }
        wpsj1.setLore(k1);
        wp1.setItemMeta(wpsj1);
        填充按钮 = wp1;

        if(baocun){
            try {
                pz.save(new File(main.getmian().getDataFolder(),"粒子配置.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                main.getmian().getLogger().warning("粒子配置保存失败");
            }
        }

    }
    /**
     * 获取一个新的自己，加载一个新gui时调用，每个gui存一个控制器
     * */
    @Override
    public kzq get(dian 选择点, Inventory 箱子) {
        lizi a = new lizi();
        a.箱子 = 箱子;
        a.dian = 选择点;
        return a;
    }

//------------------------------------------------------------------------
    dian dian;
    Inventory 箱子;
    boolean 显示;
    boolean 线程 = false;

    static int 最大显示时间,按钮位置,填充物品放置位置;
    static ItemStack 按钮,填充按钮;

    /**
     * 玩家打开gui时调用，或者玩家点击gui后调用
     * */
    @Override
    public void jiazai() {
        if (!dian.wanjia.hasPermission("Creatorplayer.lizi")){//判断权限
            return;
        }
        箱子.setItem(按钮位置,按钮);
        if(显示){
            箱子.setItem(填充物品放置位置,填充按钮);
        }else {
            箱子.setItem(填充物品放置位置,null);
        }
    }

    /**
     * 玩家点击gui后调用
     * */
    @Override
    public void dianji(InventoryClickEvent 点击位置) {
        if (!dian.wanjia.hasPermission("Creatorplayer.lizi")){//判断权限
            return;
        }
        boolean f = false;
        if(点击位置.getRawSlot()!=按钮位置){
            if(!显示){
                return;
            } else if(点击位置.getRawSlot()!=填充物品放置位置){
                return;
            }
        }

        if(main.peizhi.debug) System.out.println("玩家点击了有效位置");
        点击位置.setCancelled(true);

        if(显示){
            显示 = false;
        }else {
            显示 = true;
        }
        if(线程){
            return;
        }
        线程 = true;
        Thread T = new Thread(){
            @Override
            public void run() {
                for(int i=0;i<=最大显示时间;i++){
                    if(!显示){
                        线程 = false;
                        return;
                    }
                    dian.xianshi();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                显示 = false;
                线程 = false;
            }
        };
        T.start();
    }
}
