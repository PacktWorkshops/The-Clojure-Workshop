# test-app

Sample figwheel project with tests

## Development

To get an interactive development environment run:

    lein fig:build

This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

Check tests in a web browser http://localhost:9500/figwheel-extra-main/auto-testing

To clean all compiled files:

	lein clean

To run tests:

	lein clean
	lein fig:test