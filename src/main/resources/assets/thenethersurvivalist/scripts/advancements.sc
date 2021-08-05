__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_try_switch_on_a_portal(block) ->
run('advancement grant @p only tns:trapped_forever');

__on_bedrock_break(block) ->
run('advancement grant @p only tns:cheater');