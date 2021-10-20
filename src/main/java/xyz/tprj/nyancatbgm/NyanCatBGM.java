package xyz.tprj.nyancatbgm;

import net.minecraft.server.v1_16_R3.MinecraftKey;
import net.minecraft.server.v1_16_R3.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_16_R3.SoundCategory;
import net.minecraft.server.v1_16_R3.SoundEffect;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public final class NyanCatBGM extends JavaPlugin implements Listener {

    private final int[] notes = new int[256];
    private final int[] notes_sub = new int[256];
    private final float[] pitches = new float[25];
    private final int[] introNotes = new int[32];
    private final int[] introNotes_sub = new int[32];

    private final Sound MUSIC_SOUND = Sound.BLOCK_NOTE_BLOCK_HARP;
    private final Sound MUSIC_SOUND_SUB = Sound.BLOCK_NOTE_BLOCK_BASS;
    private final Sound MUSIC_SOUND_INTRO_SUB = Sound.BLOCK_NOTE_BLOCK_SNARE;

    private static NyanCatBGM instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        int i = 0;
        for (Integer integer : Arrays.asList(12, -1, 14, -1, 9, 9, -1, 5, 8, 7, 5, -1, 5, -1, 7, -1, 8, -1, 8, 7, 5, 6, 9, 12, 14, 9, 12, 7, 9, 5, 7, 5, 9, -1, 12, -1, 14, 9, 12, 7, 9, 5, 8, 9, 8, 7, 5, 7, 8, -1, 5, 7, 9, 12, 7, 9, 7, 5, 7, -1, 5, -1, 7, -1, 12, -1, 14, -1, 9, 9, -1, 5, 8, 7, 5, -1, 5, -1, 7, -1, 8, -1, 8, 7, 5, 7, 9, 12, 14, 9, 12, 7, 9, 5, 7, 5, 9, -1, 12, -1, 14, 9, 12, 7, 9, 5, 8, 9, 8, 7, 5, 7, 8, -1, 5, 7, 9, 12, 7, 9, 7, 5, 7, -1, 5, -1, 7, -1, 5, -1, 0, 2, 5, -1, 0, 2, 5, 7, 9, 5, 10, 9, 10, 12, 5, -1, 5, -1, 0, 2, 5, 0, 10, 9, 7, 5, 0, 9, 10, 0, 5, -1, 0, 2, 5, -1, 0, 2, 5, 5, 7, 9, 5, 0, 2, 0, 5, -1, 5, 4, 5, 0, 2, 5, 10, 9, 10, 12, 5, -1, 4, -1, 5, -1, 0, 2, 5, -1, 0, 2, 5, 7, 9, 5, 10, 9, 10, 12, 5, -1, 5, -1, 0, 2, 5, 0, 10, 9, 7, 5, 0, 9, 10, 0, 5, -1, 0, 2, 5, -1, 0, 2, 5, 5, 7, 9, 5, 0, 2, 0, 5, -1, 5, 4, 5, 0, 2, 5, 10, 9, 10, 12, 5, -1, 7, -1)) {
            notes[i] = integer;
            i++;
        }
        i = 0;
        for (Integer integer : Arrays.asList(10, -1, 22, -1, 12, -1, 24, -1, 9, -1, 21, -1, 14, -1, 14, -1, 7, -1, 19, -1, 12, -1, 24, -1, 5, -1, 17, -1, 5, -1, 17, -1, 10, -1, 22, -1, 7, -1, 19, -1, 9, -1, 21, -1, 14, -1, 14, -1, 7, -1, 19, -1, 12, -1, 24, -1, 5, -1, 17, -1, 5, -1, 17, -1, 10, -1, 22, -1, 12, -1, 24, -1, 9, -1, 21, -1, 14, -1, 14, -1, 7, -1, 19, -1, 12, -1, 24, -1, 5, -1, 17, -1, 5, -1, 17, -1, 10, -1, 22, -1, 7, -1, 19, -1, 9, -1, 21, -1, 14, -1, 14, -1, 7, -1, 19, -1, 12, -1, 24, -1, 5, -1, 17, -1, 5, -1, 17, -1, 10, -1, 14, -1, 17, -1, 22, -1, 9, -1, 12, -1, 17, -1, 21, -1, 7, -1, 10, -1, 14, -1, 17, -1, 5, -1, 9, -1, 12, -1, 17, -1, 10, -1, 14, -1, 17, -1, 22, -1, 9, -1, 12, -1, 17, -1, 21, -1, 7, -1, 10, -1, 14, -1, 17, -1, 5, -1, 9, -1, 12, -1, 17, -1, 10, -1, 14, -1, 17, -1, 22, -1, 9, -1, 12, -1, 17, -1, 21, -1, 7, -1, 10, -1, 14, -1, 17, -1, 5, -1, 9, -1, 12, -1, 17, -1, 10, -1, 14, -1, 17, -1, 22, -1, 9, -1, 12, -1, 17, -1, 21, -1, 7, -1, 10, -1, 14, -1, 17, -1, 5, -1, 9, -1, 12, -1, 17, -1)) {
            notes_sub[i] = integer;
            i++;
        }
        i = 0;
        for (Integer integer : Arrays.asList(9, 10, 12, -1, 17, -1, 9, 10, 12, 17, 19, 21, 19, 16, 17, -1, 12, -1, 9, 10, 12, -1, 17, -1, 19, 16, 17, 19, 22, 21, 22, 19)) {
            introNotes[i] = integer;
            i++;
        }
        i = 0;
        for (Integer integer : Arrays.asList(17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1, 17, -1, -1, -1)) {
            introNotes_sub[i] = integer;
            i++;
        }
        for (int j = -12; j <= 12; j++) {
            pitches[j + 12] = (float) Math.pow(2, j / 12f);
        }
        getServer().getPluginManager().registerEvents(this, this);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            new IntroTask(onlinePlayer).runTaskTimerAsynchronously(this, 30, 2);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        new IntroTask(event.getPlayer()).runTaskTimerAsynchronously(this, 10, 2);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private class IntroTask extends BukkitRunnable {

        private int count = 0;

        private final Player player;

        protected IntroTask(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            int note = introNotes[count];
            if (note != -1) {
                PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(new SoundEffect(new MinecraftKey("block.note.harp")), SoundCategory.RECORDS,player.getLocation().getX(), player.getLocation().getY(),player.getLocation().getZ(),2f, pitches[note] );
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                //player.playSound(player.getLocation(), MUSIC_SOUND, 2f, pitches[note]);
            }
            int note_sub = introNotes_sub[count];
            if (note_sub != -1) {
                PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(new SoundEffect(new MinecraftKey("block.note.bass")), SoundCategory.RECORDS,player.getLocation().getX(), player.getLocation().getY(),player.getLocation().getZ(),1f, pitches[note_sub] );
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                //player.playSound(player.getLocation(), MUSIC_SOUND_INTRO_SUB, 1f, pitches[note_sub]);
            }
            count++;
            if (count == 32) {
                new MusicTask(player).runTaskTimerAsynchronously(NyanCatBGM.instance, 0, 2);
                cancel();
            }
            if (!player.isOnline()) this.cancel();
        }
    }

    private class MusicTask extends BukkitRunnable {
        private int count = 0;

        private final Player player;

        protected MusicTask(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            int note = notes[count];
            if (note != -1) {
                PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(new SoundEffect(new MinecraftKey("block.note.harp")), SoundCategory.RECORDS,player.getLocation().getX(), player.getLocation().getY(),player.getLocation().getZ(),2f, pitches[note] );
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                //player.playSound(player.getLocation(), MUSIC_SOUND, 2f, pitches[note]);
            }
            int note_sub = notes_sub[count];
            if (note_sub != -1) {
                PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(new SoundEffect(new MinecraftKey("block.note.harp")), SoundCategory.RECORDS,player.getLocation().getX(), player.getLocation().getY(),player.getLocation().getZ(),0.5f, pitches[note] );
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                //player.playSound(player.getLocation(), MUSIC_SOUND_SUB, 0.5f, pitches[note_sub]);
            }
            count++;
            if (count == 256) {
                count = 0;
            }
            if (!player.isOnline()) this.cancel();
        }
    }
}
