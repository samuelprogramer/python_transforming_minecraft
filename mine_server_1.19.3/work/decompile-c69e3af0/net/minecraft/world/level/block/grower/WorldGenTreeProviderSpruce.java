package net.minecraft.world.level.block.grower;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.WorldGenFeatureConfigured;

public class WorldGenTreeProviderSpruce extends WorldGenMegaTreeProvider {

    public WorldGenTreeProviderSpruce() {}

    @Override
    protected ResourceKey<WorldGenFeatureConfigured<?, ?>> getConfiguredFeature(RandomSource randomsource, boolean flag) {
        return TreeFeatures.SPRUCE;
    }

    @Override
    protected ResourceKey<WorldGenFeatureConfigured<?, ?>> getConfiguredMegaFeature(RandomSource randomsource) {
        return randomsource.nextBoolean() ? TreeFeatures.MEGA_SPRUCE : TreeFeatures.MEGA_PINE;
    }
}
