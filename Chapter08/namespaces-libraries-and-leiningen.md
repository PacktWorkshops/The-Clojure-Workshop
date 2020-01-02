# Part three - Working in Clojure and Clojurescript

Clojure was designed from the beginning to be a very practical language. Getting things done
means interacting with the outside world, building projects, using libraries and deploying your
work. Clojure and Clojurescript are hosted languages which can lead to more complex
workflows. Interactions with the host platform create complexity, and this can be an obstacle to
learning. The goal of Part Three is to ensure that students are comfortable accomplishing the
essential tasks of creating, building, testing, deploying and running projects in Clojure and
Clojurescript.

# Chapter Nine: Namespaces, libraries, and leiningen
Learning Objectives

By the end of this lesson, you will be able to:
  * Run and use the REPL
  * Work with Clojure namespaces
  * a more thorough introduction to leiningen and project.clj
  * Using Clojure dependencies in your code
  * Building and executing a Javascript executable at the command-line

## Introduction
In a real-world project, you won’t write all the code. External dependencies are a crucial part of
any project, and in this lesson, we’ll learn how to bring them into your project and your code. The first step is to understand how Clojure namespaces work in general. Then we’ll look at the project level, and using leiningen and your `project.clj` file to pull everything together, into a Javascript executable. Moreover, finally, we’ll take a look at some of the conveniences that leiningen provides throughout the lifecycle of a project.

In this lesson, we familiarize ourselves with the REPL. The REPL, short for "Read-Eval-Print
Loop" is an integral part of the workflow when writing code in Clojure. You receive immediate
feedback on the code as you are writing it. Many editors also support direct integration with the
REPL, enabling the editor to evaluate Clojure statements itself, and printing the output.
However, in this lesson, we use leiningen from the command line to run the REPL.

### Exercise 1: Starting An Interactive Session with Clojure
To start an interactive session with Clojure, perform the following steps:

1. Open the Command Prompt
2. Type the following command and press _Enter_ to start the REPL.
   ```
   lein run
   ```

It might take some time until the input prompt appears due to the JVM loading in the background and its slower start-up times. The
output will be as follows:

The last line, user=&gt;, tells you that you’re in the user namespace. Namespaces are the
core organizational unit of structuring your Clojure application code.
Later we will see how to organize our code in files and group together related functions
and pieces of data.

### Exercise 2: Navigating namespaces
In this exercise, we will explore more the concept of namespaces using the REPL.
1. At `the user=>` prompt enter the following:
   ```clojure
   (in-ns 'my-namespace)
   ```
   You should observe at the prompt something like `#object[clojure.lang.Namespace.0x25a6348f]` being returned. The function `in-ns` takes a symbol and creates a namespace with that name if it does not already exist, and switches into that namespace directly. Now you can observe that the prompt has changed to `my-namespace=>` Anything you declare inside of this namespace will be stored inside of it.
2. Declare a var named herbs as follows:
   ```clojure
   (def herbs ["coriander" "basil" "mint" "sage"])
   ```
This creates a var named `'my-namespace/herbs`

3. To check the vector with the contents, execute the following:
   ```
   herbs
   ```
   The output is as follows:
   ```clojure
   ["coriander" "basil" "mint" "sage"]
   ```

4. Now change into a new namespace as follows:
   ```
   (in-ns 'my-garden)
   ```
5. Now, try executing `herbs` again and observe the output:
    ```clojure
    my-garden=> herbs
    ```

   The output is as follows:
   _Unable to resolve symbol: herbs in this context._

63. If you type the following using the whole symbol qualfier then it will return the contents:
   ```
   my-namespace/herbs
   ```
  The contents are as follows:
   ```
  ["coriander" "basil" "mint" "sage"]
   ```
By completing this exercise, we have #TODO
### Exercise 3: Using `refer`

Namespaces are `clojure.lang.Namespace` objects created by the `ns` macro. Functionally speaking they are a storage location which mostly contains vars and references to vars in other
namespaces. With `refer` you can reference functions and objects from other namespaces.

