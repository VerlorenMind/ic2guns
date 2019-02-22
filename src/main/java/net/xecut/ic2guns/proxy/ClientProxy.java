package net.xecut.ic2guns.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.xecut.ic2guns.keybinds.Keybinds;

public class ClientProxy extends ServerProxy {

    @Override
    public void preInit (FMLPreInitializationEvent event) {}

    @Override
    public void init (FMLInitializationEvent event) {
        Keybinds.init();
    }

    @Override
    public void postInit (FMLPostInitializationEvent event) {}
}
