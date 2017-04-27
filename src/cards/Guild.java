package cards;

import effects.Effect;

import java.awt.*;

/**
 * Created by mkrec_000 on 27/04/2017.
 */
public class Guild extends Card{
    private Effect guildEffect;

    public Guild(String type, String name, String price, int minimumPlayers, Image picture, Effect guildEffect) {
        super(type, name, price, minimumPlayers, picture);
        this.guildEffect=guildEffect;
    }

    public Effect getGuildEffect() {
        return guildEffect;
    }

    public void setGuildEffect(Effect guildEffect) {
        this.guildEffect = guildEffect;
    }
}
