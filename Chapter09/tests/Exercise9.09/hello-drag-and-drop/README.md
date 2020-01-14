# hello-drag-and-drop

Testing an application using JavaScript interop implementing drag-and-drop.

## Testing

    lein fig:test

## Development

To get an interactive development environment run:

    lein fig:build

This will auto compile and send all changes to the browser without the
need to reload. Open your browser at [http://localhost:9500/](http://localhost:9500/).
After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

	lein clean

To create a production build run:

	lein clean
	lein fig:min