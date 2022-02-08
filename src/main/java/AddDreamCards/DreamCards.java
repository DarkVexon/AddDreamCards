package AddDreamCards;

import AddDreamCards.cards.LightingSpin;
import AddDreamCards.cards.PoisonBomb;
import AddDreamCards.cards.SowrdThrow;
import basemod.BaseMod;
import basemod.devcommands.unlock.Unlock;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class DreamCards implements PostInitializeSubscriber,
    EditStringsSubscriber, EditKeywordsSubscriber,
    EditCardsSubscriber {

    public static final String modID = "dreamcards"; //TODO: Change this.

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public DreamCards() {
        BaseMod.subscribe(this);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static void initialize() {
        DreamCards thismod = new DreamCards();
    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new SowrdThrow());
        BaseMod.addCard(new PoisonBomb());
        BaseMod.addCard(new LightingSpin());
        UnlockTracker.markCardAsSeen(SowrdThrow.ID);
        UnlockTracker.markCardAsSeen(PoisonBomb.ID);
        UnlockTracker.markCardAsSeen(LightingSpin.ID);
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/loc/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/loc/eng/Cardstrings.json");
    }
}
