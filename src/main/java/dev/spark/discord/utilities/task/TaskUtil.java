package dev.spark.discord.utilities.task;

import dev.spark.discord.SparkDiscord;
import lombok.experimental.UtilityClass;
import org.bukkit.scheduler.BukkitRunnable;

@UtilityClass
public class TaskUtil {

    public void run(Runnable runnable) {
        SparkDiscord.get().getServer().getScheduler().runTask(SparkDiscord.get(), runnable);
    }

    public void runTimer(Runnable runnable, long delay, long timer) {
        SparkDiscord.get().getServer().getScheduler().runTaskTimer(SparkDiscord.get(), runnable, delay, timer);
    }

    public void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(SparkDiscord.get(), delay, timer);
    }

    public void runTimerAsync(Runnable runnable, long delay, long timer) {
        SparkDiscord.get().getServer().getScheduler().runTaskTimerAsynchronously(SparkDiscord.get(), runnable, delay, timer);
    }

    public void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimerAsynchronously(SparkDiscord.get(), delay, timer);
    }

    public void runLater(Runnable runnable, long delay) {
        SparkDiscord.get().getServer().getScheduler().runTaskLater(SparkDiscord.get(), runnable, delay);
    }

    public void runLaterAsync(Runnable runnable, long delay) {
        try {
            SparkDiscord.get().getServer().getScheduler().runTaskLaterAsynchronously(SparkDiscord.get(), runnable, delay);
        } catch (IllegalStateException e) {
            SparkDiscord.get().getServer().getScheduler().runTaskLater(SparkDiscord.get(), runnable, delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runTaskTimerAsynchronously(Runnable runnable, int delay) {
        try {
            SparkDiscord.get().getServer().getScheduler().runTaskTimerAsynchronously(SparkDiscord.get(), runnable, 20L * delay, 20L * delay);
        } catch (IllegalStateException e) {
            SparkDiscord.get().getServer().getScheduler().runTaskTimer(SparkDiscord.get(), runnable, 20L * delay, 20L * delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runAsync(Runnable runnable) {
        try {
            SparkDiscord.get().getServer().getScheduler().runTaskAsynchronously(SparkDiscord.get(), runnable);
        } catch (IllegalStateException e) {
            SparkDiscord.get().getServer().getScheduler().runTask(SparkDiscord.get(), runnable);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}