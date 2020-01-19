# test cases

1.
curl -X POST "http://localhost:8080/user/user1" -H "Content-Type: application
/json" -d '{"username": "paul1", "address": "oisjfsf"}'

{"id":null,"username":"paul1","address":"oisjfsf"}

2.
curl -X POST "http://localhost:8080/user/user2" -H "Content-Type: application
/json" -d '{"username": "paul2", "address": "jirhiasfasf"}'

{"id":2,"username":"paul2","address":"jirhiasfasf"}

3.
curl -X GET "http://localhost:8080/user/all1"

[{"id":1,"username":"paul1","address":"oisjfsf"},{"id":2,"username":"paul2","address":"jirhiasfasf"}]

4.
curl -X GET "http://localhost:8080/user/all2"

[{"id":1,"username":"paul1","address":"oisjfsf"},{"id":2,"username":"paul2","address":"jirhiasfasf"}]

5.
curl -X PUT "http://localhost:8080/user/2" -H "Content-Type: application/json
" -d '{"username": "paul3", "address": "osf38hif"}'

{"id":2,"username":"paul3","address":"osf38hif"}

6.
curl -X DELETE "http://localhost:8080/user/2"
