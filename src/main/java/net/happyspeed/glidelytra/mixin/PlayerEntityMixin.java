package net.happyspeed.glidelytra.mixin;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.fabricmc.loader.api.FabricLoader;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.access.ElytraItemClassAccess;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntity.class, priority = 501)
abstract class PlayerEntityMixin extends LivingEntity {

    @Unique
    int tickCount = 0;

    @Unique
    int damageTickCount = 0;

    @Unique
    boolean damagedAnElytra = false;

    @Unique
    boolean elytraEnabled = true;

    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow
    public abstract void tickMovement();

    @Shadow
    public abstract void stopFallFlying();

    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Unique
    public void tickYourElytras(ItemStack stack, LivingEntity living) {
        this.elytraEnabled = !(this.getItemCooldownManager().getCooldownProgress(stack.getItem(), 1.0f) > 0.0);
        if (!this.elytraEnabled) {
            this.stopFallFlying();
        }
        if (living instanceof PlayerEntity player && !player.getWorld().isClient() ) {
            if (player.isFallFlying() && this.damageTickCount > 29) {
                this.damagedAnElytra = false;
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (!(player.getInventory().getStack(i).getItem() == null) && !player.getInventory().getStack(i).isEmpty()) {
                        int da = 0;
                        int currentStackSlotE = player.getInventory().getSlotWithStack(player.getInventory().getStack(i));
                        if (player.getInventory().getStack(i).getItem() instanceof ElytraItem) {
                            if (player.getInventory().getStack(i).getDamage() + 3 < player.getInventory().getStack(i).getMaxDamage()) {
                                da = 3;
                            }
                            else if (player.getInventory().getStack(i).getDamage() + 1 < player.getInventory().getStack(i).getMaxDamage()) {
                                da = 1;
                            }
                        }
                        else if (player.getInventory().getStack(i).getItem() instanceof FabricElytraItem) {
                            if (player.getInventory().getStack(i).getDamage() + 1 < player.getInventory().getStack(i).getMaxDamage()) {
                                da = 1;
                            }
                        }
                        player.getInventory().getStack(i).damage(da, living, (w) -> w.sendEquipmentBreakStatus(EquipmentSlot.fromTypeIndex(EquipmentSlot.Type.ARMOR, currentStackSlotE)));
                        damagedAnElytra = true;
                    }
                }
                if (damagedAnElytra) {
                    this.damageTickCount = 0;
                }
            }

            if (!player.isFallFlying() && stack.getDamage() - 30 >= 0 && this.tickCount > 399) {
                stack.setDamage(stack.getDamage() - 30);
                Criteria.ITEM_DURABILITY_CHANGED.trigger((ServerPlayerEntity) player, stack, stack.getDamage() - 30);
                this.tickCount = 0;
            }
            else if (!player.isFallFlying() && !(stack.getDamage() - 30 >= 0) && this.tickCount > 399) {
                stack.setDamage(0);
                Criteria.ITEM_DURABILITY_CHANGED.trigger((ServerPlayerEntity) player, stack, 0);
                this.tickCount = 0;
            }
            if (this.tickCount < 400 && !player.isFallFlying()) {
                this.tickCount++;
            }
            if (this.damageTickCount < 30 && player.isFallFlying()) {
                this.damageTickCount++;
            }
        }
    }
    @Unique
    public void disableElytras(ItemStack stack, LivingEntity living) {
        if (living instanceof PlayerEntity player && !player.getWorld().isClient()) {
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).getItem() instanceof ElytraItem || player.getInventory().getStack(i).getItem().toString() == "elytra_armored" || player.getInventory().getStack(i).getItem().toString() == "elytra_crystalite") {
                    player.getItemCooldownManager().set(player.getInventory().getStack(i).getItem(), 300);
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void elytraTick(CallbackInfo ci) {
        if (!ModConfigs.CONFIGVANILLAELYTRADURRABILITY) {
            if (this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof ElytraItem || this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof FabricElytraItem) {
                this.tickYourElytras(this.getEquippedStack(EquipmentSlot.CHEST), this);
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"))
    public void elytraDisable(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfigs.CONFIGALLOWELYTRADISABLE) {
            if ((this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof ElytraItem || this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof FabricElytraItem) && (source.isIn(DamageTypeTags.IS_PROJECTILE) || source.isIn(DamageTypeTags.IS_EXPLOSION))) {
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 0.7f, 0.8f);
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, SoundCategory.PLAYERS, 0.7f, 0.8f);
                this.disableElytras(this.getEquippedStack(EquipmentSlot.CHEST), this);
                this.stopFallFlying();
            }
        }
    }

    @Redirect(method = "checkFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ElytraItem;isUsable(Lnet/minecraft/item/ItemStack;)Z"))
    public boolean checkFallflyRedirect(ItemStack stack) {
        if (stack.getItem() instanceof ElytraItem) {
            if (this.getItemCooldownManager().getCooldownProgress(stack.getItem(), 1.0f) > 0.1) {
                return false;
            } else {
                return ElytraItem.isUsable(stack);
            }
        }
        return false;
    }
}
