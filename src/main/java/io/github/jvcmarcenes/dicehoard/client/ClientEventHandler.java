package io.github.jvcmarcenes.dicehoard.client;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
  
  @SubscribeEvent
  public static void registerItemColors(ColorHandlerEvent.Item event) {
    event.getItemColors().register(ModItems.D6.get(), ModItems.D6.get());
  }

  @SubscribeEvent
  public static void clientSetup(FMLClientSetupEvent event) {
    
  }

}
