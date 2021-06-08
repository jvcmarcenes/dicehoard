package io.github.jvcmarcenes.dicehoard.client;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DiceEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandlerForge {
  
  @SubscribeEvent
  public static void onRenderWorld(final RenderWorldLastEvent event) {
    Minecraft mc = Minecraft.getInstance();
    
    for (Entity ent : mc.world.getAllEntities()) {
      if (!(ent instanceof DiceEntity)) continue;
      if (mc.player.getPositionVec().distanceTo(ent.getPositionVec()) > 30) continue;

      DiceEntity dent = (DiceEntity)ent;

      if (!dent.isLanded()) continue;

      String display = Integer.toString(dent.getRolledNumber());

      MatrixStack ms = event.getMatrixStack();

      ms.push();

      ms.translate(ent.getPosX() - mc.player.getPosX(), ent.getPosY() - mc.player.getPosY(), ent.getPosZ() - mc.player.getPosZ());
      ms.rotate(mc.getRenderManager().getCameraOrientation());
      ms.scale(-0.05f, -0.05f, 0.05f);

      int w = mc.fontRenderer.getStringWidth(display);
      mc.fontRenderer.drawStringWithShadow(ms, display, -w/2, 0, 0xeeeeee);

      ms.pop();
    }
  }
}
