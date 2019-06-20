curl -v -X GET -H "Content-Type: application/json" http://localhost:8085/api/product/v1/details/Prod3/
        
        
curl -v -X POST -H "Content-Type: application/json" \
        --data-binary '{"id":"Prod3","name":"Product3","price":{"amount":105,"currency":"USD"},"errorCode":0,"errorMessage":null}' \
        http://localhost:8085/api/product/v1/createdproduct/Prod3/ 
        
        
curl -v -X PUT -H "Content-Type: application/json" \
        --data-binary '{"id":"Prod3","name":"Product3","price":{"amount":110,"currency":"USD"},"errorCode":0,"errorMessage":null}' \
        http://localhost:8085/api/product/v1/updateproduct/Prod3/

todo
 future based calls
 error handling and code cleanup 
