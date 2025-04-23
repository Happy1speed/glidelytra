package net.happyspeed.glidelytra.mixin;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.happyspeed.glidelytra.config.ModConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 900)
abstract public class LivingEntityMixin extends Entity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean isFallFlying();

    @Unique float bumpAmount = 0.09f;

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

    @Inject(method = "tick", at=@At(value = "HEAD"))
    public void raycastGroundBounce(CallbackInfo ci) {
        if (!this.getWorld().isClient()) {
            if (this.hasStatusEffect(GlidelytraMod.SKIPPING_EFFECT) && this.isFallFlying() && !this.isSneaking()) {
                Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
                float maxDistance = 2;
                BlockPos position = this.getBlockPos();
                for (int i = 0; i <= maxDistance; i++) {
                    BlockState state = this.getWorld().getBlockState(new BlockPos(this.getBlockX(), position.getY() - i, this.getBlockZ()));
                    if (!state.isAir()) {
                        float distanceFloat = (float) (vec3d.getY() - this.getY() - i);
                        bumpAmount += distanceFloat * 0.1f;
                        float velocity = (float) Math.min(3, Math.max(0, (2 - bumpAmount)));
                        Vec3d counterGravity = new Vec3d(this.getVelocity().getX(), Math.min(0.5, this.getVelocity().getY() + velocity), this.getVelocity().getZ());
                        velocityModified = true;
                        this.fallDistance = 0.0f;
                        this.setVelocity(counterGravity);
                        break;
                    }
                }
            }
            bumpAmount = 0.09f;
        }
    }
}
