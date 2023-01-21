package net.minecraft.world.level.block;

import net.minecraft.core.BlockPosition;
import net.minecraft.server.level.WorldServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockBase;
import net.minecraft.world.level.block.state.IBlockData;

public class WeatheringCopperStairBlock extends BlockStairs implements WeatheringCopper {

    private final WeatheringCopper.a weatherState;

    public WeatheringCopperStairBlock(WeatheringCopper.a weatheringcopper_a, IBlockData iblockdata, BlockBase.Info blockbase_info) {
        super(iblockdata, blockbase_info);
        this.weatherState = weatheringcopper_a;
    }

    @Override
    public void randomTick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, RandomSource randomsource) {
        this.onRandomTick(iblockdata, worldserver, blockposition, randomsource);
    }

    @Override
    public boolean isRandomlyTicking(IBlockData iblockdata) {
        return WeatheringCopper.getNext(iblockdata.getBlock()).isPresent();
    }

    @Override
    public WeatheringCopper.a getAge() {
        return this.weatherState;
    }
}
