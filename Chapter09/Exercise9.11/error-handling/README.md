# error-handling

Project showing error handling in ClojureSript.

## Setup

Most of the following scripts require [rlwrap](http://utopia.knoware.nl/~hlub/uck/rlwrap/) (on OS X installable via brew).

Build your project once in dev mode with the following script and then open `index.html` in your browser.

    ./scripts/build

To auto build your project in dev mode:

    ./scripts/watch

To start an auto-building Node REPL:

    ./scripts/repl
    
For more info using the browser as a REPL environment, see
[this](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).
    
Clean project specific out:

    lein clean