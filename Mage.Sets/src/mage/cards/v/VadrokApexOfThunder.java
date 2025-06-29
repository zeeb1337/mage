package mage.cards.v;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.MutatesSourceTriggeredAbility;
import mage.abilities.effects.common.MayCastTargetCardEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.MutateAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class VadrokApexOfThunder extends CardImpl {

    private static final FilterCard filter = new FilterCard(
            "noncreature card with mana value 3 or less from your graveyard"
    );

    static {
        filter.add(Predicates.not(CardType.CREATURE.getPredicate()));
        filter.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, 4));
    }

    public VadrokApexOfThunder(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}{R}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.DINOSAUR);
        this.subtype.add(SubType.CAT);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Mutate {1}{W/U}{R}{R}
        this.addAbility(new MutateAbility(this, "{1}{W/U}{R}{R}"));

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // First strike
        this.addAbility(FirstStrikeAbility.getInstance());

        // Whenever this creature mutates, you may cast target noncreature card with converted mana cost 3 or less from your graveyard without paying its mana cost.
        Ability ability = new MutatesSourceTriggeredAbility(new MayCastTargetCardEffect(CastManaAdjustment.WITHOUT_PAYING_MANA_COST));
        ability.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability);
    }

    private VadrokApexOfThunder(final VadrokApexOfThunder card) {
        super(card);
    }

    @Override
    public VadrokApexOfThunder copy() {
        return new VadrokApexOfThunder(this);
    }
}
