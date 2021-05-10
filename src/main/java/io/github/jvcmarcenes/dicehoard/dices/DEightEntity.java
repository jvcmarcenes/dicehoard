package io.github.jvcmarcenes.dicehoard.dices;

import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class DEightEntity extends DiceEntity {

  public DEightEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public DEightEntity(World world, LivingEntity entity) {
    super(ModEntityTypes.DSIX.get(), entity, world);
  }

  public DEightEntity(World world, int x, int y, int z) {
    super(ModEntityTypes.DSIX.get(), x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return ModItems.DEIGHT.get();
  }

  @Override
  protected int getMaxRoll() {
    return 8;
  }

}
