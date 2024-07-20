package org.polyfrost.example.command;

import org.polyfrost.example.ArcadeHelper;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

@Command(value = ArcadeHelper.MODID, aliases = {"ah"}, description = "Access the " + ArcadeHelper.NAME + " GUI.")
public class ArcadeHelperCommand {
    @Main
    private void handle() {
        ArcadeHelper.INSTANCE.config.openGui();
    }
}