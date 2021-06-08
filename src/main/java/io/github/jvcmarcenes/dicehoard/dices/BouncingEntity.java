package io.github.jvcmarcenes.dicehoard.dices;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class BouncingEntity extends ProjectileItemEntity {

  private static final String LANDED_TAG = "landed";

  protected boolean landed = false;

  public BouncingEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public BouncingEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity entity, World world) {
    super(type, entity, world);
  }

  public BouncingEntity(EntityType<? extends ProjectileItemEntity> type, int x, int y, int z, World world) {
    super(type, x, y, z, world);
  }

  @Override
  protected abstract Item getDefaultItem();

  @Override
  public void tick() {
    super.tick();

    if (Math.abs(getMotion().getY()) > 0.1 && landed) {
      landed = false;
    }
  }

  @Override
  protected void onImpact(RayTraceResult result) {
    if (!world.isRemote) {
      if (result.getType() == RayTraceResult.Type.BLOCK) {
        BlockRayTraceResult blockResult = (BlockRayTraceResult)result;
        Direction.Axis a = blockResult.getFace().getAxis();
        Vector3d m = this.getMotion();
        Vector3d _m = new Vector3d(m.getX() * (a == Direction.Axis.X ? -1 : 1), m.getY() * (a == Direction.Axis.Y ? -1 : 1), m.getZ() * (a == Direction.Axis.Z ? -1 : 1));
        this.setMotion(_m.scale(0.3));
      }
    }

    Vector3d m = this.getMotion();

    if (Math.abs(m.getY()) < 0.1) {
      this.setMotion(m.getX(), 0.0d, m.getZ());

      if (landed == false) {
        landed = true;
        this.onLanded();
      }
    } else {
      landed = false;
    }
  }

  public boolean isLanded() {
    return landed;
  }

  protected abstract void onLanded();

  @Override
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putBoolean(LANDED_TAG, landed);
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    landed = compound.getBoolean(LANDED_TAG);
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}
