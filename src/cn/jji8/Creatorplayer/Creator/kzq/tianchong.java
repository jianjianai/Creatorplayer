package cn.jji8.Creatorplayer.Creator.kzq;

import cn.jji8.Creatorplayer.Creator.dian;
import cn.jji8.Creatorplayer.main;
import org.bukkit.Material;
import org.bukkit.block.data.type.Fire;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tianchong implements kzq{

    public void peizi(){//加载填充配置
        YamlConfiguration pz = YamlConfiguration.loadConfiguration(new File(main.getmian().getDataFolder(),"填充配置.yml"));
        boolean baocun = false;
        //加载填充按钮位置
        if(!pz.contains("填充按钮位置")){
            pz.set("填充按钮位置",0);
            baocun = true;
        }
        填充按钮位置 = pz.getInt("填充按钮位置");

        //加载填充按钮
        String s;
        if(main.getmian().getConfig().contains("填充按钮")){
            s = main.getmian().getConfig().getString("填充按钮");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮配置，已重新生成");
            main.getmian().getConfig().set("填充按钮","WOODEN_AXE");
            s = "WOODEN_AXE";
            baocun =true;
        }
        ItemStack wp = new ItemStack(Material.getMaterial(s));
        ItemMeta wpsj = wp.getItemMeta();
        String y;
        if(main.getmian().getConfig().contains("按钮名字")){
            y = main.getmian().getConfig().getString("按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            main.getmian().getConfig().set("按钮名字","使用下方方块填充选区");
            y = "使用下方方块填充选区";
            baocun =true;
        }
        wpsj.setDisplayName(y);
        List<String> k;
        if(main.getmian().getConfig().contains("按钮简介")){
            k = main.getmian().getConfig().getStringList("按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到按钮简介配置，已重新生成");
            k = new ArrayList<String>();
            k.add("将想填充的方块放在下方格子中");
            k.add("点击即可填充");
            main.getmian().getConfig().set("按钮简介",k);
            baocun =true;
        }
        wpsj.setLore(k);
        wp.setItemMeta(wpsj);
        填充按钮 = wp;

        //填充物品放置位置
        if(!pz.contains("填充物品放置位置")){
            pz.set("填充物品放置位置",9);
            baocun = true;
        }
        填充物品放置位置 = pz.getInt("填充物品放置位置");

        if(baocun){
            try {
                pz.save(new File(main.getmian().getDataFolder(),"填充配置.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                main.getmian().getLogger().warning("填充配置保存失败");
            }
        }
    }

    dian dian;
    Inventory 箱子;

    static int 填充按钮位置,填充物品放置位置;
    static ItemStack 填充按钮;

    @Override
    public kzq get(dian 选择点, Inventory 箱子) {
        tianchong a = new tianchong();
        a.dian = 选择点;
        a.箱子 = 箱子;
        return a;
    }

    @Override
    public void jiazai(){
        箱子.setItem(填充按钮位置,填充按钮);
    }

    @Override
    public void dianji(InventoryClickEvent 点击位置) {
        if(!(点击位置.getRawSlot() == 填充按钮位置)){
            return;
        }
        点击位置.setCancelled(true);
        if(main.getmian().peizhi.debug){System.out.println("玩家点击了填充按钮");}
        if(main.getmian().peizhi.debug){System.out.println("准备填充"+箱子.getItem(填充物品放置位置).getType());}
    }
}
