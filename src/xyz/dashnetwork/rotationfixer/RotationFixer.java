package xyz.dashnetwork.rotationfixer;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dashnetwork.rotationfixer.listener.PacketListener;

public final class RotationFixer extends JavaPlugin {

    private static ProtocolManager manager;
    private static PacketListener listener;

    @Override
    public void onEnable() {
        manager = ProtocolLibrary.getProtocolManager();
        listener = new PacketListener(this);

        manager.addPacketListener(listener);
    }

    @Override
    public void onDisable() {
        manager.removePacketListener(listener);
    }

}
