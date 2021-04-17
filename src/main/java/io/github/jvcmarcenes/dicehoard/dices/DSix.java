package io.github.jvcmarcenes.dicehoard.dices;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class DSix extends Item {

  public DSix(Properties props) {
    super(props);
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    if (!world.isRemote) {
      DSixEntity ent = new DSixEntity(world, player);
      ent.setItem(stack);
      //ent.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0f, 1f, 0.0f);
      ent.createPhysicsObject(player);
      world.addEntity(ent);
    }

    if (!player.isCreative())
      stack.shrink(1);

    return ActionResult.resultSuccess(stack);
  }
  
  @Override
  public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    if (!isInGroup(group)) return;

    items.add(colorStack(0xffffff));
    items.add(colorStack(0xff0000));
    items.add(colorStack(0x00ff00));
    items.add(colorStack(0x0000ff));
  }

  private ItemStack colorStack(int color) {
    ItemStack stack = new ItemStack(this);
    CompoundNBT tag = new CompoundNBT();
    tag.putInt("color", color);
    stack.setTag(tag);
    return stack;
  }
}
