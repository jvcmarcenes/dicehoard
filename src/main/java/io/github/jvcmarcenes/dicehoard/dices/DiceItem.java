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
      ItemStack diceStack = stack.copy();
      ent.setItem(diceStack);
      ent.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0f, .45f, 0.05f);
      world.addEntity(ent);
    }

    if (!player.isCreative())
      stack.shrink(1);

    return ActionResult.resultSuccess(stack);
  }
  
  @Override
  public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
    if (!isInGroup(group)) return;

    items.add(colorStack(0xd1d1d1)); // white
    items.add(colorStack(0x818181)); // gray
    items.add(colorStack(0x1d1d1d)); // black
    items.add(colorStack(0x940a0a)); // red
    items.add(colorStack(0xc46d16)); // orange
    items.add(colorStack(0x226e26)); // green
    items.add(colorStack(0x159299)); // cyan
    items.add(colorStack(0x0f3587)); // blue
    items.add(colorStack(0x5a3d75)); // magenta
  }

  public ItemStack colorStack(int color) {
    ItemStack stack = new ItemStack(this);
    CompoundNBT tag = new CompoundNBT();
    tag.putInt("color", color);
    stack.setTag(tag);
    return stack;
  }
}
