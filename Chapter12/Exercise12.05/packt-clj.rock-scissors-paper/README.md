# packt-clj.rock-scissors-paper

A simple Clojurescript implementation of the famous Rock, Scissors, Paper game.

## Overview

Take the world by storm with this flash, text-based, interactive game!

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## Testing

In your Clojurescript REPL, you can run the tests by loading the `core_test.cljs` file:

```
dev:cljs.user=> (load-file "test/packt_clj/rock_scissors_paper/core_test.cljs")

Testing packt-clj.rock-scissors-paper.core-test
                
Ran 3 tests containing 7 assertions.
0 failures, 0 errors.
dev:cljs.user=> 
```







