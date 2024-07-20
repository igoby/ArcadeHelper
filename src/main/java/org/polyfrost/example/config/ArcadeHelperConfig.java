package org.polyfrost.example.config;

import cc.polyfrost.oneconfig.config.annotations.Color;
import cc.polyfrost.oneconfig.config.core.OneColor;
import net.minecraftforge.common.MinecraftForge;
import org.polyfrost.example.ArcadeHelper;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import org.polyfrost.example.module.esp;

public class ArcadeHelperConfig extends Config {


    @Switch(
            name = "Enabled",
            subcategory = "Farm Hunt ESP"
    )
    public static boolean fh = false;

    @Switch(
            name = "Enabled",
            subcategory = "Prop Hunt ESP"
    )
    public static boolean ph = false;

    @Switch(
            name = "Enabled",
            subcategory = "Party Pooper ESP"
    )
    public static boolean ppo = false;

    @Color(
            name = "ESP Color"
    )
    public static OneColor espColor = new OneColor(85, 255, 85, 255);


    public ArcadeHelperConfig() {
        super(new Mod(ArcadeHelper.NAME, ModType.UTIL_QOL), ArcadeHelper.MODID + ".json");
        initialize();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new esp());
    }
}

