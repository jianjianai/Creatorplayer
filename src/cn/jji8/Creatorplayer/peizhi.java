package cn.jji8.Creatorplayer;

import org.bukkit.Material;

import java.io.File;
import java.io.IOException;

public class peizhi {
    public Material xuanquwupin;
    public peizhi(){
        boolean baocunpeizhi = false;
        main.getmian().getLogger().info("开始加载配置");
        String s;
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
        main.getmian().getLogger().info("选区物品是"+xuanquwupin.toString());



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
