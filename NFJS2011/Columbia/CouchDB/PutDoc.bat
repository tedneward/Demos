@ECHO ON

@IF "%1" == "" goto usage
@IF "%2" == "" goto usage

curl -X PUT http://localhost:5984/%1 -d @%2 -H "Content-Type: application/json"
@goto end

:usage
@ECHO PutDoc database-and-url document

:end
