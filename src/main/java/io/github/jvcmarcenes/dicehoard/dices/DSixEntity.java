package io.github.jvcmarcenes.dicehoard.dices;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

import io.github.jvcmarcenes.dicehoard.PhysicsHandler;
import io.github.jvcmarcenes.dicehoard.init.ModEntityTypes;
import io.github.jvcmarcenes.dicehoard.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DSixEntity extends ProjectileItemEntity {

  private RigidBody rb;
  // private Vector3f startPos;
  // private Vector3f startVel;

  public void createPhysicsObject(LivingEntity entity) {

    Vector3f startPos = new Vector3f((float)entity.getPosX(), (float)entity.getPosY() + entity.getHeight()/1.8f, (float)entity.getPosZ());

    Vector2f rot = entity.getPitchYaw();
    Vector3f startVel = new Vector3f((float)Math.cos(rot.y) * (float)Math.cos(rot.x), (float)Math.sin(rot.y) * (float)Math.cos(rot.x), (float)Math.sin(rot.x));
    startVel.scale(5f);

    CollisionShape shape = new SphereShape(0.3f);
    MotionState motion = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), startPos, 1)));
    Vector3f inertia = new Vector3f(0, 0, 0);
    shape.calculateLocalInertia(2.5f, inertia);
    RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(1, motion, shape, inertia);
    info.restitution = 0.5f;
    info.friction = 0.8f;
    info.angularDamping = 0.8f;
    rb = new RigidBody(info);
    rb.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
    rb.setLinearVelocity(startVel);

    PhysicsHandler.getInstance().addDice(rb);
  }

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
    if (rb == null) return;

    Vector3f pos = rb.getWorldTransform(new Transform()).origin;
    Quat4f q = rb.getWorldTransform(new Transform()).getRotation(new Quat4f());

    float yaw = (float)Math.atan2(2 * (q.y * q.z + q.w * q.x), q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z);
    float pitch = (float)Math.asin(-2 * (q.x * q.z - q.w * q.y));

    this.setPosition(pos.x, pos.y, pos.z);
    this.setRotation(yaw, pitch);
  }

  @Override
  protected void onImpact(RayTraceResult result) {
    
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }

}
