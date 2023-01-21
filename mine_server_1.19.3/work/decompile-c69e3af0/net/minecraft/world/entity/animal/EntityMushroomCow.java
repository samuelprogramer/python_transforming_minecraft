package net.minecraft.world.entity.animal;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.particles.Particles;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.WorldServer;
import net.minecraft.sounds.SoundCategory;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.tags.TagsBlock;
import net.minecraft.tags.TagsItem;
import net.minecraft.util.INamable;
import net.minecraft.util.RandomSource;
import net.minecraft.world.EnumHand;
import net.minecraft.world.EnumInteractionResult;
import net.minecraft.world.effect.MobEffectList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityAgeable;
import net.minecraft.world.entity.EntityLightning;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumMobSpawn;
import net.minecraft.world.entity.IShearable;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.ItemLiquidUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemSuspiciousStew;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GeneratorAccess;
import net.minecraft.world.level.IWorldReader;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.gameevent.GameEvent;
import org.apache.commons.lang3.tuple.Pair;

public class EntityMushroomCow extends EntityCow implements IShearable, VariantHolder<EntityMushroomCow.Type> {

    private static final DataWatcherObject<String> DATA_TYPE = DataWatcher.defineId(EntityMushroomCow.class, DataWatcherRegistry.STRING);
    private static final int MUTATE_CHANCE = 1024;
    @Nullable
    private MobEffectList effect;
    private int effectDuration;
    @Nullable
    private UUID lastLightningBoltUUID;

