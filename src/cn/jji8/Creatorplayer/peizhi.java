package cn.jji8.Creatorplayer;

import org.bukkit.Material;

import java.io.File;
import java.io.IOException;
/**
 * 用于加载插件全局配置
 * */
public class peizhi {
    public Material xuanquwupin;
    public boolean debug,只允许在自己领地中使用创作者;
    public String 箱子标题;
    public int 箱子大小;

    public peizhi(){
        boolean baocunpeizhi = false;
        main.getmian().getLogger().info("开始加载配置");
        String s;
        //只允许在自己领地中使用创作者
        if(main.getmian().getConfig().contains("只允许在自己领地中使用创作者")){
            只允许在自己领地中使用创作者 = main.getmian().getConfig().getBoolean("只允许在自己领地中使用创作者");
        }else {
            main.getmian().getLogger().warning("未找到只允许在自己领地中使用创作者配置，已重新生成");
            main.getmian().getConfig().set("只允许在自己领地中使用创作者",true);
            只允许在自己领地中使用创作者 = true;
            baocunpeizhi =true;
        }
        //debug
        if(main.getmian().getConfig().contains("debug")){
            debug = main.getmian().getConfig().getBoolean("debug");
        }else {
            main.getmian().getLogger().warning("未找到debug配置，已重新生成");
            main.getmian().getConfig().set("debug",false);
            debug = false;
            baocunpeizhi =true;
        }
        //箱子大小
        if(main.getmian().getConfig().contains("箱子大小")){
            箱子大小 = main.getmian().getConfig().getInt("箱子大小");
        }else {
            main.getmian().getLogger().warning("未找到箱子大小配置，已重新生成");
            main.getmian().getConfig().set("箱子大小",3);
            箱子大小 = 3;
            baocunpeizhi =true;
        }
        //加载选取工具
        if(main.getmian().getConfig().contains("选区工具")){
            s = main.getmian().getConfig().getString("选区工具");
        }else {
            main.getmian().getLogger().warning("未找到选区工具配置，已重新生成");
            main.getmian().getConfig().set("选区工具","WOODEN_AXE");
            s = "WOODEN_AXE";
            baocunpeizhi =true;
        }
        xuanquwupin = org.bukkit.Material.getMaterial(s);
        if(xuanquwupin==null){
            main.getmian().getLogger().warning("选区物品配置不正确，已为你设置为默认值");
            xuanquwupin = Material.WOODEN_AXE;
        }
        main.getmian().getLogger().info("选区物品是"+xuanquwupin.toString());
        //加载箱子标题
        if(main.getmian().getConfig().contains("箱子标题")){
            箱子标题 = main.getmian().getConfig().getString("箱子标题");
        }else {
            main.getmian().getLogger().warning("未找到箱子标题配置，已重新生成");
            main.getmian().getConfig().set("箱子标题","%玩家%的创作者操作界面");
            箱子标题 = "%玩家%的创作者操作界面";
            baocunpeizhi =true;
        }



        if(baocunpeizhi){
            try {
                main.getmian().getConfig().save(new File(main.getmian().getDataFolder(),"config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
                main.getmian().getLogger().warning("配置文件释放失败");
            }
        }
    }
}
