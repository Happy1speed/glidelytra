package net.happyspeed.glidelytra.access;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface ElytraItemClassAccess {
        void glidelytra$elytraTickRepair(ItemStack stack, LivingEntity living);
        void glidelytra$disableIt(ItemStack stack, LivingEntity living);
}
