package io.github.jvcmarcenes.dicehoard.init;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DEightEntity;
import io.github.jvcmarcenes.dicehoard.dices.DFourEntity;
import io.github.jvcmarcenes.dicehoard.dices.DSixEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTenEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwelveEntity;
import io.github.jvcmarcenes.dicehoard.dices.DTwentyEntity;
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
  public static final RegistryObject<EntityType<DFourEntity>> DFOUR = ENTITY_TYPES.register("dfour", () -> 
    EntityType.Builder.<DFourEntity>create(DFourEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dfour")
  );
  public static final RegistryObject<EntityType<DEightEntity>> DEIGHT = ENTITY_TYPES.register("deight", () -> 
    EntityType.Builder.<DEightEntity>create(DEightEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dsix")
  );
  public static final RegistryObject<EntityType<DTenEntity>> DTEN = ENTITY_TYPES.register("dten", () -> 
    EntityType.Builder.<DTenEntity>create(DTenEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dfour")
  );
  public static final RegistryObject<EntityType<DTwelveEntity>> DTWELVE = ENTITY_TYPES.register("dtwelve", () -> 
    EntityType.Builder.<DTwelveEntity>create(DTwelveEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dsix")
  );
  public static final RegistryObject<EntityType<DTwentyEntity>> DTWENTY = ENTITY_TYPES.register("dtwenty", () -> 
    EntityType.Builder.<DTwentyEntity>create(DTwentyEntity::new, EntityClassification.MISC)
      .size(0.25f, 0.25f).trackingRange(4).func_233608_b_(10).build("dfour")
  );

}
