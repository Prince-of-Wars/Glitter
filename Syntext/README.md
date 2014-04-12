Syntext
=======

A library used to segment text using [regular expressions](https://en.wikipedia.org/wiki/Regular_expression)

Usage
-----

Use the `Syntext.create(String name)` method to create an empty syntax definition. To fill this `SyntaxDefinition`
instance with rules, call the `createRule(String name, String regex)` method. To parse a string based on your defined
rules, call the `match(String content)` method. You can also parse input based on a single rule by using the
`match(String content)` method your `SyntaxRule` instance.