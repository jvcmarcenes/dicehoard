package io.github.jvcmarcenes.dicehoard.dices;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DSixEntity extends ProjectileItemEntity {

  private boolean inGround;

  public DSixEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);
  }

  public DSixEntity(World world, LivingEntity entity) {
    super(ModEntityTypes.DSIX.get(), entity, world);
  }

  public DSixEntity(World world, int x, int y, int z) {
    super(ModEntityTypes.DSIX.get(), x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return ModItems.D6.get();
  }

  @Override
  public void tick() {

    BlockPos blockpos = this.getPosition();
    BlockState blockstate = this.world.getBlockState(blockpos);
    if (!blockstate.isAir(this.world, blockpos)) {
      VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
      if (!voxelshape.isEmpty()) {
        Vector3d vector3d1 = this.getPositionVec();

        for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
          if (axisalignedbb.offset(blockpos).contains(vector3d1)) {
            this.setMotion(0, 0, 0);
            break;
          }
        }
      }
    }
    
    if (Math.abs(getMotion().x) + Math.abs(getMotion().z) > .001) {
      this.setMotion(getMotion().mul(.96, 1, .96).add(0, -.07, 0));
      setRotation(rotationYaw + .5f, rotationPitch + .35f);
      this.setPosition(getPosX() + getMotion().x, getPosY() + getMotion().y, getPosZ() + getMotion().z);
    } else {
      this.setMotion(Vector3d.ZERO);
    }
  }

  @Override
  protected void onImpact(RayTraceResult result) {
    
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }

}
