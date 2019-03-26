package blacksmith;

import basemod.helpers.RelicType;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import blacksmith.relics.ForgeRelic;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.BaseMod;
import blacksmith.cards.BigDamage;

import java.nio.charset.StandardCharsets;


@SpireInitializer
public class BlacksmithMod implements EditCardsSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {
    public static final Logger logger = LogManager.getLogger(BlacksmithMod.class.getName());

    public static final String ASSETS_FOLDER = "images";
    public static final com.badlogic.gdx.graphics.Color YELLOW = com.megacrit.cardcrawl.helpers.CardHelper.getColor(216.0f, 158.0f, 66.0f);
    public static final String ATTACK_YELLOW = "cardui/512/bg_attack_yellow.png";
    public static final String POWER_YELLOW = "cardui/512/bg_power_yellow.png";
    public static final String SKILL_YELLOW = "cardui/512/bg_skill_yellow.png";

    public static final String ATTACK_YELLOW_P = "cardui/1024/bg_attack_yellow.png";
    public static final String POWER_YELLOW_P = "cardui/1024/bg_power_yellow.png";
    public static final String SKILL_YELLOW_P = "cardui/1024/bg_skill_yellow.png";

    public BlacksmithMod() {
        BaseMod.subscribe(this);
    }
    public static void initialize() {
        new BlacksmithMod();
    }

    public String assetPath(String resource) {
        return ASSETS_FOLDER + "/" + resource;
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new BigDamage());
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Loading strings");
        String cardStrings = Gdx.files.internal("localization/eng/CardStrings.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        String uiStrings = Gdx.files.internal("localization/eng/UIStrings.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        String relicStrings = Gdx.files.internal("localization/eng/RelicStrings.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        BaseMod.loadCustomStrings(UIStrings.class, uiStrings);
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        logger.info("String loading done");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Loading relics");
        BaseMod.addRelic(new ForgeRelic(), RelicType.SHARED);
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("Loading Keywords");
        final Gson gson = new Gson();
        String language = "eng";
        final String json = Gdx.files.internal("localization/" + language + "/KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        final com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = (com.evacipated.cardcrawl.mod.stslib.Keyword[])gson.fromJson(json, (Class) com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        if (keywords != null) {
            for (final com.evacipated.cardcrawl.mod.stslib.Keyword keyword: keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
