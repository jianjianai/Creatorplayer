package cn.jji8.Creatorplayer.Creator.kzq;

import cn.jji8.Creatorplayer.Creator.dian;
import cn.jji8.Creatorplayer.main;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.google.common.collect.ComparisonChain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Fire;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if(pz.contains("填充按钮")){
            s = pz.getString("填充按钮");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮配置，已重新生成");
            pz.set("填充按钮","WOODEN_AXE");
            s = "WOODEN_AXE";
            baocun =true;
        }
        ItemStack wp = new ItemStack(Material.getMaterial(s));
        ItemMeta wpsj = wp.getItemMeta();
        String y;
        if(pz.contains("按钮名字")){
            y = pz.getString("按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            pz.set("按钮名字","使用下方方块填充选区");
            y = "使用下方方块填充选区";
            baocun =true;
        }
        wpsj.setDisplayName(y);
        List<String> k;
        if(pz.contains("按钮简介")){
            k = pz.getStringList("按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到按钮简介配置，已重新生成");
            k = new ArrayList<String>();
            k.add("将想填充的方块放在下方格子中");
            k.add("点击即可填充");
            pz.set("按钮简介",k);
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

        //每秒最大填充方块量
        if(!pz.contains("每秒最大填充方块量")){
            pz.set("每秒最大填充方块量",5);
            baocun = true;
        }
        每秒最大填充方块量 = pz.getInt("每秒最大填充方块量");

        //一次最大数量
        if(!pz.contains("一次最大数量")){
            pz.set("一次最大数量",32768);
            baocun = true;
        }
        一次最大数量 = pz.getInt("一次最大数量");

        if(baocun){
            try {
                pz.save(new File(main.getmian().getDataFolder(),"填充配置.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                main.getmian().getLogger().warning("填充配置保存失败");
            }
        }
    }

//------------------------------------------------------------------------
    dian dian;
    Inventory 箱子;

    static int 填充按钮位置,填充物品放置位置,每秒最大填充方块量,一次最大数量;
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
        点击位置.getWhoClicked().closeInventory();
        if(main.getmian().peizhi.debug){System.out.println("玩家点击了填充按钮");}
        ItemStack wp = 箱子.getItem(填充物品放置位置);
        if(main.getmian().peizhi.debug){System.out.println("准备填充"+(wp==null?"null":wp.getType()));}
        //多线程填充方块
        if(正在填充){
            dian.wanjia.sendTitle("请等待填充结束才可以开始下一个填充哦","正在填充，已填充"+计数+"方块",0,10,40);
            return;
        }
        正在填充 = true;
        Thread T = new Thread(){
            @Override
            public void run() {
                tiancun(wp==null?Material.AIR:wp.getType());
                正在填充 = false;
                计数 = 0;
            }
        };
        T.start();
    }

    //填充方法，用于填充方块
    void tiancun(Material wp){
        try {
            dian.getWeizi1().getWorld();
            dian.getWeizi2().getWorld();
            if(main.peizhi.debug)System.out.println("开始填充");
        }catch (NullPointerException h){
            dian.wanjia.sendTitle("","你没有选择两点哦",0,10,40);
            return;//玩家没有选择点
        }
        World world1= dian.getWeizi1().getWorld();
        double x1 = dian.getWeizi1().getBlockX();
        double y1 = dian.getWeizi1().getBlockY();
        double z1 = dian.getWeizi1().getBlockZ();

        World world2 = dian.getWeizi2().getWorld();
        double x2 = dian.getWeizi2().getBlockX();
        double y2 = dian.getWeizi2().getBlockY();
        double z2 = dian.getWeizi2().getBlockZ();
        if(!world1.equals(world2)){
            dian.wanjia.sendTitle("","两个点的世界不同哦",0,10,40);
            return;
        }
        if(!wp.isBlock()){
            dian.wanjia.sendTitle("","这不是一个可以放置的方块",0,10,40);
            return;
        }

        if(main.res){
            if(!ResidenceApi.getResidenceManager().getByLoc(dian.getWeizi1()).equals(ResidenceApi.getResidenceManager().getByLoc(dian.getWeizi2()))){
                dian.wanjia.sendTitle("","你的两个点在不同领地，不可以填充哦",0,10,40);
                return;
            }
        }

        double suliang = (x1-x2)*(y1-y2)*(z1-z2);
        if(suliang<0){
            suliang = suliang*-1;
        }
        if(suliang>一次最大数量){
            dian.wanjia.sendTitle("","你一次填充的太多了，最大"+一次最大数量+"你选择了"+suliang,0,10,40);
            return;
        }

        tiancun3(world1,wp,x1,x2,y1,y2,z1,z2);
    }
    void tiancun3(World world,Material wp,double x1,double x2,double y1,double y2,double z1,double z2){
        if(z1>z2){
            double a = z1;
            z1 = z2;
            z2 = a;
        }
        for(double i = z1;i<=z2;i++) {
            tiancun2(world,wp,x1,x2,y1,y2,i);
        }
    }
    void tiancun2(World world,Material wp,double x1,double x2,double y1,double y2,double z){
        if(y1>y2){
            double a = y1;
            y1 = y2;
            y2 = a;
        }
        for(double i = y1;i<=y2;i++) {
            tiancun1(world, wp, x1, x2, i, z);
        }
    }


    int 填充数量 = 0;
    int 计数 = 0;
    boolean 正在填充 = false;
    void tiancun1(World world,Material wp,double x1,double x2,double y,double z){
        if(x1>x2){
            double a = x1;
            x1 = x2;
            x2 = a;
        }
        for(double i = x1;i<=x2;i++){
            if(填充数量>每秒最大填充方块量){
                try {
                    dian.wanjia.sendTitle("","正在填充，已填充"+计数+"方块",0,10,40);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                填充数量 = 1;
            }
            Block blcock = new Location(world,i,y,z).getBlock();
            if(!blcock.getType().equals(wp)){
                BukkitRunnable BukkitRunnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(main.res){
                            if(!ResidenceApi.getResidenceManager().getByLoc(blcock.getLocation()).getPermissions().playerHas(dian.wanjia.getName(),"build",true)){
                                dian.wanjia.sendTitle("","无权限，已填充"+计数+"方块",0,10,40);
                                return;
                            }
                        }
                        blcock.setType(wp);
                        计数++;
                    }
                };
                BukkitRunnable.runTask(main.getmian());
                填充数量++;
            }
        }

    }
}
