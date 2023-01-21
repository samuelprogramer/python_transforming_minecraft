package net.minecraft.world.level.block.entity;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.advancements.CriterionTriggers;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.EnumDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.phys.Vec3D;

public class SculkCatalystBlockEntity extends TileEntity implements GameEventListener {

    private final BlockPositionSource blockPosSource;
    private final SculkSpreader sculkSpreader;

    public SculkCatalystBlockEntity(BlockPosition blockposition, IBlockData iblockdata) {
        super(TileEntityTypes.SCULK_CATALYST, blockposition, iblockdata);
        this.blockPosSource = new BlockPositionSource(this.worldPosition);
        this.sculkSpreader = SculkSpreader.createLevelSpreader();
    }

    @Override
    public PositionSource getListenerSource() {
        return this.blockPosSource;
    }

    @Override
    public int getListenerRadius() {
        return 8;
    }

    @Override
    public GameEventListener.a getDeliveryMode() {
        return GameEventListener.a.BY_DISTANCE;
    }

    @Override
    public boolean handleGameEvent(WorldServer worldserver, GameEvent gameevent, GameEvent.a gameevent_a, Vec3D vec3d) {
        if (gameevent == GameEvent.ENTITY_DIE) {
            Entity entity = gameevent_a.sourceEntity();

            if (entity instanceof EntityLiving) {
                EntityLiving entityliving = (EntityLiving) entity;

                if (!entityliving.wasExperienceConsumed()) {
                    int i = entityliving.getExperienceReward();

                    if (entityliving.shouldDropExperience() && i > 0) {
                        this.sculkSpreader.addCursors(new BlockPosition(vec3d.relative(EnumDirection.UP, 0.5D)), i);
                        this.tryAwardItSpreadsAdvancement(entityliving);
                    }

                    entityliving.skipDropExperience();
                    SculkCatalystBlock.bloom(worldserver, this.worldPosition, this.getBlockState(), worldserver.getRandom());
                }

                return true;
            }
        }

        return false;
    }

    private void tryAwardItSpreadsAdvancement(EntityLiving entityliving) {
        EntityLiving entityliving1 = entityliving.getLastHurtByMob();

        if (entityliving1 instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityliving1;
            DamageSource damagesource = entityliving.getLastDamageSource() == null ? DamageSource.playerAttack(entityplayer) : entityliving.getLastDamageSource();

            CriterionTriggers.KILL_MOB_NEAR_SCULK_CATALYST.trigger(entityplayer, entityliving, damagesource);
        }

    }

    public static void serverTick(World world, BlockPosition blockposition, IBlockData iblockdata, SculkCatalystBlockEntity sculkcatalystblockentity) {
        sculkcatalystblockentity.sculkSpreader.updateCursors(world, blockposition, world.getRandom(), true);
    }

    @Override
    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.sculkSpreader.load(nbttagcompound);
    }

    @Override
    protected void saveAdditional(NBTTagCompound nbttagcompound) {
        this.sculkSpreader.save(nbttagcompound);
        super.saveAdditional(nbttagcompound);
    }

    @VisibleForTesting
    public SculkSpreader getSculkSpreader() {
        return this.sculkSpreader;
    }
}
