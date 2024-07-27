package net.happyspeed.glidelytra.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.happyspeed.glidelytra.GlidelytraMod;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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

    public static float calculateAngle(double z1, double x1, double z2, double x2) {
        double angle = Math.toDegrees(Math.atan2(x2 - x1, z2 - z1));
        angle = (angle + 360) % 360;
        return (float) angle;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        if (camera.getPos().distanceTo(new Vec3d(this.x, this.y, this.z)) > 4) {
            Vec3d vec3d = camera.getPos();
            float f = (float) (MathHelper.lerp((double) tickDelta, this.prevPosX, this.x) - vec3d.getX());
            float g = (float) (MathHelper.lerp((double) tickDelta, this.prevPosY, this.y) - vec3d.getY());
            float h = (float) (MathHelper.lerp((double) tickDelta, this.prevPosZ, this.z) - vec3d.getZ());
            Vector3f[] vector3fs = new Vector3f[]{new Vector3f(-1.0f, -1.0f, 0.0f), new Vector3f(-1.0f, 1.0f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f), new Vector3f(1.0f, -1.0f, 0.0f)};
            float i = this.getSize(tickDelta);
            for (int j = 0; j < 4; ++j) {
                Vector3f vector3f = vector3fs[j];
                vector3f.rotateY((float) Math.toRadians(calculateAngle(vec3d.z, vec3d.x, this.z, this.x)));
                vector3f.mul(i);
                vector3f.add(f, g, h);
            }
            float k = this.getMinU();
            float l = this.getMaxU();
            float m = this.getMinV();
            float n = this.getMaxV();
            int o = this.getBrightness(tickDelta);
            vertexConsumer.vertex(vector3fs[0].x(), vector3fs[0].y(), vector3fs[0].z()).texture(l, n).color(this.red, this.green, this.blue, this.alpha).light(o).next();
            vertexConsumer.vertex(vector3fs[1].x(), vector3fs[1].y(), vector3fs[1].z()).texture(l, m).color(this.red, this.green, this.blue, this.alpha).light(o).next();
            vertexConsumer.vertex(vector3fs[2].x(), vector3fs[2].y(), vector3fs[2].z()).texture(k, m).color(this.red, this.green, this.blue, this.alpha).light(o).next();
            vertexConsumer.vertex(vector3fs[3].x(), vector3fs[3].y(), vector3fs[3].z()).texture(k, n).color(this.red, this.green, this.blue, this.alpha).light(o).next();
        }
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
