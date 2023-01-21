package net.minecraft.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.WorldGenFeatureDefinedStructurePoolStructure;
import net.minecraft.world.level.levelgen.structure.pools.WorldGenFeatureDefinedStructurePoolTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorList;

public class WorldGenFeatureBastionHoglinStable {

    public WorldGenFeatureBastionHoglinStable() {}

    public static void bootstrap(BootstapContext<WorldGenFeatureDefinedStructurePoolTemplate> bootstapcontext) {
        HolderGetter<ProcessorList> holdergetter = bootstapcontext.lookup(Registries.PROCESSOR_LIST);
        Holder<ProcessorList> holder = holdergetter.getOrThrow(ProcessorLists.STABLE_DEGRADATION);
        Holder<ProcessorList> holder1 = holdergetter.getOrThrow(ProcessorLists.SIDE_WALL_DEGRADATION);
        HolderGetter<WorldGenFeatureDefinedStructurePoolTemplate> holdergetter1 = bootstapcontext.lookup(Registries.TEMPLATE_POOL);
        Holder<WorldGenFeatureDefinedStructurePoolTemplate> holder2 = holdergetter1.getOrThrow(WorldGenFeaturePieces.EMPTY);

        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/starting_pieces", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/starting_stairs_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/starting_stairs_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/starting_stairs_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/starting_stairs_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/starting_stairs_4", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/mirrored_starting_pieces", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/stairs_0_mirrored", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/stairs_1_mirrored", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/stairs_2_mirrored", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/stairs_3_mirrored", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/starting_pieces/stairs_4_mirrored", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/wall_bases", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/walls/wall_base", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/walls", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/walls/side_wall_0", holder1), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/walls/side_wall_1", holder1), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/stairs", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_1_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_1_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_1_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_1_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_1_4", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_2_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_2_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_2_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_2_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_2_4", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_3_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_3_1", holder), 1), new Pair[]{Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_3_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_3_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/stairs/stairs_3_4", holder), 1)}), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/small_stables/inner", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/inner_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/inner_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/inner_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/inner_3", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/small_stables/outer", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/outer_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/outer_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/outer_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/small_stables/outer_3", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/large_stables/inner", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/inner_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/inner_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/inner_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/inner_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/inner_4", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/large_stables/outer", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/outer_0", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/outer_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/outer_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/outer_3", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/large_stables/outer_4", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/posts", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/posts/stair_post", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/posts/end_post", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/ramparts", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/ramparts/ramparts_1", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/ramparts/ramparts_2", holder), 1), Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/ramparts/ramparts_3", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/rampart_plates", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/rampart_plates/rampart_plate_1", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
        WorldGenFeaturePieces.register(bootstapcontext, "bastion/hoglin_stable/connectors", new WorldGenFeatureDefinedStructurePoolTemplate(holder2, ImmutableList.of(Pair.of(WorldGenFeatureDefinedStructurePoolStructure.single("bastion/hoglin_stable/connectors/end_post_connector", holder), 1)), WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID));
    }
}
