# Exemplo de chamada para api

```
curl --location 'http://localhost:8080/api/v1/anagrams' \
--header 'Content-Type: application/json' \
--data '{
    "letters": [
        "a",
        "m",
        "o",
        "r"
    ]
}'
```
