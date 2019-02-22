package net.xecut.ic2guns.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.xecut.ic2guns.IC2GunsMod;
import net.xecut.ic2guns.Reference;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber (modid = Reference.MODID)
public class Keybinds {

    public static KeyBinding reload;

    public static void init () {
        reload = new KeyBinding("key.reload", Keyboard.KEY_R, "key.category");
        ClientRegistry.registerKeyBinding(reload);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onClientTick (TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END) && Keybinds.reload.isKeyDown()) {
            IC2GunsMod.logger.info("RELOADING");
        }
    }

}
