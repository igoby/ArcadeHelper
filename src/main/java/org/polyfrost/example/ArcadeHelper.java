package org.polyfrost.example;

import org.polyfrost.example.command.ArcadeHelperCommand;
import org.polyfrost.example.config.ArcadeHelperConfig;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;


@Mod(modid = ArcadeHelper.MODID, name = ArcadeHelper.NAME, version = ArcadeHelper.VERSION)
public class ArcadeHelper {

    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static ArcadeHelper INSTANCE;
    public static ArcadeHelperConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new ArcadeHelperConfig();
        CommandManager.INSTANCE.registerCommand(new ArcadeHelperCommand());
    }
}
