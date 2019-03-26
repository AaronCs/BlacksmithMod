package blacksmith.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ForgeRelic extends CustomRelic {
    public static final String ID = "BlacksmithMod:ForgeRelic";
    public static final String IMG_PATH = "images/relics/forgeRelic.png";

    public ForgeRelic() {
        super(ID, new Texture(IMG_PATH), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ForgeRelic();
    }
}
