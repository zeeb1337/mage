package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.IsStepCondition;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.ReturnSourceFromGraveyardToBattlefieldEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.triggers.BeginningOfUpkeepTriggeredAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;

import java.util.UUID;

/**
 * @author Loki
 */
public final class FiremaneAngel extends CardImpl {

    public FiremaneAngel(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{R}{W}{W}");
        this.subtype.add(SubType.ANGEL);

        this.power = new MageInt(4);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Firststrike
        this.addAbility(FirstStrikeAbility.getInstance());

        // At the beginning of your upkeep, if Firemane Angel is in your graveyard or on the battlefield, you may gain 1 life.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(
                Zone.ALL, TargetController.YOU, new GainLifeEffect(1), true
        ).withInterveningIf(FiremaneAngelCondition.instance));

        // {6}{R}{R}{W}{W}: Return Firemane Angel from your graveyard to the battlefield. Activate this ability only during your upkeep.
        this.addAbility(new ConditionalActivatedAbility(
                Zone.GRAVEYARD, new ReturnSourceFromGraveyardToBattlefieldEffect(false, false),
                new ManaCostsImpl<>("{6}{R}{R}{W}{W}"), new IsStepCondition(PhaseStep.UPKEEP), null
        ));
    }

    private FiremaneAngel(final FiremaneAngel card) {
        super(card);
    }

    @Override
    public FiremaneAngel copy() {
        return new FiremaneAngel(this);
    }
}

enum FiremaneAngelCondition implements Condition {
    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        return game.getState().getZone(source.getSourceId()) == Zone.GRAVEYARD
                || game.getState().getZone(source.getSourceId()) == Zone.BATTLEFIELD;
    }

    @Override
    public String toString() {
        return "{this} is in your graveyard or on the battlefield";
    }
}
