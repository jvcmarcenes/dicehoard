package io.github.jvcmarcenes.dicehoard.init;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DSix;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<DSix> D6 = ITEMS.register("d_six", () -> 
        new DSix(new Item.Properties().group(Main.ITEM_GROUP).maxStackSize(1))
    );

}
