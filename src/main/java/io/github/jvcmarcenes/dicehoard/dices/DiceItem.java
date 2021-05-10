package io.github.jvcmarcenes.dicehoard.dices;

import java.util.function.BiFunction;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class DiceItem extends Item {

  private final BiFunction<World, PlayerEntity, DiceEntity> factory;

  public DiceItem(Properties props, BiFunction<World, PlayerEntity, DiceEntity> factory) {
    super(props);
    this.factory = factory;
  }

  @Override
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    ItemStack stack = player.getHeldItem(hand);

    if (!world.isRemote) {
      DiceEntity ent = factory.apply(world, player);
      ent.setItem(stack);
      ent.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0f, 1f, 0.0f);
      world.addEntity(ent);
    }

    if (!player.isCreative())
      stack.shrink(1);

    return ActionResult.resultSuccess(stack);
  }
  
  @Override
  public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    if (!isInGroup(group)) return;

    items.add(colorStack(0xd1d1d1));
    items.add(colorStack(0x940a0a));
    items.add(colorStack(0x0f3587));
    items.add(colorStack(0x226e26));
    items.add(colorStack(0x616161));
    items.add(colorStack(0x159299));
    items.add(colorStack(0x5a3d75));
    items.add(colorStack(0xc46d16));
  }

  private ItemStack colorStack(int color) {
    ItemStack stack = new ItemStack(this);
    CompoundNBT tag = new CompoundNBT();
    tag.putInt("color", color);
    stack.setTag(tag);
    return stack;
  }
}