# snake-and-ladders

A Clojure library designed to ... well, that part is up to you.
Approach

Throughout the game fields on the board are reffered through their indices from 0 - 99
## Assumptions
You cannot chain slides and climbs
When the player lands on the same snake more then once we keep track of each slide
Landing field from a snake or ladder will not be the beginning of snake or ladder
A roll can be lucky and unlucky at the same time
A single roll can be lucky for more then one reason but will be counted just once
A roll is lucky if we land next to snake without stepping on it when we get off the ladder if there is a ladder
A roll is lucky and unlucky if we land on a snake entry point that is just next to another snake entrypoints

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
