package io.github.jvcmarcenes.dicehoard.dices;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DSixEntity extends Entity {

  public DSixEntity(EntityType<?> entityTypeIn, World worldIn) {
    super(entityTypeIn , worldIn);
  }

  public DSixEntity() {
    super(null, null); //TODO
  }

  @Override
  protected void registerData() {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void readAdditional(CompoundNBT compound) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void writeAdditional(CompoundNBT compound) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IPacket<?> createSpawnPacket() {
    return NetworkHooks.getEntitySpawningPacket(this);
  }
}
