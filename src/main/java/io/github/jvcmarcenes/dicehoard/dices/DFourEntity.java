package io.github.jvcmarcenes.dicehoard.dices;

import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class DFourEntity extends DiceEntity {

  public DFourEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public DFourEntity(World world, LivingEntity entity) {
    super(ModEntityTypes.DSIX.get(), entity, world);
  }

  public DFourEntity(World world, int x, int y, int z) {
    super(ModEntityTypes.DSIX.get(), x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return ModItems.DFOUR.get();
  }

  @Override
  protected int getMaxRoll() {
    return 4;
  }
}
