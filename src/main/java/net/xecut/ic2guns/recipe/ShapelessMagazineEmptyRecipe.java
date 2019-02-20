package net.xecut.ic2guns.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.xecut.ic2guns.item.IC2GunsItems;
import net.xecut.ic2guns.item.ItemMagazine;

public class ShapelessMagazineEmptyRecipe extends ShapelessRecipes {

    public ShapelessMagazineEmptyRecipe (String group, ItemStack output, NonNullList<Ingredient> input) {
        super(group, output, input);
    }

    @Override
    public ItemStack getCraftingResult (InventoryCrafting inv) {
        int ammo = 0;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemMagazine) {
                ammo = ItemMagazine.getAmmo(itemStack);
                break;
            }
        }
        if (ammo == 0) return ItemStack.EMPTY;

        ItemStack result = super.getCraftingResult(inv);
        result.setCount(ammo);
        return result;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems (InventoryCrafting inv) {
        int pos = 0;
        for (; pos < inv.getSizeInventory(); pos++) {
            ItemStack itemStack = inv.getStackInSlot(pos);
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemMagazine)
                break;
        }

        NonNullList<ItemStack> remaining = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        remaining.set(pos, new ItemStack(IC2GunsItems.MAGAZINE.instance));
        ItemMagazine.setAmmo(remaining.get(pos), 0);
        return remaining;
    }

    public static class Factory implements IRecipeFactory {
        @Override
        public IRecipe parse (JsonContext context, JsonObject json) {
            String group = JsonUtils.getString(json, "group", "");
            NonNullList<Ingredient> ingredients = Util.parseShapeless(context, json);
            ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"),
                    context);
            return new ShapelessMagazineEmptyRecipe(group, result, ingredients);
        }
    }
}
