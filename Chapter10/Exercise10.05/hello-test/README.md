# hello-test

Project showing how to do tests in ClojureScript. Using Bensudoo

# Setup

Install Karma library for testing locally:

    npm install karma karma-cljs-test --save-dev

Install required launchers:

    npm install karma-chrome-launcher karma-firefox-launcher --save-dev
    npm install karma-safari-launcher karma-opera-launcher --save-dev
    npm install karma-ie-launcher --save-dev

Install cli tool for running Karma:

    npm install -g karma-cli

# Running

    lein doo chrome test