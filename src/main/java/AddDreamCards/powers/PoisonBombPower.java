package AddDreamCards.powers;

import AddDreamCards.DreamCards;
import AddDreamCards.util.TexLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static AddDreamCards.DreamCards.makeID;

public class PoisonBombPower extends TwoAmountPower implements NonStackablePower {
    public static String ID2 = makeID(PoisonBombPower.class.getSimpleName());

    public PoisonBombPower(int amount) {
        this.ID = ID2;
        this.isTurnBased = true;
        this.name = "Poison Bomb";
        this.owner = AbstractDungeon.player;
        this.amount2 = amount;
        this.amount = 3;
        this.type = PowerType.BUFF;

        Texture normalTexture = TexLoader.getTexture(DreamCards.modID + "Resources/images/powers/PoisonBomb_power32.png");
        Texture hiDefImage = TexLoader.getTexture(DreamCards.modID + "Resources/images/powers/PoisonBomb_power84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new ReducePowerAction(owner, owner, this, 1));
        if (this.amount == 1) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new ApplyPowerAction(m, owner, new PoisonPower((AbstractCreature) m, owner, amount2), amount2));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = "At the end of #b" + amount + " turns, apply #b" + amount2 + " #yPoison to ALL enames.";
    }
}