1. Switch into the namespace from above `user=> (in-ns 'my-garden)`
2. Create a var for herbs `my-garden=> (def herbs ["coriander" "cumin" "basil" "sage"])`
3. One for vegetables `my-garden=> (def vegetables ["carrot" "cucumber" "beetroot" "parsnip"])
4. Create and switch into a namespace `my-garden=> (in-ns 'my-kitchen)`
5. Using `refer` to include all the objects from the `my-garden namespace` to be
available within the newly created one `my-kitchen=> (clojure.core/refer 'my-garden)`
6. Now from within this namespace `my-kitchen=>` when you type `herbs` at the prompt It’ll output 
   ```
   ["coriander" "basil" "mint" "sage"]
   ```
7. When you4type at the `my-kitchen=>` prompt `vegatables`
   It’ll output:
   ```clojure
   ["carrot" "cucumber" "beetroot" "parsnip"]
   ```

Here we created two vectors inside the namespace `my-garden`: `herbs` and `vegatables`.
Using `refer` with a namespace symbol we included all the objects from this namespace into our current one without having to use the whole symbol qualifier.
## Activity 1: Using filters with `refer`

In this activity, we apply filters in combination with refer. Refer accepts a variety of filters as
arguments. For example, you can pass it the filters: `:only`, `:exclude`, and: `:rename`.
These steps will help you complete the activity:
1. Create two vectors with `def` in one namespace, then switch to a newly created namespace using `refer` with a filter to include only one of them into your current namespace.

Expected Outcome:

The solution to the activity:
The filters `:only` and `:exclude` limit which vars are included into the current namespace. `:rename` lets you rename symbols when including them in the current namespace.

## Organizing application code

We have seen the basics of how to organize our code in Clojure. Now we take a closer look at how a Clojure project is structured, the relationship between file paths and namespace names, and how to use leiningen to set up our application.

### Exercise 4: Create a project using leiningen
To set up a project using leiningen, perform the following steps:
1. Open the Command Prompt
2. Create a new project with leiningen called my-project.
   Enter 
   ```bash
   lein new app my-project
   ```
   It should return: _Generating a project called my-project based on the 'app' template._
8. Change into the newly created directory. Enter `cd my-project`
   We’ve used lein to provide us with a template for a project structure. The project layout should look like this:
    ```
    my-project/
    ├── CHANGELOG.md
    ├── LICENSE
    ├── README.md
    ├── doc/
    │ └── intro.md
    ├── project.clj
    ├── resources/
    ├── src/
    │ └── my_project/
    │   └── core.clj
    └── test/
      └── my_project/
        └── core_test.clj
    ```

  It created a directory structure and files with the necessary scaffolding to start a project. The `src/` directory contains our application code, and in the `test/` directory we later place the tests for our application. The `project.clj` file is the main file describing the project and to manage dependencies including additional libraries.

9. Open the file contained in `src/my_project/` named `core.clj` with your Editor. Take a look at the contents of that file. The first line
  ```clojure
  (ns my-project.core
  (:gen-class))
  ```
  declares the namespace with the name `my-project.core` using the `ns` macro. The `src/my_project/core.clj` file corresponds to the `my-project.core` namespace. The underscores in the file path map to the dashes in the namespace name, and the separator for the directory (depending on your operating system, here it is `/`) is equivalent the period `.` in the namespace name.

### Exercise 5: Running the app on the command line
In this exercise, we write a small command line application that takes a string as input, parses that input and replaces the contents of that string.

1. Open your `src/my_project/core.clj` file in your Editor
2. Replace the line with `(:gen-class)` with `(:require [clojure.string :as str])` (and don't forget the closing bracket for the `ns` macro at the end).

   Here we use `:require` to include string manipulation utilities from Clojure’s core string library which we refer to with `str` string
3. Replace the main function that was generated when creating the project

   ```clojure
    (defn -main
      "I don't do a whole lot ... yet."
      [& args]
      (println "Hello, World!"))
    ```
    with
    
    ```clojure
    (defn -main 
      "I don't do a whole lot ... yet."
      [& args]
        (-> (str/join " " args)
          (str/replace "melon" "banana")
          (str/replace "apple" "orange")
            println))

    ```

  Our main function takes an arbitrary list of arguments, combines all the arguments into one string and using the threading macro replaces any occurrences of "melon" with "banana" and any occurrences of "apple" with "orange". Go to the command line and run the code:

  ```clojure
  lein run "apple" "melon"

  ```
4. In the above example we used `:require` to add additional utilities to the namespace. Earlier when we navigating namespaces in the REPL we could do the same:

  ```clojure
  (in-ns 'namespace.test)
  (require 'clojure.string)
  ```

is about equivalent to

  ```clojure
  (ns namespace.test
    (:require clojure.string))
 ```
 
  

## Activity 2: Extend the command line application

In this activity, we extend the application we created above to take integers as inputs and sum them up to print the result to the console.

## Libraries

## Exercise 6: Using external libraries

In this exercise, we continue working on the command line application, extending it to take as
input a link to a website, parsing the table we find on the website and printing the table to the
command line. First, we need to add a new dependency to the project.clj file. We add two
libraries: hickory a HTML parser and clj-http an HTTP client which we need in order
to call the webpage.

1. Open the `project.clj` file in your Editor, below `:dependencies`
2. Run `lein deps` to install the libraries in your project.
3. Open your `src/my_project/core.clj` file in your Editor
