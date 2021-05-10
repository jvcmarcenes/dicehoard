package io.github.jvcmarcenes.dicehoard.dices;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class DiceEntity extends BouncingEntity {

  // private static final String ROLLED_TAG = "rolled";
  //private static final String COLOR_TAG = "color";

  private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(DiceEntity.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> ROLLED_NUMBER = EntityDataManager.createKey(DiceEntity.class, DataSerializers.VARINT);

  // protected int rolledNumber = -1;
  //protected int color = -1;

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
    // return rolledNumber;
    return this.dataManager.get(ROLLED_NUMBER);
  }

  public int getColor() {
    // return color;
    return this.dataManager.get(COLOR);
  }

  @Override
  public void setItem(ItemStack stack) {
    super.setItem(stack);

    int color = stack.getTag().getInt("color");
    this.dataManager.set(COLOR, color);

    // color = stack.getTag().getInt("color");
  }

  @Override
  public void tick() {
    super.tick();

    if (isLanded() && getRolledNumber() == -1) {
      int rolled = world.rand.nextInt(getMaxRoll()) + 1;
      this.dataManager.set(ROLLED_NUMBER, rolled);
    }

    // if (isLanded() && rolledNumber == -1)
    //   rolledNumber = world.rand.nextInt(getMaxRoll()) + 1;
  }

  @Override
  protected void registerData() {
    super.registerData();

    this.dataManager.register(COLOR, -1);
    this.dataManager.register(ROLLED_NUMBER, -1);
  }

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    // compound.putInt(ROLLED_TAG, rolledNumber);
    // compound.putInt(COLOR_TAG, color);
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    // rolledNumber = compound.getInt(ROLLED_TAG);
    // color = compound.getInt(COLOR_TAG);
  }

  protected abstract int getMaxRoll();
  
}