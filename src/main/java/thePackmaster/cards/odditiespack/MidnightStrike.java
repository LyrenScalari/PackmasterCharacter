package thePackmaster.cards.odditiespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MidnightStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("MidnightStrike");
    // intellij stuff attack, enemy, rare, 12, , , , , 

    public MidnightStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 12;
        baseSecondDamage = 60;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() == 12) {
            //TODO: THE COOLEST VFX YOU HAVE EVER SEEN IN YOUR LIFE!!!
            altDmg(m, AbstractGameAction.AttackEffect.FIRE);
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        int count = AbstractDungeon.actionManager.cardsPlayedThisCombat.size();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();

        loadCardImage("anniv5Resources/images/cards/MidnightStrike" + ((count) % 13 + 1) + ".png");
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }


    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() == 11 ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}