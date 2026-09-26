package me.uuun.packetWorker.command;

import lombok.RequiredArgsConstructor;
import me.uuun.packetWorker.PacketWorker;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SendAttributeCommand implements CommandExecutor {
    private final PacketWorker plugin;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player player)) return false;

        if(args.length < 1) return false;
        double maxHealth;
        try{
            maxHealth = Double.parseDouble(args[0]);
        } catch (Exception e){
            return false;
        }

        AttributeInstance healthAttribute = new AttributeInstance(
                Attributes.MAX_HEALTH,
                (instance) -> {}
        );
        healthAttribute.setBaseValue(maxHealth);

        Collection<AttributeInstance> attributes = List.of(healthAttribute);

        plugin.getAttributePacket().sendAttribute(player, attributes);
        return true;
    }
}