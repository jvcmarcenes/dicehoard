package io.github.jvcmarcenes.dicehoard.dices;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class DiceEntity extends BouncingEntity {

  private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(DiceEntity.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> ROLLED_NUMBER = EntityDataManager.createKey(DiceEntity.class, DataSerializers.VARINT);

  public DiceEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public DiceEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity entity, World world) {
    super(type, entity, world);
  }

  public DiceEntity(EntityType<? extends ProjectileItemEntity> type, int x, int y, int z, World world) {
    super(type, x, y, z, world);
  }

  public int getRolledNumber() {
    return this.dataManager.get(ROLLED_NUMBER);
  }

  public int getColor() {
    return this.dataManager.get(COLOR);
  }

  @Override
  public void setItem(ItemStack stack) {
    super.setItem(stack);

    int color = stack.getTag().getInt("color");
    this.dataManager.set(COLOR, color);
  }
  
  @Override
  protected void onLanded() {
    int rolled = world.rand.nextInt(getMaxRoll()) + 1;
    this.dataManager.set(ROLLED_NUMBER, rolled);
  }

  @Override
  protected void registerData() {
    super.registerData();

    this.dataManager.register(COLOR, -1);
    this.dataManager.register(ROLLED_NUMBER, -1);
  }

  @Override public boolean canBeCollidedWith() { return true; }
  // @Override public boolean canBePushed() { return true; }
  @Override public boolean canBeAttackedWithItem() { return true; }
  @Override public boolean canSwim() { return true; }

  @Override
  public boolean hitByEntity(Entity entity) {

    Vector3d dir = this.getPositionVec()
      .subtract(entity.getPositionVec().add(0.0, entity.getEyeHeight(), 0.0))
      .normalize()
      .scale(0.35);

    // this.setMotion(dir.x, dir.y < 0 ? -dir.y : dir.y, dir.z);
    this.setMotion(dir);

    return super.hitByEntity(entity);
  }

  protected abstract int getMaxRoll();

}
