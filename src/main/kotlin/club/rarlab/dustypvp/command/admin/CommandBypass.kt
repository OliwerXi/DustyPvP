package club.rarlab.dustypvp.command.admin

import club.rarlab.dustypvp.command.CommandContext
import club.rarlab.dustypvp.command.SubCommand
import club.rarlab.dustypvp.config.configurations.Message.*
import club.rarlab.dustypvp.structure.PlayerHandler
import club.rarlab.dustypvp.util.Permission
import club.rarlab.dustypvp.util.sendTo
import org.bukkit.entity.Player

/**
 * Class to handle the 'bypass' command.
 */
class CommandBypass : SubCommand(
        "bypass",
        "Toggle your bypass mode",
        Permission.COMMAND_BYPASS
) {
    /**
     * Execute the sub command.
     */
    override fun execute(context: CommandContext) {
        val sender = context.commandSender

        if (sender !is Player) {
            ONLY_PLAYERS.toString().sendTo(sender)
            return
        }

        val dustyPlayer = PlayerHandler.fetch(sender) ?: return
        with (dustyPlayer) {
            this.bypass = !this.bypass
            ADMIN_BYPASS.toString().format(
                    if (this.bypass) ENABLED.toString() else DISABLED.toString()
            ).sendTo(sender)
        }
    }
}