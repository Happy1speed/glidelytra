package net.happyspeed.glidelytra.mixin;

import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinForDrag extends Entity {

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    public LivingEntityMixinForDrag(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyArgs(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;multiply(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 2))
    private void modVelocity(Args args) {
        if (ModConfigs.CONFIGLESSELYTRADRAG && this.hasStatusEffect(GlidelytraMod.FAST_GLIDE_EFFECT)) {
            args.set(0, 0.996);
            args.set(1, 0.99);
            args.set(2, 0.996);
        }
        else if (ModConfigs.CONFIGLIGHTERELYTRA && ModConfigs.CONFIGLESSELYTRADRAG) {
            args.set(0, 0.991);
            args.set(1, 0.98);
            args.set(2, 0.991);
        }
    }
    @ModifyConstant(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", constant = @Constant(doubleValue = 0.75))
    public double gravTweak(double constant) {
        if (ModConfigs.CONFIGEXPERIMENTALSUPERELYTRAGRAVITY) {
            return 0.1;
        }
        else if (ModConfigs.CONFIGLIGHTERELYTRA) {
            return 0.88;
        }
        return constant;
    }
}