    public EntityMushroomCow(EntityTypes<? extends EntityMushroomCow> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    public float getWalkTargetValue(BlockPosition blockposition, IWorldReader iworldreader) {
        return iworldreader.getBlockState(blockposition.below()).is(Blocks.MYCELIUM) ? 10.0F : iworldreader.getPathfindingCostFromLightLevels(blockposition);
    }

    public static boolean checkMushroomSpawnRules(EntityTypes<EntityMushroomCow> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, RandomSource randomsource) {
        return generatoraccess.getBlockState(blockposition.below()).is(TagsBlock.MOOSHROOMS_SPAWNABLE_ON) && isBrightEnoughToSpawn(generatoraccess, blockposition);
    }

    @Override
    public void thunderHit(WorldServer worldserver, EntityLightning entitylightning) {
        UUID uuid = entitylightning.getUUID();

        if (!uuid.equals(this.lastLightningBoltUUID)) {
            this.setVariant(this.getVariant() == EntityMushroomCow.Type.RED ? EntityMushroomCow.Type.BROWN : EntityMushroomCow.Type.RED);
            this.lastLightningBoltUUID = uuid;
            this.playSound(SoundEffects.MOOSHROOM_CONVERT, 2.0F, 1.0F);
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EntityMushroomCow.DATA_TYPE, EntityMushroomCow.Type.RED.type);
    }

    @Override
    public EnumInteractionResult mobInteract(EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.getItemInHand(enumhand);

        if (itemstack.is(Items.BOWL) && !this.isBaby()) {
            boolean flag = false;
            ItemStack itemstack1;

            if (this.effect != null) {
                flag = true;
                itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
                ItemSuspiciousStew.saveMobEffect(itemstack1, this.effect, this.effectDuration);
                this.effect = null;
                this.effectDuration = 0;
            } else {
                itemstack1 = new ItemStack(Items.MUSHROOM_STEW);
            }

            ItemStack itemstack2 = ItemLiquidUtil.createFilledResult(itemstack, entityhuman, itemstack1, false);

            entityhuman.setItemInHand(enumhand, itemstack2);
            SoundEffect soundeffect;

            if (flag) {
                soundeffect = SoundEffects.MOOSHROOM_MILK_SUSPICIOUSLY;
            } else {
                soundeffect = SoundEffects.MOOSHROOM_MILK;
            }

            this.playSound(soundeffect, 1.0F, 1.0F);
            return EnumInteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (itemstack.is(Items.SHEARS) && this.readyForShearing()) {
            this.shear(SoundCategory.PLAYERS);
            this.gameEvent(GameEvent.SHEAR, entityhuman);
            if (!this.level.isClientSide) {
                itemstack.hurtAndBreak(1, entityhuman, (entityhuman1) -> {
                    entityhuman1.broadcastBreakEvent(enumhand);
                });
            }

            return EnumInteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (this.getVariant() == EntityMushroomCow.Type.BROWN && itemstack.is(TagsItem.SMALL_FLOWERS)) {
            if (this.effect != null) {
                for (int i = 0; i < 2; ++i) {
                    this.level.addParticle(Particles.SMOKE, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }
            } else {
                Optional<Pair<MobEffectList, Integer>> optional = this.getEffectFromItemStack(itemstack);

                if (!optional.isPresent()) {
                    return EnumInteractionResult.PASS;
                }

                Pair<MobEffectList, Integer> pair = (Pair) optional.get();

                if (!entityhuman.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                for (int j = 0; j < 4; ++j) {
                    this.level.addParticle(Particles.EFFECT, this.getX() + this.random.nextDouble() / 2.0D, this.getY(0.5D), this.getZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
                }

                this.effect = (MobEffectList) pair.getLeft();
                this.effectDuration = (Integer) pair.getRight();
                this.playSound(SoundEffects.MOOSHROOM_EAT, 2.0F, 1.0F);
            }

            return EnumInteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            return super.mobInteract(entityhuman, enumhand);
        }
    }

    @Override
    public void shear(SoundCategory soundcategory) {
        this.level.playSound((EntityHuman) null, (Entity) this, SoundEffects.MOOSHROOM_SHEAR, soundcategory, 1.0F, 1.0F);
        if (!this.level.isClientSide()) {
            EntityCow entitycow = (EntityCow) EntityTypes.COW.create(this.level);

            if (entitycow != null) {
                ((WorldServer) this.level).sendParticles(Particles.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                this.discard();
                entitycow.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                entitycow.setHealth(this.getHealth());
                entitycow.yBodyRot = this.yBodyRot;
                if (this.hasCustomName()) {
                    entitycow.setCustomName(this.getCustomName());
                    entitycow.setCustomNameVisible(this.isCustomNameVisible());
                }

                if (this.isPersistenceRequired()) {
                    entitycow.setPersistenceRequired();
                }

                entitycow.setInvulnerable(this.isInvulnerable());
                this.level.addFreshEntity(entitycow);

                for (int i = 0; i < 5; ++i) {
                    this.level.addFreshEntity(new EntityItem(this.level, this.getX(), this.getY(1.0D), this.getZ(), new ItemStack(this.getVariant().blockState.getBlock())));
                }
            }
        }

    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void addAdditionalSaveData(NBTTagCompound nbttagcompound) {
        super.addAdditionalSaveData(nbttagcompound);
        nbttagcompound.putString("Type", this.getVariant().getSerializedName());
        if (this.effect != null) {
            nbttagcompound.putInt("EffectId", MobEffectList.getId(this.effect));
            nbttagcompound.putInt("EffectDuration", this.effectDuration);
        }

    }

    @Override
    public void readAdditionalSaveData(NBTTagCompound nbttagcompound) {
        super.readAdditionalSaveData(nbttagcompound);
        this.setVariant(EntityMushroomCow.Type.byType(nbttagcompound.getString("Type")));
        if (nbttagcompound.contains("EffectId", 1)) {
            this.effect = MobEffectList.byId(nbttagcompound.getInt("EffectId"));
        }

        if (nbttagcompound.contains("EffectDuration", 3)) {
            this.effectDuration = nbttagcompound.getInt("EffectDuration");
        }

    }

    private Optional<Pair<MobEffectList, Integer>> getEffectFromItemStack(ItemStack itemstack) {
        SuspiciousEffectHolder suspiciouseffectholder = SuspiciousEffectHolder.tryGet(itemstack.getItem());

        return suspiciouseffectholder != null ? Optional.of(Pair.of(suspiciouseffectholder.getSuspiciousEffect(), suspiciouseffectholder.getEffectDuration())) : Optional.empty();
    }

    public void setVariant(EntityMushroomCow.Type entitymushroomcow_type) {
        this.entityData.set(EntityMushroomCow.DATA_TYPE, entitymushroomcow_type.type);
    }

    @Override
    public EntityMushroomCow.Type getVariant() {
        return EntityMushroomCow.Type.byType((String) this.entityData.get(EntityMushroomCow.DATA_TYPE));
    }

    @Nullable
    @Override
    public EntityMushroomCow getBreedOffspring(WorldServer worldserver, EntityAgeable entityageable) {
        EntityMushroomCow entitymushroomcow = (EntityMushroomCow) EntityTypes.MOOSHROOM.create(worldserver);

        if (entitymushroomcow != null) {
            entitymushroomcow.setVariant(this.getOffspringType((EntityMushroomCow) entityageable));
        }

        return entitymushroomcow;
    }

    private EntityMushroomCow.Type getOffspringType(EntityMushroomCow entitymushroomcow) {
        EntityMushroomCow.Type entitymushroomcow_type = this.getVariant();
        EntityMushroomCow.Type entitymushroomcow_type1 = entitymushroomcow.getVariant();
        EntityMushroomCow.Type entitymushroomcow_type2;

        if (entitymushroomcow_type == entitymushroomcow_type1 && this.random.nextInt(1024) == 0) {
            entitymushroomcow_type2 = entitymushroomcow_type == EntityMushroomCow.Type.BROWN ? EntityMushroomCow.Type.RED : EntityMushroomCow.Type.BROWN;
        } else {
            entitymushroomcow_type2 = this.random.nextBoolean() ? entitymushroomcow_type : entitymushroomcow_type1;
        }

        return entitymushroomcow_type2;
    }

    public static enum Type implements INamable {

        RED("red", Blocks.RED_MUSHROOM.defaultBlockState()), BROWN("brown", Blocks.BROWN_MUSHROOM.defaultBlockState());

        public static final INamable.a<EntityMushroomCow.Type> CODEC = INamable.fromEnum(EntityMushroomCow.Type::values);
        final String type;
        final IBlockData blockState;

        private Type(String s, IBlockData iblockdata) {
            this.type = s;
            this.blockState = iblockdata;
        }

        public IBlockData getBlockState() {
            return this.blockState;
        }

        @Override
        public String getSerializedName() {
            return this.type;
        }

        static EntityMushroomCow.Type byType(String s) {
            return (EntityMushroomCow.Type) EntityMushroomCow.Type.CODEC.byName(s, EntityMushroomCow.Type.RED);
        }
    }
}
