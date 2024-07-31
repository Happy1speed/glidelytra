package net.happyspeed.glidelytra.mixin;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = LivingEntity.class, priority = 900)
abstract public class LivingEntityMixin extends Entity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyArg(method = "tickFallFlying", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V"), index = 0)
    private int cancelDamageElytraNatural(int damage) {
        if (!ModConfigs.CONFIGVANILLAELYTRADURRABILITY) {
            return 0;
        }
        else {
            return damage;
        }
    }

    @ModifyArgs(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;multiply(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 2))
    private void modVelocity(Args args) {
        if (ModConfigs.CONFIGLESSELYTRADRAG) {
            if (this.hasStatusEffect(GlidelytraMod.FAST_GLIDE_EFFECT)) {
                args.set(0, 0.994);
                args.set(1, 0.98);
                args.set(2, 0.994);
            }
        }
    }

//    @WrapOperation(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 6))
//    private void modDrag(LivingEntity instance, Vec3d vec3d, Operation<Void> original) {
//        if (ModConfigs.CONFIGLESSELYTRADRAG) {
//            //this.setVelocity(vec3d.multiply(0.99f, 0.98f, 0.99f));
//            GlidelytraMod.LOGGER.info("GO");
//        }
//        else {
//            original.call(instance, vec3d);
//        }
//    }

//    @ModifyArg(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 6))
//    private Vec3d lessDrag(Vec3d vector){
////        Vec3d velo = vector.multiply(0.99f, 0.98f, 0.99f);
////        if (velo != null)
////            return velo;
////        else
////            return vector;
//        return vector;
//    }
}
