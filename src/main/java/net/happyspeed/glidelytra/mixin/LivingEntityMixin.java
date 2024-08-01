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
}
