package io.github.jvcmarcenes.dicehoard;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.jvcmarcenes.dicehoard.client.ClientEventHandler;
import io.github.jvcmarcenes.dicehoard.init.*;

@Mod(Main.MOD_ID)
public class Main {

  public static final Logger LOGGER = LogManager.getLogger();
  public static final String MOD_ID = "dicehoard";

  public Main() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ModItems.ITEMS.register(modEventBus);
    ModContainerTypes.CONTAINER_TYPES.register(modEventBus);

    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(ClientEventHandler.class);
  }

  public static final ItemGroup ITEM_GROUP = new ItemGroup("dicehoard"){
    public ItemStack createIcon() {
      return new ItemStack(Items.DIAMOND);
    };
  };
}
