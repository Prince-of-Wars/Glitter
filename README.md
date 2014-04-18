Glitter
=======

A simple IDE written in Java that was created as a fun learning experience. Not intended to replace your current IDE.

[![Build Status](https://travis-ci.org/GlitterIDE/Glitter.svg?branch=master)](https://travis-ci.org/GlitterIDE/Glitter)

Notice
------

This project is still in its infancy. Many features are incomplete or not yet implemented. Please be patient. Feel free to submit pull requests if you'd like to help out.

Contents
--------

Glitter is broken up into several Maven modules.

* **Syntext**: A library used to segment text using [regular expressions](https://en.wikipedia.org/wiki/Regular_expression)
* **API**: An API that allows programmers to write their own extensions
* **Editor**: The main application and implementation

Compilation
-----------

We use maven to handle our dependencies.

* Install [Apache Maven](https://maven.apache.org/download.cgi)
* Check out this repo and run `mvn clean install`

License
-------

This project is licensed under the [Apache License](https://github.com/GlitterIDE/Glitter/blob/master/LICENSE).