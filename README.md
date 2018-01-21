# cljsql

generated using Luminus version "2.9.12.14"

cljsql runs a web server and showing at localhost:3000 a list of URLs which are stored in a H2 db

## Running

To start a web server for the application, run:

    lein run 

## Warning

;; the dev-config.env file is used for local environment variables, such as database credentials.
;; this file is listed in .gitignore and will be excluded from version control by Git.
;; it must be created in the cljsql home dir with content like this

{:database-url "jdbc:h2:./cljsql_dev.db"}

same for test-config.edn

{:database-url "jdbc:h2:./cljsql_test.db"}

	
## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## License

Copyright © 2018 GPL 3
