package net.xecut.ic2guns.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.xecut.ic2guns.Reference;

@Mod.EventBusSubscriber (modid = Reference.MODID)
public class IC2GunsItems {

    // Store items classes and instances
    public enum Items {
        SMALL_GUNPOWDER (ItemSmallGunpowder.class),
        CARTRIDGE_EMPTY (ItemCartridgeEmpty.class),
        CARTRIDGE (ItemCartridge.class),
        NUGGET_LEAD (ItemNuggetLead.class);

        public Class cl;
        public IC2GunsItemBasic instance;

        Items (Class cl) {
            this.cl = cl;
        }

        public void registerOreDictionary (String name) {
            OreDictionary.registerOre(name, instance);
        }
    }

    public static void preInit () {
        // Create instances
        for (Items item: Items.values()) {
            try {
                item.instance = (IC2GunsItemBasic) item.cl.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void registerItems (RegistryEvent.Register<Item> event) {
        for (Items item: Items.values())
            event.getRegistry().register(item.instance);

        Items.NUGGET_LEAD.registerOreDictionary ("nuggetLead");
    }

    @SubscribeEvent
    public static void registerRenders (ModelRegistryEvent event) {
        for (Items item: Items.values())
            registerRender(item.instance);
    }

    private static void registerRender (IC2GunsItemBasic item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
