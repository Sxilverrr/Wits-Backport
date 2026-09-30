package com.telepathicgrunt.wits;

import com.telepathicgrunt.wits.commands.WITSCommand;
import com.telepathicgrunt.wits.commands.WITSOpCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "wits", useMetadata = true, acceptableRemoteVersions = "*")
public class WITS {
    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new WITSCommand());
        event.registerServerCommand(new WITSOpCommand());
    }
}
