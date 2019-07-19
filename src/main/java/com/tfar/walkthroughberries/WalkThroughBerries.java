package com.tfar.walkthroughberries;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WalkThroughBerries.MODID)
public class WalkThroughBerries {

  public static final String MODID = "walkthroughberries";

  public WalkThroughBerries() {
    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onInsideBlock(LivingAttackEvent event) {
    LivingEntity entity = event.getEntityLiving();
    if (!(entity instanceof PlayerEntity)) return;
    PlayerEntity player = (PlayerEntity) entity;
    if (event.getSource().damageType.equals("sweetBerryBush")) {
      if (player.isSneaking()) {
        event.setCanceled(true);
      } else {
        ItemStack pants = player.inventory.armorInventory.get(1);
        ItemStack shoes = player.inventory.armorInventory.get(0);
        if (pants.getItem() instanceof ArmorItem && shoes.getItem() instanceof ArmorItem) event.setCanceled(true);
      }
    }
  }
}

