package net.xecut.ic2guns.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;

public class Util {

    public static NonNullList<Ingredient> parseShapeless (JsonContext context, JsonObject json) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (JsonElement element : JsonUtils.getJsonArray(json, "ingredients"))
            ingredients.add(CraftingHelper.getIngredient(element, context));
        return ingredients;
    }

}
