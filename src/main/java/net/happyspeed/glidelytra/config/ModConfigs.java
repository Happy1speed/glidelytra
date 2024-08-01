package net.happyspeed.glidelytra.config;

import com.mojang.datafixers.util.Pair;
import net.happyspeed.glidelytra.GlidelytraMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean CONFIGFIREWORKCOOLDOWN;
    public static boolean CONFIGRELYTRAREGEN;
    public static boolean CONFIGALLOWELYTRADISABLE;
    public static int CONFIGFIREWORKCOOLDOWNBASE;
    public static int CONFIGFIREWORKCOOLDOWNMODIFIER;
    public static double CONFIGFIREWORKBOOSTAMPLIFIER;
    public static boolean CONFIGVANILLAELYTRADURRABILITY;
    public static boolean CONFIGLIGHTERELYTRA;
    public static boolean CONFIGLESSELYTRADRAG;
    public static boolean CONFIGEXPERIMENTALSUPERELYTRAGRAVITY;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(GlidelytraMod.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("firework_cooldown", true));
        configs.addKeyValuePair(new Pair<>("elytra_regeneration", true));
        configs.addKeyValuePair(new Pair<>("allow_elytra_disable", true));
        configs.addKeyValuePair(new Pair<>("firework_cooldown_base", 240));
        configs.addKeyValuePair(new Pair<>("firework_cooldown_modifier", 140));
        configs.addKeyValuePair(new Pair<>("firework_boost_amplifier", 1.6));
        configs.addKeyValuePair(new Pair<>("less_elytra_drag", true));
        configs.addKeyValuePair(new Pair<>("lighter_elytra", true));
        configs.addKeyValuePair(new Pair<>("experimental_super_elytra_gravity", false));
        configs.addKeyValuePair(new Pair<>("vanilla_elytra_durrability_behavior", false));
    }

    private static void assignConfigs() {
        CONFIGFIREWORKCOOLDOWN = CONFIG.getOrDefault("firework_cooldown", true);
        CONFIGRELYTRAREGEN = CONFIG.getOrDefault("elytra_regeneration", true);
        CONFIGALLOWELYTRADISABLE = CONFIG.getOrDefault("allow_elytra_disable", true);
        CONFIGFIREWORKCOOLDOWNBASE = CONFIG.getOrDefault("firework_cooldown_base", 240);
        CONFIGFIREWORKCOOLDOWNMODIFIER = CONFIG.getOrDefault("firework_cooldown_modifier", 140);
        CONFIGFIREWORKBOOSTAMPLIFIER = CONFIG.getOrDefault("firework_boost_amplifier", 1.6);
        CONFIGLESSELYTRADRAG = CONFIG.getOrDefault("less_elytra_drag", true);
        CONFIGLIGHTERELYTRA = CONFIG.getOrDefault("lighter_elytra", true);
        CONFIGEXPERIMENTALSUPERELYTRAGRAVITY = CONFIG.getOrDefault("experimental_super_elytra_gravity", false);
        CONFIGVANILLAELYTRADURRABILITY = CONFIG.getOrDefault("vanilla_elytra_durrability_behavior", false);


        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}