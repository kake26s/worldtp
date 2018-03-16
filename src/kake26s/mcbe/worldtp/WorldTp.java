package kake26s.mcbe.worldtp;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;


public class WorldTp extends PluginBase{

    private static final String WORLDTP = TextFormat.GREEN + "[WorldTp]" + TextFormat.RESET;


    @Override
    public void onEnable(){
        this.getLogger().info(TextFormat.GREEN + "読み込みました" + TextFormat.RESET);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player pl = (Player)sender;

        switch (command.getName()){
            case "worldtp":
                if (sender instanceof ConsoleCommandSender) {
                    sender.sendMessage("コンソールから実行することはできません");
                    return false;
                }
                if(args.length == 0) {
                    pl.sendMessage(WORLDTP + "ワールド名を指定してください");
                    return false;
                }

                String world_name = args[0];
                if(this.getServer().loadLevel(world_name)){
                    pl.teleport(this.getServer().getLevelByName(world_name).getSpawnLocation());
                    pl.sendMessage(WORLDTP + "テレポートが完了しました");
                    return true;
                }else{
                    pl.sendMessage(WORLDTP + "指定したワールドはテレポートできない状態です");
                }
                break;
        }
        return false;
    }
}
