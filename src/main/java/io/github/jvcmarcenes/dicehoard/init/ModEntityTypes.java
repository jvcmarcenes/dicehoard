package io.github.jvcmarcenes.dicehoard.init;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DSixEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
  
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

  public static final RegistryObject<EntityType<DSixEntity>> DSIX = ENTITY_TYPES.register("dsix", () -> 
    EntityType.Builder.<DSixEntity>create(DSixEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dsix")
  );

}
