package org.polyfrost.example.util;

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;


public class LocrawUtils {
    public static boolean isInArcade(){
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        return locraw != null && locraw.getGameType() == LocrawInfo.GameType.ARCADE_GAMES && LocrawUtil.INSTANCE.isInGame();
    }
}
