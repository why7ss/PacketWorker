package me.uuun.packetWorker;

import lombok.Getter;
import me.uuun.packetWorker.command.SendAttributeCommand;
import me.uuun.packetWorker.damage.AttributePacket;
import me.uuun.packetWorker.packets.AttributePacket_V1_21_11;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketWorker extends JavaPlugin {
    @Getter public AttributePacket attributePacket;

    @Override
    public void onEnable() {
        getLogger().info("Загружаем плагин...");
        setupEffects();
        getLogger().info("Плагин успешно загружен.");

        getCommand("setmaxhealth").setExecutor(new SendAttributeCommand(this));
    }

    public void setupEffects(){
        String minecraftVersion = getServer().getMinecraftVersion(); // "1.21.11"
        String bukkitVersion = getServer().getBukkitVersion();        // "1.21.11-R0.1-SNAPSHOT"
        getLogger().info("Обнаруженная версия сервера: " + minecraftVersion + " | " + bukkitVersion);

        switch (minecraftVersion) {
            case "1.21.11":
                attributePacket = new AttributePacket_V1_21_11();
                break;
            default:
                getLogger().warning("Неподдерживаемая версия сервера: " + minecraftVersion);
                getServer().getPluginManager().disablePlugin(this);
                break;
        }
    }
}