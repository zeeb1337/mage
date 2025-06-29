package mage.abilities.effects.common.replacement;

import mage.abilities.Ability;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.NumberOfTriggersEvent;
import mage.util.CardUtil;

/**
 * @author TheElk801
 */
public class AdditionalTriggerControlledETBReplacementEffect extends ReplacementEffectImpl {

    public AdditionalTriggerControlledETBReplacementEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "If a permanent entering causes a triggered ability " +
                "of a permanent you control to trigger, that ability triggers an additional time";
    }

    private AdditionalTriggerControlledETBReplacementEffect(final AdditionalTriggerControlledETBReplacementEffect effect) {
        super(effect);
    }

    @Override
    public AdditionalTriggerControlledETBReplacementEffect copy() {
        return new AdditionalTriggerControlledETBReplacementEffect(this);
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.NUMBER_OF_TRIGGERS;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        // Only triggers for the source controller
        if (!source.isControlledBy(event.getPlayerId())) {
            return false;
        }
        GameEvent sourceEvent = ((NumberOfTriggersEvent) event).getSourceEvent();
        // Only EtB triggers
        if (sourceEvent == null
                || sourceEvent.getType() != GameEvent.EventType.ENTERS_THE_BATTLEFIELD
                || !(sourceEvent instanceof EntersTheBattlefieldEvent)) {
            return false;
        }
        // Only for triggers of permanents
        return game.getPermanent(event.getSourceId()) != null;
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        event.setAmount(CardUtil.overflowInc(event.getAmount(), 1));
        return false;
    }
}
