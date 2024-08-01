package net.happyspeed.glidelytra.mixin;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FireworkRocketEntity.class, priority = 500)
abstract class FireworkRocketEntityMixin extends ProjectileEntity
		implements FlyingItemEntity {

	@Shadow private @Nullable LivingEntity shooter;

	@Shadow private int life;

	@Unique private int boostHeight;

	@Unique private boolean hadSetHeight = false;

	public FireworkRocketEntityMixin(EntityType<? extends ProjectileEntity> entityType, World world) {
		super(entityType, world);
	}
	@Unique
	public double boostCode(double constant) {
		if (!this.hadSetHeight) {
			if (this.shooter != null) {
				this.boostHeight = (int) this.shooter.getY();
			}
			this.hadSetHeight = true;
        }
		float boost = (float) ModConfigs.CONFIGFIREWORKBOOSTAMPLIFIER;
		if (this.shooter != null) {
			ItemStack itemStack;
			if (shooter.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.FIREWORK_ROCKET) {
				itemStack = this.shooter.getEquippedStack(EquipmentSlot.MAINHAND);
			}
			else {
				itemStack = this.shooter.getEquippedStack(EquipmentSlot.OFFHAND);
			}
			NbtCompound nbtCompound = itemStack.isEmpty() ? null : itemStack.getSubNbt("Fireworks");
			NbtList nbtList = nbtCompound != null ? nbtCompound.getList("Explosions", NbtElement.COMPOUND_TYPE) : null;
			int f = 0;
			if (!itemStack.isEmpty() && itemStack.hasNbt()) {
				f += itemStack.getOrCreateSubNbt("Fireworks").getByte("Flight");
			}
			if (nbtList != null && !nbtList.isEmpty()) {
				for (int i = 0; i < nbtList.size(); i++) {
					if (f == 3) {
						boost += 0.4f;
					}
					else if (f == 2) {
						boost += 0.5f;
					}
					else {
						boost += 0.6f;
					}
				}
			}
			if (this.boostHeight < 90) {
				boost += (100 - Math.max(64, this.boostHeight)) * 0.02f;
			}
		}
		return boost;
	}

	@ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1.5, ordinal = 1))
	public double modifyfirstboostconst(double constant) {
		return boostCode(constant);
    }
	@ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1.5, ordinal = 2))
	public double modifysecondboostconst(double constant) {
		return boostCode(constant);
	}
	@ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1.5, ordinal = 3))
	public double modifythirdboostconst(double constant) {
		return boostCode(constant);
	}
}