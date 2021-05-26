package io.github.jvcmarcenes.dicehoard.dices;

import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class DTwentyEntity extends DiceEntity {
 
  public DTwentyEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public DTwentyEntity(World world, LivingEntity entity) {
    super(ModEntityTypes.DTWENTY.get(), entity, world);
  }

  public DTwentyEntity(World world, int x, int y, int z) {
    super(ModEntityTypes.DTWENTY.get(), x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return ModItems.DTWENTY.get();
  }

  @Override
  protected int getMaxRoll() {
    return 20;
  }

}
