package me.uuun.packetWorker.packets;

import me.uuun.packetWorker.damage.AttributePacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;

public class AttributePacket_V1_21_11 implements AttributePacket {
    @Override
    public void sendAttribute(Player player, Collection<AttributeInstance> attributes) {
        ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();

        ClientboundUpdateAttributesPacket packet = new ClientboundUpdateAttributesPacket(
                serverPlayer.getId(),
                attributes
        );

        serverPlayer.connection.send(packet);
    }
}