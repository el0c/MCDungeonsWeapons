package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.types.AOEEnchantment;
import chronosacaria.mcdw.enchants.types.DamageBoostEnchantment;
import chronosacaria.mcdw.enums.EnchantmentsID;
import chronosacaria.mcdw.enums.SettingsID;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class VoidShotEnchantment extends DamageBoostEnchantment {

    public VoidShotEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        if (Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_SHOT)) {
            Registry.register(Registry.ENCHANTMENT, Mcdw.ID("void_shot"), this);
        }
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    protected boolean canAccept (Enchantment other){
        return Mcdw.CONFIG.mcdwEnchantmentSettingsConfig.enableEnchantmentSettings.get(SettingsID.ENABLE_OP_ENCHANTMENT_MIXING) || !(other instanceof AOEEnchantment || other instanceof DamageBoostEnchantment);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return Mcdw.CONFIG.mcdwEnchantmentsConfig.enableEnchantments.get(EnchantmentsID.VOID_SHOT);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem || stack.getItem() instanceof BowItem;
    }
}

