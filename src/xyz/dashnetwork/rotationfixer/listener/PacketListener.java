package xyz.dashnetwork.rotationfixer.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.ScheduledPacket;
import org.bukkit.plugin.Plugin;

public final class PacketListener extends PacketAdapter {

    public PacketListener(Plugin plugin) {
        super(plugin, PacketType.Play.Server.NAMED_ENTITY_SPAWN);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer spawn = event.getPacket();
        int id = spawn.getIntegers().read(0);
        byte yaw = spawn.getBytes().read(0);

        PacketContainer rotation = new PacketContainer(PacketType.Play.Server.ENTITY_HEAD_ROTATION);
        rotation.getIntegers().write(0, id);
        rotation.getBytes().write(0, yaw);

        event.schedule(ScheduledPacket.fromSilent(rotation, event.getPlayer()));
    }

}
