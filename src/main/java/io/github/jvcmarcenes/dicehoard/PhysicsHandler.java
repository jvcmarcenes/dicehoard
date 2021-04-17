package io.github.jvcmarcenes.dicehoard;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

public class PhysicsHandler {

  private static PhysicsHandler instance = null;

  private static DynamicsWorld dynamicsWorld;
  private static List<RigidBody> dices = new ArrayList<>();

  public PhysicsHandler() {
    if (instance == null) setupPhysics();
    instance = this;
  }

  private static void setupPhysics() {
    BroadphaseInterface broadphase = new DbvtBroadphase();
    CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
    CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
    ConstraintSolver solver = new SequentialImpulseConstraintSolver();

    dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
    dynamicsWorld.setGravity(new Vector3f(0, -10, 0));

    CollisionShape groundShape = new StaticPlaneShape(new Vector3f(0, 1, 0), .25f);
    MotionState groundMotionState = new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 1, 0), 1)));
    RigidBodyConstructionInfo groundConstructionInfo = new RigidBodyConstructionInfo(0, groundMotionState, groundShape, new Vector3f(0, 0, 0));
    groundConstructionInfo.restitution = 0.3f;
    RigidBody groundRigidBody = new RigidBody(groundConstructionInfo);

    dynamicsWorld.addRigidBody(groundRigidBody);
  }

  // public void addRigidBody(RigidBody toAdd) {
  //   dynamicsWorld.addRigidBody(toAdd);
  // }

  public void addDice(RigidBody body) {
    dynamicsWorld.addRigidBody(body);
    dices.add(body);
  }

  private long baseTime = 0;

  public void tick() {
    if (dices.size() == 0) {
      baseTime = 0;
      return;
    }

    if (baseTime == 0) baseTime = System.currentTimeMillis();

    long curTime = System.currentTimeMillis();
    long deltaTime = curTime - baseTime;
    baseTime = curTime;

    dynamicsWorld.stepSimulation(deltaTime/1000f);
  }

  public static PhysicsHandler getInstance() {
    return instance;
  }
  
}
