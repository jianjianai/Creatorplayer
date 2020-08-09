package cn.jji8.Creatorplayer.Creator.kzq;

import cn.jji8.Creatorplayer.Creator.dian;
import cn.jji8.Creatorplayer.main;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于操作复杂选项
 * */
public class fuzhi implements kzq{
    /**
     * 用于加载配置，被添加到控制器中时自动调用
     */
    @Override
    public void peizi() {
        YamlConfiguration pz = YamlConfiguration.loadConfiguration(new File(main.getmian().getDataFolder(),"复制配置.yml"));
        boolean baocun = false;
        if(!pz.contains("一次最大复制数量")){
            pz.set("一次最大复制数量",32768);
            baocun = true;
        }
        一次最大复制数量 = pz.getInt("一次最大复制数量");

        //加载填充按钮位置
        if(!pz.contains("按钮位置")){
            pz.set("按钮位置",2);
            baocun = true;
        }
        按钮位置 = pz.getInt("按钮位置");

        //加载填充按钮
        String s;
        if(pz.contains("按钮")){
            s = pz.getString("按钮");
        }else {
            main.getmian().getLogger().warning("未找到按钮配置，已重新生成");
            pz.set("按钮","NAME_TAG");
            s = "NAME_TAG";
            baocun =true;
        }
        ItemStack wp = new ItemStack(Material.getMaterial(s));
        ItemMeta wpsj = wp.getItemMeta();
        String y;
        if(pz.contains("按钮名字")){
            y = pz.getString("按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            pz.set("按钮名字","复制选区");
            y = "复制选区";
            baocun =true;
        }
        wpsj.setDisplayName(y);
        List<String> k;
        if(pz.contains("按钮简介")){
            k = pz.getStringList("按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到按钮简介配置，已重新生成");
            k = new ArrayList<String>();
            k.add("点击复制选区到剪切板");
            k.add("显示在下方");
            pz.set("按钮简介",k);
            baocun =true;
        }
        wpsj.setLore(k);
        wp.setItemMeta(wpsj);
        按钮 = wp;

        //填充物品放置位置
        if(!pz.contains("填充物品放置位置")){
            pz.set("填充物品放置位置",11);
            baocun = true;
        }
        填充物品放置位置 = pz.getInt("填充物品放置位置");

        //加载填充按钮
        String q,q1;
        if(pz.contains("填充按钮")){
            q = pz.getString("填充按钮");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮配置，已重新生成");
            pz.set("填充按钮","STONE");
            q = "STONE";
            baocun =true;
        }
        if(pz.contains("填充按钮1")){
            q1 = pz.getString("填充按钮1");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮1配置，已重新生成");
            pz.set("填充按钮1","COBBLESTONE");
            q1 = "COBBLESTONE";
            baocun =true;
        }
        ItemStack wp1 = new ItemStack(Material.getMaterial(q));
        ItemStack wp2 = new ItemStack(Material.getMaterial(q1));
        ItemMeta wpsj1 = wp1.getItemMeta();
        String y1;
        if(pz.contains("填充按钮名字")){
            y1 = pz.getString("填充按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到按钮名字配置，已重新生成");
            pz.set("填充按钮名字","剪切板");
            y1 = "剪切板";
            baocun =true;
        }
        wpsj1.setDisplayName(y1);
        List<String> k1;
        if(pz.contains("填充按钮简介")){
            k1 = pz.getStringList("填充按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到填充按钮简介配置，已重新生成");
            k1 = new ArrayList<String>();
            k1.add("点击粘连剪切板");
            k1.add("剪切板位子");
            k1.add("点击将剪切版粘连到第一个点");
            pz.set("填充按钮简介",k1);
            baocun =true;
        }
        wpsj1.setLore(k1);
        wp1.setItemMeta(wpsj1);
        wp2.setItemMeta(wpsj1);
        填充按钮 = wp1;
        填充按钮1 = wp2;

        //加载取消按钮
        if(pz.contains("取消按钮")){
            s = pz.getString("取消按钮");
        }else {
            main.getmian().getLogger().warning("未找到取消按钮配置，已重新生成");
            pz.set("取消按钮","REDSTONE_BLOCK");
            s = "REDSTONE_BLOCK";
            baocun =true;
        }
        wp = new ItemStack(Material.getMaterial(s));
        wpsj = wp.getItemMeta();
        if(pz.contains("取消按钮名字")){
            y = pz.getString("取消按钮名字");
        }else {
            main.getmian().getLogger().warning("未找到取消按钮按钮名字配置，已重新生成");
            pz.set("取消按钮按钮名字","取消填充");
            y = "取消填充";
            baocun =true;
        }
        wpsj.setDisplayName(y);
        List<String> kk;
        if(pz.contains("取消按钮按钮简介")){
            kk = pz.getStringList("取消按钮按钮简介");
        }else {
            main.getmian().getLogger().warning("未找到取消按钮按钮简介配置，已重新生成");
            kk = new ArrayList<String>();
            kk.add("取消正在执行的填充");
            pz.set("取消按钮按钮简介",kk);
            baocun =true;
        }
        wpsj.setLore(k);
        wp.setItemMeta(wpsj);
        取消按钮 = wp;

        //取消按钮位置
        if(!pz.contains("取消按钮位置")){
            pz.set("取消按钮位置",20);
            baocun = true;
        }
        取消按钮位置 = pz.getInt("取消按钮位置");

        //每秒最大填充方块量
        if(!pz.contains("每秒最大填充方块量")){
            pz.set("每秒最大填充方块量",5);
            baocun = true;
        }
        每秒最大填充方块量 = pz.getInt("每秒最大填充方块量");

        if(baocun){
            try {
                pz.save(new File(main.getmian().getDataFolder(),"复制配置.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                main.getmian().getLogger().warning("复制配置保存失败");
            }
        }

    }

    /**
     * 获取一个新的自己，加载一个新gui时调用，每个gui存一个控制器
     *
     * @param 选择点
     * @param 箱子
     */
    @Override
    public kzq get(dian 选择点, Inventory 箱子) {
        fuzhi a = new fuzhi();
        a.箱子 = 箱子;
        a.dian = 选择点;
        return a;
    }

    //----------------------------------------------------
    static int 按钮位置,填充物品放置位置,一次最大复制数量,每秒最大填充方块量,取消按钮位置;
    static ItemStack 按钮,填充按钮,填充按钮1,取消按钮;

    dian dian;
    Inventory 箱子;
    boolean 取消 = false;

    Location wz1 = null;
    Location wz2 = null;
    boolean wp = true;
    /**
     * 玩家打开gui时调用，或者玩家点击gui后调用
     */
    @Override
    public void jiazai() {
        箱子.setItem(按钮位置,按钮);
        if(wz1!=null&wz2!=null){
            if(wp){
                箱子.setItem(填充物品放置位置,填充按钮1);
            }else {
                箱子.setItem(填充物品放置位置,填充按钮);
            }
        }else {
            箱子.setItem(填充物品放置位置,null);
        }
        箱子.setItem(取消按钮位置,取消按钮);
    }

    /**
     * 玩家点击gui后调用
     *
     * @param 点击位置
     */
    @Override
    public void dianji(InventoryClickEvent 点击位置) {
        if(点击位置.getRawSlot()==按钮位置){
            点击位置.setCancelled(true);
            if(dian.getWeizi1()==null){
                dian.wanjia.sendTitle("","你还没有选区哦",0,10,40);
                return;
            }
            if(dian.getWeizi2()==null){
                dian.wanjia.sendTitle("","你还没有选区哦",0,10,40);
                return;
            }
            wz1 = dian.getWeizi1();
            wz2 = dian.getWeizi2();
            dian.wanjia.sendTitle("","已将选区放到剪切板",0,10,40);
            wp = !wp;
        }else if(点击位置.getRawSlot()==填充物品放置位置){
            if(wz1==null|wz2==null){
                return;
            }
            点击位置.setCancelled(true);
            点击位置.getWhoClicked().closeInventory();
            if(main.peizhi.debug){ System.out.println("准备复制剪切板内容"); }

            //多线程填充方块
            if(正在填充){
                dian.wanjia.sendTitle("请等待填充结束才可以开始下一个填充哦","正在填充，复制"+计数+"方块",0,10,40);
                return;
            }
            正在填充 = true;
            Thread T = new Thread(){
                @Override
                public void run() {
                    取消 = false;
                    fuzi();
                    正在填充 = false;
                    计数 = 0;
                }
            };
            T.start();
        }else if(点击位置.getRawSlot()==取消按钮位置){
            点击位置.setCancelled(true);
            点击位置.getWhoClicked().closeInventory();
            取消 = true;
            dian.wanjia.sendTitle("","已取消填充",0,10,40);
        }
    }

    int 填充数量 = 0;
    int 计数 = 0;
    boolean 正在填充 = false;
    /**
     * 复制方法，用于填充方块
     */
    void fuzi(){
        try {
            wz1.getWorld();
            wz2.getWorld();
            if(main.peizhi.debug)System.out.println("开始填充");
        }catch (NullPointerException h){
            dian.wanjia.sendTitle("","你没有复制任何东西哦",0,10,40);
            return;//玩家没有选择点
        }
        World world1= wz1.getWorld();
        double x1 = wz1.getBlockX();
        double y1 = wz1.getBlockY();
        double z1 = wz1.getBlockZ();

        World world2 = wz2.getWorld();
        double x2 = wz2.getBlockX();
        double y2 = wz2.getBlockY();
        double z2 = wz2.getBlockZ();
        if(!world1.equals(world2)){
            dian.wanjia.sendTitle("","复制错误",0,10,40);
            return;
        }

        if(main.res){
            if(!ResidenceApi.getResidenceManager().getByLoc(wz1).equals(ResidenceApi.getResidenceManager().getByLoc(wz2))){
                dian.wanjia.sendTitle("","不可以跨领地复制哦",0,10,40);
                return;
            }
        }

        double suliang = (x1-x2)*(y1-y2)*(z1-z2);
        if(suliang<0){
            suliang = suliang*-1;
        }
        if(suliang>一次最大复制数量){
            dian.wanjia.sendTitle("","你一次复制的太多了，最大"+一次最大复制数量+"你选择了"+suliang,0,10,40);
            return;
        }

        fuzi13(world1,x1,x2,y1,y2,z1,z2,dian.getWeizi1().getWorld(),dian.getWeizi1().getBlockX(),dian.getWeizi1().getBlockY(),dian.getWeizi1().getBlockZ());
    }
    void fuzi13(World world,double x1,double x2,double y1,double y2,double z1,double z2,World wzworld,double wzx,double wzy,double wzz){
        if(z1>z2){
            double a = z1;
            z1 = z2;
            z2 = a;
        }
        for(double i = 0;i+z1<=z2;i++) {
            fuzi12(world,x1,x2,y1,y2,i+z1,wzworld,wzx,wzy,i+wzz);
        }
    }

    void fuzi12(World world1,double x1,double x2,double y1,double y2,double z,World wzworld,double wzx,double wzy,double wzz){
        if(y1>y2){
            double a = y1;
            y1 = y2;
            y2 = a;
        }
        for(double i = 0;i+y1<=y2;i++) {
            fuzi1(world1,x1,x2,i+y1,z,wzworld,wzx,i+wzy,wzz);
        }
    }

    void fuzi1(World world1,double x1,double x2,double y,double z,World wzworld,double wzx,double wzy,double wzz){
        if(x1>x2){
            double a = x1;
            x1 = x2;
            x2 = a;
        }
        for(double i = 0;x1+i<=x2;i++){
            if(取消){
                return;
            }
            if(填充数量>每秒最大填充方块量){
                try {
                    dian.wanjia.sendTitle("","正在填充，已复制"+计数+"方块",0,10,40);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                填充数量 = 1;
            }
            Block blcock = new Location(world1,x1+i,y,z).getBlock();
            Block Block2 = new Location(wzworld,wzx+i,wzy,wzz).getBlock();
            if(!blcock.getType().equals(Block2.getType())){
                BukkitRunnable BukkitRunnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if(main.res){
                            ClaimedResidence res = ResidenceApi.getResidenceManager().getByLoc(blcock.getLocation());
                            ClaimedResidence res1 = ResidenceApi.getResidenceManager().getByLoc(Block2.getLocation());
                            if(res==null|res1==null){
                                dian.wanjia.sendTitle("","无权限，已复制"+计数+"方块",0,10,40);
                                return;
                            }
                            if(!res.getPermissions().playerHas(dian.wanjia.getName(),"build",true)){
                                dian.wanjia.sendTitle("","无权限，已复制"+计数+"方块",0,10,40);
                                return;
                            }
                            if(!res1.getPermissions().playerHas(dian.wanjia.getName(),"build",true)){
                                dian.wanjia.sendTitle("","无权限，已复制"+计数+"方块",0,10,40);
                                return;
                            }
                        }
                        Block2.setType(blcock.getType());
                        Block2.setBlockData(blcock.getBlockData());
                        计数++;
                    }
                };
                BukkitRunnable.runTask(main.getmian());
                填充数量++;
            }
        }

    }
}
