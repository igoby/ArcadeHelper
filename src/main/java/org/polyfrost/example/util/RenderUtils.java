package org.polyfrost.example.util;

import cc.polyfrost.oneconfig.config.core.OneColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static void drawESP(Entity entity, OneColor color, float partialTicks) {
        float x = (float) (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double) partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX);
        float y = (float) (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY);
        float z = (float) (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double) partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ);
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        final AxisAlignedBB entityBox = entity.getEntityBoundingBox();
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                entityBox.minX - entity.posX + x - 0.05D,
                entityBox.minY - entity.posY + y,
                entityBox.minZ - entity.posZ + z - 0.05D,
                entityBox.maxX - entity.posX + x + 0.05D,
                entityBox.maxY - entity.posY + y + 0.15D,
                entityBox.maxZ - entity.posZ + z + 0.05D
        );
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(color.getRed()/ 255.0f, color.getGreen()/ 255.0f, color.getBlue()/ 255.0f, color.getAlpha()/ 255.0f);

        RenderGlobal.drawSelectionBoundingBox(axisAlignedBB);
        GlStateManager.resetColor();
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GL11.glPopMatrix();
    }
}
