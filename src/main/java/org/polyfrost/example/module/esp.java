package org.polyfrost.example.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.passive.*;
import net.minecraft.scoreboard.Team;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.polyfrost.example.config.ArcadeHelperConfig;
import org.polyfrost.example.util.LocrawUtils;
import net.minecraft.entity.Entity;
import org.polyfrost.example.util.RenderUtils;

import java.util.HashSet;
import java.util.Set;

public class esp {
    private Set<Entity> fake = new HashSet<>();

    @SubscribeEvent
    public void render(RenderWorldLastEvent event) {
        if(LocrawUtils.isInArcade()) {
            for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                if(ArcadeHelperConfig.fh) {
                    if (entity instanceof EntityPig || entity instanceof EntityChicken || entity instanceof EntityCow || entity instanceof EntityOcelot || entity instanceof EntityHorse || entity instanceof EntityWolf || entity instanceof EntitySheep) {
                        if (getSpeed((EntityLivingBase) entity) != 0.0D && Math.abs(entity.rotationPitch) != 0.0F) {
                            fake.add(entity);
                        }
                    }
                }
                if (ArcadeHelperConfig.ph){
                    if (entity instanceof EntityArmorStand) {
                        if ((((EntityArmorStand) entity).getHeldItem() != null || ((EntityArmorStand) entity).getCurrentArmor(3) != null) && Math.abs(entity.rotationPitch) != 0.0F) {
                            fake.add(entity);
                        }else{
                            fake.remove(entity);
                        }
                    }
                }
                if(ArcadeHelperConfig.ppo) {
                    if (entity instanceof EntityOtherPlayerMP) {
                        if (!entity.isInvisible() && getSpeed((EntityLivingBase) entity) != 0.1D && isDifferentTeam((EntityOtherPlayerMP) entity)) {
                            fake.add(entity);
                        }else{
                            fake.remove(entity);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void renderE(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().theWorld.loadedEntityList.forEach(entity -> {
            if (fake.contains(entity)) {
                RenderUtils.drawESP(entity, ArcadeHelperConfig.espColor, event.partialTicks);
            }
        });
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        fake.clear();
    }

    private double getSpeed(EntityLivingBase entity) {
        try {
            return entity.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").getBaseValue();
        } catch (Exception var) {
            return entity.getMaxHealth();
        }
    }

    private boolean isDifferentTeam(EntityOtherPlayerMP entity) {
        return entity.getTeam() == null || entity.getTeam().getNameTagVisibility() == Team.EnumVisible.NEVER || entity.getTeam().getNameTagVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS && Minecraft.getMinecraft().thePlayer.getTeam() != entity.getTeam();
    }
}
