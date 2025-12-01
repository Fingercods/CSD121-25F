reflection 2.5

I could return a humanplayer even tho the method says it returns player because humanplayer is a type of player.
java lets you use a subclass anywhere the parent class is expected.
so basically humanplayer is a player, so the return type still works fine

reflection 2.9

the error came because i moved picknextmove out of the player class and then tictactoegame could not find it anymore.
the variable “whoseturn” is typed as player so it needs player to have that method.
when I added it back as an abstract method the error stopped because now every player has to overrid.

reflection 5

i did not need to change main or tictactoegame because they only use the player type.
they don't care if it’s human, first, random or omala since all of them override picknextmove.
polymorphism makes it so the game just calls the same method and each player does their own thing.
the only place I need to add new stuff was the console where i choose which player to create.
