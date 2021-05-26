package io.github.jvcmarcenes.dicehoard.client;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.client.renderer.DiceRenderer;
import io.github.jvcmarcenes.dicehoard.dices.DEightEntity;
import io.github.jvcmarcenes.dicehoard.dices.DFourEntity;
import io.github.jvcmarcenes.dicehoard.dices.DSixEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTenEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwelveEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwentyEntity;
import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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
    }, ModItems.DFOUR.get(), ModItems.DSIX.get(), ModItems.DEIGHT.get(), ModItems.DTEN.get(), ModItems.DTWELVE.get(), ModItems.DTWENTY.get());
  }

  @SubscribeEvent
  public static void clientSetup(FMLClientSetupEvent event) {
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DFOUR.get(), new IRenderFactory<DFourEntity>() {
      @Override
      public EntityRenderer<? super DFourEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/dfour");
      }
    });
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DSIX.get(), new IRenderFactory<DSixEntity>() {
      @Override
      public EntityRenderer<? super DSixEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/dsix");
      }
    });
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DEIGHT.get(), new IRenderFactory<DEightEntity>() {
      @Override
      public EntityRenderer<? super DEightEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/deight");
      }
    });
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DTEN.get(), new IRenderFactory<DTenEntity>() {
      @Override
      public EntityRenderer<? super DTenEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/dten");
      }
    });
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DTWELVE.get(), new IRenderFactory<DTwelveEntity>() {
      @Override
      public EntityRenderer<? super DTwelveEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/dtwelve");
      }
    });
    RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DTWENTY.get(), new IRenderFactory<DTwentyEntity>() {
      @Override
      public EntityRenderer<? super DTwentyEntity> createRenderFor(EntityRendererManager manager) {
        return new DiceRenderer(manager, "entity/dtwenty");
      }
    });
  }

  @SubscribeEvent
  public static void modelRegistry(ModelRegistryEvent event) {
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/dfour"));
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/dsix"));
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/deight"));
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/dten"));
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/dtwelve"));
    ModelLoader.addSpecialModel(new ResourceLocation(Main.MOD_ID, "entity/dtwenty"));
  }

}
