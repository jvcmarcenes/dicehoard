package io.github.jvcmarcenes.dicehoard.dices;

import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DSixEntity extends ProjectileItemEntity {

  // private Vector3f startPos;
  // private Vector3f startVel;

  public DSixEntity(EntityType<? extends ProjectileItemEntity> type, World world) {
    super(type, world);

    // startPos = new Vector3f(0, 0, 0);
    // startVel = new Vector3f(0, 0, 0);

    // createPhysicsObject();
  }

  public DSixEntity(World world, LivingEntity entity) {
    super(ModEntityTypes.DSIX.get(), entity, world);

    // startPos = new Vector3f((float)entity.getPosX(), (float)entity.getPosY() + entity.getHeight()/1.8f, (float)entity.getPosZ());

    // Vector2f rot = entity.getPitchYaw();
    // startVel = new Vector3f((float)Math.cos(rot.y) * (float)Math.cos(rot.x), (float)Math.sin(rot.y) * (float)Math.cos(rot.x), (float)Math.sin(rot.x));
    // startVel.scale(2f);

    // createPhysicsObject();
  }

  public DSixEntity(World world, int x, int y, int z) {
    super(ModEntityTypes.DSIX.get(), x, y, z, world);

    // startPos = new Vector3f(x, y, z);
    // startVel = new Vector3f(0, 0, 0);

    // createPhysicsObject();
  }

  @Override
  protected Item getDefaultItem() {
    return ModItems.D6.get();
  }

  @Override
  public void tick() {
  }

  @Override
  protected void onImpact(RayTraceResult result) {
    
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }

}
