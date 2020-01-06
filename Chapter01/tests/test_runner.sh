#!/bin/bash

# no clojure namespaces or project etc. at the moment so using this simple runner
for f in $(echo $PWD/*/);
do
 cd $f;
 echo Running tests in $f
 clj repl.clj;
done
