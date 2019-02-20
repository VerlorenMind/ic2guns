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
import net.xecut.ic2guns.item.ItemMagazine;

public class ShapelessMagazineFillRecipe extends ShapelessRecipes {

    public ShapelessMagazineFillRecipe (String group, ItemStack output, NonNullList<Ingredient> input) {
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
        if (ammo >= ItemMagazine.MAX_AMMO)
            return ItemStack.EMPTY;

        return ItemMagazine.makeMagazine(1, ammo + 1);
    }

    public static class Factory implements IRecipeFactory {
        @Override
        public IRecipe parse (JsonContext context, JsonObject json) {
            String group = JsonUtils.getString(json, "group", "");
            NonNullList<Ingredient> ingredients = Util.parseShapeless(context, json);
            ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"),
                    context);
            return new ShapelessMagazineFillRecipe(group, result, ingredients);
        }
    }

}
