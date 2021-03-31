package io.github.jvcmarcenes.dicehoard.client;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DSixEntity;
import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
  
  @SubscribeEvent
  public static void registerItemColors(ColorHandlerEvent.Item event) {
    event.getItemColors().register(new IItemColor() {
      @Override
      public int getColor(ItemStack stack, int tintIndex) {
        if (stack.isEmpty() || stack == null || !stack.hasTag()) return 0xff0000;
        return stack.getTag().getInt("color");
      }
    }, ModItems.D6.get());
  }

  @SubscribeEvent
  public static void clientSetup(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DSIX.get(), new IRenderFactory<DSixEntity>() {
      @Override
      public EntityRenderer<? super DSixEntity> createRenderFor(EntityRendererManager manager) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        return new SpriteRenderer<>(manager, itemRenderer);
      }
    });
  }

}
