package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.bases.McdwCustomWeaponBase;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.registry.Registry;

public class SoulDevourerEnchantment extends Enchantment {
    public SoulDevourerEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("soul_devourer"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.SOUL_DEVOURER);
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING) || !(other instanceof SoulSiphonEnchantment);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem || stack.getItem() instanceof McdwCustomWeaponBase;
    }
}
