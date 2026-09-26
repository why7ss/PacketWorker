package me.uuun.packetWorker.packet;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface AttributePacket {
    void sendAttribute(Player player, Collection<AttributeInstance> attributes);
}