package blacksmith.ui;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSmithEffect;

public class ForgeCardBonfireOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BlacksmithMod:ForgeCardOption");
    public static final String[] TEXT = uiStrings.TEXT;
    public ForgeCardBonfireOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        // TODO: Make only active if one has the blacksmith relic.
        if (active) {
            this.description = TEXT[1];
            this.img = ImageMaster.loadImage("images/ui/smith.png");
        }
        else {
            this.description = TEXT[2];
            this.img = ImageMaster.loadImage("images/ui/smithDisabled.png");
        }
    }

    @Override
    public void useOption() {
        if (this.usable) {
            // TODO: Change this to CampfireForgeEffect
            AbstractDungeon.effectList.add(new CampfireSmithEffect());
        }
    }
}
