package AddDreamCards.cards;

import AddDreamCards.DreamCards;
import AddDreamCards.powers.PoisonBombPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.ElectroPower;
import com.megacrit.cardcrawl.powers.FocusPower;

public class LightingSpin extends CustomCard {

    // TEXT DECLARATION

    public static final String ID = DreamCards.makeID(LightingSpin.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = DreamCards.makeImagePath("cards/LightingSpin.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.BLUE;

    private static final int COST = 5;

    public LightingSpin() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 3;
        baseDamage = 14;
        exhaust = true;
    }

    public void applyPowers() {
    }

    public void calculateCardDamage(AbstractMonster mo) {
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            addToBot(new ChannelAction(new Lightning()));
        addToBot(new ApplyPowerAction(p, p, new FocusPower(p, damage), damage));
        addToBot(new ApplyPowerAction(p, p, new ElectroPower(p), 1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDamage(1);
            initializeDescription();
        }
    }
}
