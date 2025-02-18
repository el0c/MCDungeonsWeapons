package chronosacaria.mcdw.mixin.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.EnchantsRegistry;
import chronosacaria.mcdw.enums.EnchantmentsID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class GrowingEnchantmentMixin extends Entity {
    public GrowingEnchantmentMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void onGrowingEnchantmentEntityHit(EntityHitResult entityHitResult, CallbackInfo ci){
        if (!(entityHitResult.getEntity() instanceof LivingEntity)) {
            return;
        }
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();
        LivingEntity shooter = (LivingEntity) persistentProjectileEntity.getOwner();
        ItemStack mainHandStack = null;
        if (shooter != null) {
            mainHandStack = shooter.getMainHandStack();
        }
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.GROWING)) {
            if (mainHandStack != null && (EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack) >= 1)) {
                int level = EnchantmentHelper.getLevel(EnchantsRegistry.GROWING, mainHandStack);
                double originalDamage = persistentProjectileEntity.getDamage();
                double damageModifier = 0;
                if (level == 1) damageModifier = 1.25D;
                if (level == 2) damageModifier = 1.5D;
                if (level == 3) damageModifier = 1.75D;
                double squareDistanceTo = shooter.distanceTo(target);
                double distance = Math.sqrt(squareDistanceTo);
                double distanceTraveledModifier = distance * 0.1;
                persistentProjectileEntity.setDamage(originalDamage * Math.min(distanceTraveledModifier, damageModifier));
            }
        }
    }
}
