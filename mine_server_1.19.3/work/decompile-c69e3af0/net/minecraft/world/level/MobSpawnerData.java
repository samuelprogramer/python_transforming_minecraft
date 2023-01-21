package net.minecraft.world.level;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;

public record MobSpawnerData(NBTTagCompound entityToSpawn, Optional<MobSpawnerData.a> customSpawnRules) {

    public static final String ENTITY_TAG = "entity";
    public static final Codec<MobSpawnerData> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(NBTTagCompound.CODEC.fieldOf("entity").forGetter((mobspawnerdata) -> {
            return mobspawnerdata.entityToSpawn;
        }), MobSpawnerData.a.CODEC.optionalFieldOf("custom_spawn_rules").forGetter((mobspawnerdata) -> {
            return mobspawnerdata.customSpawnRules;
        })).apply(instance, MobSpawnerData::new);
    });
    public static final Codec<SimpleWeightedRandomList<MobSpawnerData>> LIST_CODEC = SimpleWeightedRandomList.wrappedCodecAllowingEmpty(MobSpawnerData.CODEC);

    public MobSpawnerData() {
        this(new NBTTagCompound(), Optional.empty());
    }

    public MobSpawnerData(NBTTagCompound nbttagcompound, Optional<MobSpawnerData.a> optional) {
        if (nbttagcompound.contains("id")) {
            MinecraftKey minecraftkey = MinecraftKey.tryParse(nbttagcompound.getString("id"));

            if (minecraftkey != null) {
                nbttagcompound.putString("id", minecraftkey.toString());
            } else {
                nbttagcompound.remove("id");
            }
        }

        this.entityToSpawn = nbttagcompound;
        this.customSpawnRules = optional;
    }

    public NBTTagCompound getEntityToSpawn() {
        return this.entityToSpawn;
    }

    public Optional<MobSpawnerData.a> getCustomSpawnRules() {
        return this.customSpawnRules;
    }

    public static record a(InclusiveRange<Integer> blockLightLimit, InclusiveRange<Integer> skyLightLimit) {

        private static final InclusiveRange<Integer> LIGHT_RANGE = new InclusiveRange<>(0, 15);
        public static final Codec<MobSpawnerData.a> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(InclusiveRange.INT.optionalFieldOf("block_light_limit", MobSpawnerData.a.LIGHT_RANGE).flatXmap(MobSpawnerData.a::checkLightBoundaries, MobSpawnerData.a::checkLightBoundaries).forGetter((mobspawnerdata_a) -> {
                return mobspawnerdata_a.blockLightLimit;
            }), InclusiveRange.INT.optionalFieldOf("sky_light_limit", MobSpawnerData.a.LIGHT_RANGE).flatXmap(MobSpawnerData.a::checkLightBoundaries, MobSpawnerData.a::checkLightBoundaries).forGetter((mobspawnerdata_a) -> {
                return mobspawnerdata_a.skyLightLimit;
            })).apply(instance, MobSpawnerData.a::new);
        });

        private static DataResult<InclusiveRange<Integer>> checkLightBoundaries(InclusiveRange<Integer> inclusiverange) {
            return !MobSpawnerData.a.LIGHT_RANGE.contains(inclusiverange) ? DataResult.error("Light values must be withing range " + MobSpawnerData.a.LIGHT_RANGE) : DataResult.success(inclusiverange);
        }
    }
}
