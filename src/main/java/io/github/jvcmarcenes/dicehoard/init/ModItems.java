package io.github.jvcmarcenes.dicehoard.init;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DEightEntity;
import io.github.jvcmarcenes.dicehoard.dices.DFourEntity;
import io.github.jvcmarcenes.dicehoard.dices.DSixEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTenEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwelveEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwentyEntity;
import io.github.jvcmarcenes.dicehoard.dices.DiceItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<DiceItem> DFOUR = ITEMS.register("dfour", () -> new DiceItem(def(), DFourEntity::new));
    public static final RegistryObject<DiceItem> DSIX = ITEMS.register("dsix", () -> new DiceItem(def(), DSixEntity::new));
    public static final RegistryObject<DiceItem> DEIGHT = ITEMS.register("deight", () -> new DiceItem(def(), DEightEntity::new));
    public static final RegistryObject<DiceItem> DTEN = ITEMS.register("dten", () -> new DiceItem(def(), DTenEntity::new));
    public static final RegistryObject<DiceItem> DTWELVE = ITEMS.register("dtwelve", () -> new DiceItem(def(), DTwelveEntity::new));
    public static final RegistryObject<DiceItem> DTWENTY = ITEMS.register("dtwenty", () -> new DiceItem(def(), DTwentyEntity::new));

    private static final Item.Properties def() {
        return new Item.Properties().group(Main.ITEM_GROUP).maxStackSize(16);
    }

}
