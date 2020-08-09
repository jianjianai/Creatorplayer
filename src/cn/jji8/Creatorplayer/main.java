package cn.jji8.Creatorplayer;

import cn.jji8.Creatorplayer.Creator.gui;
import cn.jji8.Creatorplayer.Creator.kzq.fuzhi;
import cn.jji8.Creatorplayer.Creator.kzq.lizi;
import cn.jji8.Creatorplayer.Creator.kzq.tianchong;
import cn.jji8.Creatorplayer.Creator.xuanze;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    static main main;
    public static peizhi peizhi;
    public static boolean res = false;
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
        //检查res领地插件
        PluginManager pm = getServer().getPluginManager();
        Plugin p = pm.getPlugin("Residence");
        if(p!=null) {
            if(!p.isEnabled()) {
                getLogger().info("检测到Residence，与检测到Residence对接");
                pm.enablePlugin(p);
                res = true;
            }
        } else {
            getLogger().info("没有安装Residence取消Residence的全部功能");
        }
        //加载创作者标识
        if(res) {
            FlagPermissions.addFlag("build");
        }

        //加载控制器
        gui.addkzq(new tianchong());//填充控制器
        gui.addkzq(new lizi());//粒子控制器
        gui.addkzq(new fuzhi());//复制控制器


        Bukkit.getPluginManager().registerEvents(new xuanze(),this);

        getLogger().info("加载完成");
    }
}
