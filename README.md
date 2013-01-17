snake-contest
=============

Snake Java Programming Contest

Goal
----
The goal of this contest is twofold:

* Having fun
* Encouraging social and scientific exchanges among DIUF-members in the largest sense (which should as well be a lot of fun).

Task for contestants
--------------------
Write a Java-Method which pilots a snake. At every time step your method will be called and your method will return the direction in which the snake shall advance in the next step. This method will have a complete access to a copy of the playing field.

To get started download the Source Code, which contains a very basic strategy, called SimpleStrategy. Also generate the JavaDoc for more information.

Tournament rules
----------------
If possible (time-wise), for every possible combination of four strategies and for every field (may be more than 5) 4 rounds will be played, with a cyclic permutation of the starting position. Otherwise, teams of fours strategies will be randomly sampled. The ranking

The following rules are specified in Parameters.java:
<table>
<tr><td>Field size</td><td>40x30</td></tr>
<tr><td>Number of goodies on field</td><td>1</td></tr>
<tr><td>Number of goodies until game over</td><td>100</td></tr>
<tr><td>Number of steps until game over</td><td>3000</td></tr>
<tr><td>Points for a goodie</td><td>300</td></tr>
<tr><td>Points for surviving until game over</td><td>500</td></tr>
<tr><td>Points for "killing" another snake</td><td>1000</td></tr>
<tr><td>Points for bumping into another snake or a wall</td><td>-500</td></tr>
<tr><td>Points for suicide</td><td>-1000</td></tr>
<tr><td>Length increase per goodie</td><td>5</td></tr>
<tr><td>Points earned per step</td><td>1</td></tr>
</table>

At each step, every snake advances exactly one step in the direction computed by the method "computeNextStep()". If one cannot walk on the next field-element, the snake dies. Its tail continues to retract until the snake has vanished from the field.

A snake must move at each step (it cannot stand still), and it should not move backwards (it dies immediately since it crushes into itsself).

A snake starts at 0 points with length 5.

Each snake-element on the field has a counter, for how many more steps it remains on the field (you can see this value with Element.getTimer()) That means, if a snake dies, its head stops to advance and its tail follows up until the whole snake disapears.

If a snake eats a goodie, the tail continues to advance until the position of the goodie, where it stays for 5 steps.

A snake A is "killed" by another snake B, if snake A bumps into the body of snake B.

If a snake bumps into itself, it is considered suicide.

If two snakes bump into each other head to head, both loose points and do not get points rewarded for killing another snake.

At each step, computeNextStep() is called once. No more than 20ms real time can be spent! If a Strategy exceeds this time limit, its snake dies.

If unsure, you may measure time with

    // at the beginning of your method
    long timeStarted = System.nanoTime();
    // do something
    // check available time
    if (System.nanoTime() - timeUsed > 1e6*20) {
        // time is up, terminate as quickly as possible!
    }
      

or regularily check

    if (Thread.interrupted()) {
        // finish your computations immediately
    }
      

Strategies being caught in endless loops (i.e. not terminating for more than 1s) will be permanently disqualified.

A snake dies if the next position is neither empty nor a goodie or if the next position would be occupied by two heads simultanously. Then both snakes die, of course.

Origins
-------
The almost same code base has been used for the 1st-year course "Object oriented programming". This gives a little advantage to some talented 1st-year students. This is intended ;-) 
