
package mage.cards.b;

import mage.MageInt;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.SacrificeSourceEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.MorphAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.triggers.BeginningOfEndStepTriggeredAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;

import java.util.UUID;

/**
 * @author LevelX2
 */
public final class BlisteringFirecat extends CardImpl {

    public BlisteringFirecat(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{R}{R}");
        this.subtype.add(SubType.ELEMENTAL, SubType.CAT);

        this.power = new MageInt(7);
        this.toughness = new MageInt(1);

        // Trample
        this.addAbility(TrampleAbility.getInstance());

        // Haste
        this.addAbility(HasteAbility.getInstance());

        // At the beginning of the end step, sacrifice Blistering Firecat.
        this.addAbility(new BeginningOfEndStepTriggeredAbility(
                TargetController.NEXT, new SacrificeSourceEffect(), false
        ));

        // Morph {R}{R}
        this.addAbility(new MorphAbility(this, new ManaCostsImpl<>("{R}{R}")));
    }

    private BlisteringFirecat(final BlisteringFirecat card) {
        super(card);
    }

    @Override
    public BlisteringFirecat copy() {
        return new BlisteringFirecat(this);
    }
}
