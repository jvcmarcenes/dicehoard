package io.github.jvcmarcenes.dicehoard.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.jvcmarcenes.dicehoard.Main;
import io.github.jvcmarcenes.dicehoard.dices.DiceEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;

public class DiceRenderer extends EntityRenderer<DiceEntity> {

  private final String modelLoc;

  public DiceRenderer(EntityRendererManager renderManager, String modelLoc) {
    super(renderManager);
    this.modelLoc = modelLoc;
  }

  @Override
  public ResourceLocation getEntityTexture(DiceEntity entity) {
    return null;
  }

  @Override
  public void render(DiceEntity entity, float yaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
    
    // TODO move rolledNumber rendering to here instead of WorldRenderLast to stop jittering?
    IBakedModel bakedModel = Minecraft.getInstance().getModelManager().getModel(new ResourceLocation(Main.MOD_ID, modelLoc));
    Direction[] dirs = new Direction[]{ Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN, null };

    for (Direction dir : dirs)
      for (BakedQuad quad : bakedModel.getQuads(null, dir, entity.world.rand, EmptyModelData.INSTANCE)) {

        int color = entity.getColor();

        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;

        buffer
          .getBuffer(RenderType.getEntitySolid(AtlasTexture.LOCATION_BLOCKS_TEXTURE))
          .addQuad(matrixStack.getLast(), quad, red, green, blue, packedLight, OverlayTexture.NO_OVERLAY);
      }
  }
  
}
