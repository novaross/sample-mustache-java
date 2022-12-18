# Sample of Java Code Generation with Mustache

This repository contains a code sample which demonstrates how Java projects can be dynamically generated using the
mustache templating engine.  
Feel free to explore the codebase for ideas.

## Running the Project

- Clone the repository to your machine
- Build the project by executing `./gradlew build`
- Run the project by executing `./gradlew run`
- The generated java project is created in the `c:/temp/generated` folder

## Template Writer

Most of the work is performed in the `TemplateWriter.java` class.  
Conceptual flow:

- Iterate over the files in the template folder
- Copy any static files to the destination folder
- Renter and then copy `mustache` files to the destination folder

## References

- [Mustache Templating Engine][mustache-website]

[mustache-website]: https://mustache.github.io/
