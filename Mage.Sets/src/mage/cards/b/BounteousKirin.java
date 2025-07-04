
package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.stack.Spell;
import mage.players.Player;

import java.util.UUID;

/**
 * @author LevelX2
 */
public final class BounteousKirin extends CardImpl {

    public BounteousKirin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{G}{G}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.KIRIN, SubType.SPIRIT);

        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        // Whenever you cast a Spirit or Arcane spell, you may gain life equal to that spell's converted mana cost.
        this.addAbility(new SpellCastControllerTriggeredAbility(
                new BounteousKirinEffect(), StaticFilters.FILTER_SPELL_SPIRIT_OR_ARCANE,
                true, SetTargetPointer.SPELL
        ));
    }

    private BounteousKirin(final BounteousKirin card) {
        super(card);
    }

    @Override
    public BounteousKirin copy() {
        return new BounteousKirin(this);
    }
}

class BounteousKirinEffect extends OneShotEffect {

    BounteousKirinEffect() {
        super(Outcome.GainLife);
        this.staticText = "you may gain life equal to that spell's mana value";
    }

    private BounteousKirinEffect(final BounteousKirinEffect effect) {
        super(effect);
    }

    @Override
    public BounteousKirinEffect copy() {
        return new BounteousKirinEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Spell spell = game.getSpellOrLKIStack(this.getTargetPointer().getFirst(game, source));
        if (spell != null) {
            Player controller = game.getPlayer(source.getControllerId());
            if (controller != null) {
                int life = spell.getManaValue();
                controller.gainLife(life, game, source);
                return true;
            }
        }
        return false;
    }
}
