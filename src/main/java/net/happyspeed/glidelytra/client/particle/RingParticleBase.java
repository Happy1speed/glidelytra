package net.happyspeed.glidelytra.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

@Environment(value= EnvType.CLIENT)
public class RingParticleBase
        extends AnimatedParticle {
    RingParticleBase(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.0f);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale = 4.0f;
        this.maxAge = 25;
        if (velocityY == 1) {
            //cyan
            this.setColor(0.086f, 0.769f, 0.753f);
        }
        else if (velocityY == 2) {
            //red
            this.setColor(0.871f, 0.153f, 0.114f);
        }
        else if (velocityY == 3) {
            //green
            this.setColor(0.114f, 0.549f, 0.184f);
        }
        else if (velocityY == 4) {
            //blue
            this.setColor(0.11f, 0.239f, 0.749f);
        }
        else if (velocityY == 5) {
            //light_blue
            this.setColor(0.165f, 0.925f, 0.929f);
        }
        else if (velocityY == 6) {
            //purple
            this.setColor(0.522f, 0.157f, 0.78f);
        }
        else if (velocityY == 7) {
            //pink
            this.setColor(0.945f, 0.651f, 0.98f);
        }
        else if (velocityY == 8) {
            //lime
            this.setColor(0.275f, 0.949f, 0.267f);
        }
        else if (velocityY == 9) {
            //yellow
            this.setColor(0.929f, 0.941f, 0.216f);
        }
        else if (velocityY == 10) {
            //gray
            this.setColor(0.522f, 0.522f, 0.522f);
        }
        else if (velocityY == 11) {
            //light gray
            this.setColor(0.722f, 0.722f, 0.722f);
        }
        else if (velocityY == 12) {
            //black
            this.setColor(0.149f, 0.149f, 0.149f);
        }
        else if (velocityY == 13) {
            //magenta
            this.setColor(0.902f, 0.145f, 0.82f);
        }
        else if (velocityY == 14) {
            //brown
            this.setColor(0.631f, 0.306f, 0.106f);
        }
        else if (velocityY == 15) {
            //orange
            this.setColor(0.98f, 0.459f, 0.133f);
        }
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public void move(double dx, double dy, double dz) {
    }

    @Override
    public int getBrightness(float tint) {
        float f = ((float)this.age + tint) / (float)this.maxAge;
        f = MathHelper.clamp(f, 0.0f, 1.0f);
        int i = super.getBrightness(tint);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        if ((j += (int)(f * 15.0f * 16.0f)) > 240) {
            j = 240;
        }
        return j | k << 16;
    }

    @Environment(EnvType.CLIENT)
    public static class RingFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public RingFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new RingParticleBase(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
