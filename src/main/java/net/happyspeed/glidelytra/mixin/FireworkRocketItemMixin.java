package net.happyspeed.glidelytra.mixin;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FireworkRocketItem.class)
public class FireworkRocketItemMixin extends Item {
    public FireworkRocketItemMixin(Settings settings) {
        super(settings);
    }
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    public void cancelUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (!ModConfigs.CONFIGFIREWORKBOOSTINGENABLED && user.isFallFlying()) {
            cir.setReturnValue(TypedActionResult.pass(user.getStackInHand(hand)));
            cir.cancel();
        }
    }
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    public void stackedCooldown(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (ModConfigs.CONFIGFIREWORKCOOLDOWN) {
            if (user.isFallFlying()) {
                ItemStack heldRocket;
                if (hand == Hand.MAIN_HAND) {
                    heldRocket = user.getEquippedStack(EquipmentSlot.MAINHAND);
                } else {
                    heldRocket = user.getEquippedStack(EquipmentSlot.OFFHAND);
                }
                int f = 0;
                if (!heldRocket.isEmpty() && heldRocket.hasNbt()) {
                    f += heldRocket.getOrCreateSubNbt("Fireworks").getByte("Flight");
                }
                int cooldownAmount = ModConfigs.CONFIGFIREWORKCOOLDOWNBASE;
                for (int i = 0; i < f; i++) {
                    cooldownAmount += ModConfigs.CONFIGFIREWORKCOOLDOWNMODIFIER;
                }
                for (int i = 0; i < user.getInventory().size(); i++) {
                    if (user.getInventory().getStack(i).isOf(Items.FIREWORK_ROCKET)) {
                        user.getItemCooldownManager().set(user.getInventory().getStack(i).getItem(), cooldownAmount);
                    }
                }
            }
        }
    }
}
