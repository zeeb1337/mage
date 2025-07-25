package mage.cards.i;

import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.SourceMatchesFilterCondition;
import mage.abilities.effects.common.InfoEffect;
import mage.abilities.effects.common.continuous.GainAbilityControllerEffect;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterPermanent;
import mage.filter.predicate.permanent.TokenPredicate;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class ImperialMask extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("it's not a token");

    static {
        filter.add(TokenPredicate.FALSE);
    }

    private static final Condition condition = new SourceMatchesFilterCondition(filter);

    public ImperialMask(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{4}{W}");

        // When Imperial Mask enters the battlefield, if it's not a token, each of your teammates puts a token that's a copy of Imperial Mask onto the battlefield.
        // No implementation of teammates currently, so no effect needed
        this.addAbility(new EntersBattlefieldTriggeredAbility(new InfoEffect(
                "each of your teammates creates a token that's a copy of {this}"
        ), false).withInterveningIf(condition));

        // You have hexproof.
        this.addAbility(new SimpleStaticAbility(new GainAbilityControllerEffect(HexproofAbility.getInstance())));
    }

    private ImperialMask(final ImperialMask card) {
        super(card);
    }

    @Override
    public ImperialMask copy() {
        return new ImperialMask(this);
    }
}
