package blacksmith.patches;

import basemod.ReflectionHacks;
import blacksmith.relics.ForgeRelic;
import blacksmith.ui.ForgeCardBonfireOption;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

@SpirePatch(clz = CampfireUI.class, method="initializeButtons")
public class CampfirePatch {
    public static void Prefix(CampfireUI obj) {
        final ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate((Object) obj, (Class) CampfireUI.class, "buttons");
        // If player has blacksmith relic, add forge buttons
        if (AbstractDungeon.player.hasRelic(ForgeRelic.ID)) {
            campfireButtons.add(new ForgeCardBonfireOption(AbstractDungeon.player.masterDeck.getUpgradableCards().size() > 0 && !AbstractDungeon.player.hasRelic("Fusion Hammer")));
        }
    }
}
