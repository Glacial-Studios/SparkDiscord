package dev.panda.discord.utilities.task;

import dev.panda.discord.PandaDiscord;
import lombok.experimental.UtilityClass;
import org.bukkit.scheduler.BukkitRunnable;

@UtilityClass
public class TaskUtil {

    public void run(Runnable runnable) {
        PandaDiscord.get().getServer().getScheduler().runTask(PandaDiscord.get(), runnable);
    }

    public void runTimer(Runnable runnable, long delay, long timer) {
        PandaDiscord.get().getServer().getScheduler().runTaskTimer(PandaDiscord.get(), runnable, delay, timer);
    }

    public void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(PandaDiscord.get(), delay, timer);
    }

    public void runTimerAsync(Runnable runnable, long delay, long timer) {
        PandaDiscord.get().getServer().getScheduler().runTaskTimerAsynchronously(PandaDiscord.get(), runnable, delay, timer);
    }

    public void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimerAsynchronously(PandaDiscord.get(), delay, timer);
    }

    public void runLater(Runnable runnable, long delay) {
        PandaDiscord.get().getServer().getScheduler().runTaskLater(PandaDiscord.get(), runnable, delay);
    }

    public void runLaterAsync(Runnable runnable, long delay) {
        try {
            PandaDiscord.get().getServer().getScheduler().runTaskLaterAsynchronously(PandaDiscord.get(), runnable, delay);
        } catch (IllegalStateException e) {
            PandaDiscord.get().getServer().getScheduler().runTaskLater(PandaDiscord.get(), runnable, delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runTaskTimerAsynchronously(Runnable runnable, int delay) {
        try {
            PandaDiscord.get().getServer().getScheduler().runTaskTimerAsynchronously(PandaDiscord.get(), runnable, 20L * delay, 20L * delay);
        } catch (IllegalStateException e) {
            PandaDiscord.get().getServer().getScheduler().runTaskTimer(PandaDiscord.get(), runnable, 20L * delay, 20L * delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runAsync(Runnable runnable) {
        try {
            PandaDiscord.get().getServer().getScheduler().runTaskAsynchronously(PandaDiscord.get(), runnable);
        } catch (IllegalStateException e) {
            PandaDiscord.get().getServer().getScheduler().runTask(PandaDiscord.get(), runnable);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}