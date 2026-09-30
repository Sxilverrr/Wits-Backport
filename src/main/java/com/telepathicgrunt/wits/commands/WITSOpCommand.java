package com.telepathicgrunt.wits.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class WITSOpCommand extends CommandBase {
    @Override
    public String getName() {
        return "witsop";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/witsop <dimensionId> <x> <y> <z>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 4) {
            throw new WrongUsageException(getUsage(sender));
        }

        int dimensionId = parseInt(args[0]);
        WorldServer world = server.getWorld(dimensionId);
        if (world == null) {
            throw new CommandException("No dimension with id " + dimensionId + " is loaded.");
        }

        BlockPos senderPos = sender.getPosition();
        double x = parseCoordinate(senderPos.getX(), args[1], true).getResult();
        double y = parseCoordinate(senderPos.getY(), args[2], -4096, 4096, false).getResult();
        double z = parseCoordinate(senderPos.getZ(), args[3], true).getResult();
        BlockPos target = new BlockPos(x, y, z);

        WITSCommand.listStructures(world, target, false, sender);
    }
}
