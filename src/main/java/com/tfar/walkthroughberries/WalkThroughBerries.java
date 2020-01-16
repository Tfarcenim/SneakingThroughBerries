package com.tfar.walkthroughberries;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WalkThroughBerries.MODID)
public class WalkThroughBerries {

  public static final String MODID = "walkthroughberries";

  public WalkThroughBerries() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInsideBlock);
  }

  private void onInsideBlock(LivingAttackEvent event) {
    LivingEntity entity = event.getEntityLiving();
    if (!(entity instanceof ServerPlayerEntity)) return;
    ServerPlayerEntity player = (ServerPlayerEntity) entity;
    if (event.getSource().damageType.equals("sweetBerryBush")) {
      if (player.isCrouching()) {
        event.setCanceled(true);
      } else {
        ItemStack pants = player.inventory.armorInventory.get(1);
        ItemStack shoes = player.inventory.armorInventory.get(0);
        if (pants.getItem() instanceof ArmorItem && shoes.getItem() instanceof ArmorItem) event.setCanceled(true);
      }
    }
  }
}

