package com.filmkarakterleri.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

/**
 * Evcilleştirilebilir film karakteri entity'si.
 *
 * - Ekmek ile beslenerek evcilleştirilir (istersen FOOD_ITEM'ı değiştirebilirsin).
 * - Evcilleştirilince oturma/takip etme davranışı kazanır (vanilla köpek AI'sına benzer).
 * - Sağ tıklanınca (evcilse ve elde yem yoksa) rastgele bir replik söyler.
 * - Hangi karakter olduğu KARAKTER_INDEX veri parametresiyle senkronize edilir (0-9 arası).
 */
public class FilmKarakteriEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> KARAKTER_INDEX =
            SynchedEntityData.defineId(FilmKarakteriEntity.class, EntityDataSerializers.INT);

    // Evcilleştirme için kullanılacak yem - istersen değiştir
    private static final net.minecraft.world.item.Item FOOD_ITEM = Items.BREAD;

    public FilmKarakteriEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(KARAKTER_INDEX, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, stack -> stack.is(FOOD_ITEM), false));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    // ---- Karakter tipi ----

    public void setKarakterIndex(int index) {
        this.entityData.set(KARAKTER_INDEX, index);
    }

    public int getKarakterIndex() {
        return this.entityData.get(KARAKTER_INDEX);
    }

    public KarakterData getKarakter() {
        return KarakterData.byIndex(getKarakterIndex());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("KarakterIndex", getKarakterIndex());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("KarakterIndex")) {
            setKarakterIndex(tag.getInt("KarakterIndex"));
        }
    }

    // ---- Evcilleştirme ve etkileşim ----

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(FOOD_ITEM) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        }

        // Henüz evcil değilse ve elinde yem varsa -> evcilleştirmeyi dene
        if (!this.isTame() && itemstack.is(FOOD_ITEM)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (this.random.nextInt(3) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte) 7); // sevgi partikülü
                player.sendSystemMessage(Component.literal(
                        getKarakter().getDisplayName() + " artık senin yanında! \"" + getKarakter().getRandomPhrase() + "\""));
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6); // duman partikülü
            }
            return InteractionResult.SUCCESS;
        }

        // Evcilse ve sahibiyse -> otur/kalk komutu ya da replik söyletme
        if (this.isTame() && this.isOwnedBy(player)) {
            if (!itemstack.is(FOOD_ITEM)) {
                if (player.isSecondaryUseActive()) {
                    // Shift + sağ tık -> otur/kalk
                    this.setOrderedToSit(!this.isOrderedToSit());
                } else {
                    // Normal sağ tık -> replik söylet
                    soyleRepligi(player);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return super.mobInteract(player, hand);
    }

    /** Karakterin repliklerinden birini chat'e yazar. */
    public void soyleRepligi(Player player) {
        String replik = getKarakter().getRandomPhrase();
        this.level().players().forEach(p -> {
            if (p.distanceToSqr(this) < 400) { // 20 blok yarıçap
                p.sendSystemMessage(Component.literal(
                        "§6" + getKarakter().getDisplayName() + "§f: " + replik));
            }
        });
    }

    @Nullable
    @Override
    public net.minecraft.world.entity.AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level,
                                                                     net.minecraft.world.entity.AgeableMob otherParent) {
        return null; // Bu karakterler üremiyor
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource source) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }
}
