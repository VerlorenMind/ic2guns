package net.xecut.ic2guns.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.xecut.ic2guns.Reference;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRifle extends IC2GunsItemBasic {

    private static final String AMMO_KEY = "ammo";
    private static final String MAGAZINE_KEY = "has_magazine";

    public ItemRifle () {
        super("rifle");
        setMaxStackSize(1);

        addPropertyOverride(new ResourceLocation(Reference.MODID, "empty"),
                new IItemPropertyGetter() {
                    @Override
                    public float apply (ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                        return ItemRifle.getPropertyEmpty(stack);
                    }
                });
    }

    @Override
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.ammo_label") + " " + getAmmo(stack));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean showDurabilityBar (ItemStack stack) {
        int ammo = getAmmo(stack);
        return (ammo != 0) && (ammo != ItemMagazine.MAX_AMMO);
    }

    @Override
    public double getDurabilityForDisplay (ItemStack stack) {
        return 1.0 - getAmmo(stack) / (double) ItemMagazine.MAX_AMMO;
    }

    public static float getPropertyEmpty (ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof ItemRifle)
            return ItemRifle.hasMagazine(stack) ? 0.f : 1.f; // 0 - not empty
        return 0.f;
    }

    public static boolean hasMagazine (ItemStack stack) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            return nbt.hasKey(MAGAZINE_KEY) && nbt.getBoolean(MAGAZINE_KEY);
        }
        return false;
    }

    public static int getAmmo (ItemStack stack) {
        int ammo = 0;
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt.hasKey(AMMO_KEY))
                ammo = nbt.getInteger(AMMO_KEY);
        }
        return ammo;
    }

    public static void setAmmo (ItemStack stack, int value) {
        NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        nbt.setInteger(AMMO_KEY, value);
        stack.setTagCompound(nbt);
    }

}
