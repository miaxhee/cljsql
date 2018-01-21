-- :name save-message! :! :n
-- :doc creates a new message using the name, message, and timestamp keys
INSERT INTO cljsql
(name, url, message, timestamp)
VALUES (:name, :url, :message, :timestamp)

-- :name get-messages :? :*
-- :doc selects all available messages
SELECT * from cljsql

