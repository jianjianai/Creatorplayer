package cn.jji8.Creatorplayer;

import cn.jji8.Creatorplayer.Creator.gui;
import cn.jji8.Creatorplayer.Creator.kzq.tianchong;
import cn.jji8.Creatorplayer.Creator.xuanze;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    static main main;
    public static peizhi peizhi;
    //获取main
    public static main getmian(){
        return main;
    }
    @Override
    public void onEnable(){
        main = this;
        getLogger().info("开始加载..");
        getLogger().info("检查Residence插件是否加载");
        peizhi = new peizhi();
        PluginManager pm = getServer().getPluginManager();
        Plugin p = pm.getPlugin("Residence");
        boolean resjiazai;
        //res加载设置为true，未加载设置为false
        resjiazai = p==null?false:true;

        //输出日志
        if(resjiazai){
            getLogger().info("Residence领地插件已加载，启用兼容功能");
        }else {
            getLogger().info("Residence领地插件未加载，不启用兼容功能");
        }

        //加载控制器
        gui.addkzq(new tianchong());//填充控制器

        Bukkit.getPluginManager().registerEvents(new xuanze(),this);

        getLogger().info("加载完成");
    }
}
