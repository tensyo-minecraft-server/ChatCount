package moe.nmkmn.chat_count;

import moe.nmkmn.chat_count.utils.Cache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class ChatCount extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Load Cache
        Cache cache = new Cache(this);
        cache.checkCache("ChatCount.json");

        // Event Listeners
        getServer().getPluginManager().registerEvents(this, this);
    }

    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add("w");
        words.add("ｗ");
        words.add("草");

        return words;
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        int count = 0;
        Cache cache = new Cache(this);
        Long cacheValue = cache.getCache("ChatCount", player.getUniqueId().toString());

        String[] ary = event.getMessage().split("");

        for (String s : ary) {
            System.out.print(s + " ");
        }

        for (String s : ary) {
            if (getWords().contains(s)) {
                count++;
            }
        }

        if (cacheValue != null) {
            cache.saveCache(player, "ChatCount", cacheValue + count);
        } else {
            cache.saveCache(player, "ChatCount", count);
        }
    }
}
