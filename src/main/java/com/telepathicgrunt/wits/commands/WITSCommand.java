package com.telepathicgrunt.wits.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

import java.util.Locale;

public class WITSCommand extends CommandBase {
    private static final String[] OVERWORLD = {"Stronghold", "Mineshaft", "Village", "Temple", "Monument", "Mansion"};
    private static final String[] NETHER = {"Fortress"};
    private static final String[] END = {"EndCity"};

    @Override
    public String getName() {
        return "wits";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/wits";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        listStructures((WorldServer) sender.getEntityWorld(), sender.getPosition(), true, sender);
    }

    static void listStructures(WorldServer world, BlockPos pos, boolean callerPosition, ICommandSender sender) {
        int dimension = world.provider.getDimension();
        StringBuilder found = new StringBuilder();
        for (String name : dimension == -1 ? NETHER : dimension == 1 ? END : OVERWORLD) {
            try {
                if (world.getChunkProvider().isInsideStructure(world, name, pos)) {
                    found.append("\n - ").append(TextFormatting.GOLD).append("minecraft:").append(name.toLowerCase(Locale.ROOT)).append(TextFormatting.RESET);
                }
            } catch (Throwable ignored) {
            }
        }

        String where = callerPosition ? "your location" : pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
        sender.sendMessage(new TextComponentString(found.length() == 0
                ? "There's no structures at " + (callerPosition ? "your location." : "the location.")
                : "Structure(s) at " + where + ":" + found));
    }
}
