package io.github.jvcmarcenes.dicehoard;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.jvcmarcenes.dicehoard.client.ClientEventHandler;
import io.github.jvcmarcenes.dicehoard.dices.DiceEntity;
import io.github.jvcmarcenes.dicehoard.init.*;

@Mod(Main.MOD_ID)
public class Main {

  public static final Logger LOGGER = LogManager.getLogger();
  public static final String MOD_ID = "dicehoard";

  public Main() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    ModItems.ITEMS.register(modEventBus);
    ModEntityTypes.ENTITY_TYPES.register(modEventBus);

    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.register(ClientEventHandler.class);
  }

  public static final ItemGroup ITEM_GROUP = new ItemGroup("dicehoard") {
    public ItemStack createIcon() {
      return ModItems.DTWELVE.get().colorStack(0x940a0a);
    };
  };

  @SubscribeEvent
  public void onEntityInteraction(PlayerInteractEvent.EntityInteract e) {
    if (e.getWorld().isRemote()) return;

    if (!(e.getTarget() instanceof DiceEntity)) return;
    if (e.getHand() == Hand.OFF_HAND) return;
    
    DiceEntity dice = (DiceEntity)e.getTarget();
    ItemStack diceItem = dice.getItem().copy();
    diceItem.setCount(1);

    dice.remove();
    
    e.getPlayer().addItemStackToInventory(diceItem);
  }
}
