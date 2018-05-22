# PNLCalculator
Code base for Client & Server code for PnL, Holdings and Cash calculation on Transactions.
This is Spring boot based Java Micro service, which takes JSON input for transactions and outputs JSON of Cash, Pnl and Holdings

Build & Install:

Required Software - Eclipse Oxygen, Java 1.8
1) Clone PnlCalculator Java REST API Service code from github https://github.com/kranthikollu/PNLCalculator.git
2) In Eclipse --> File --> Import --> Git ( Projects from Git) Clone URI --> ( you will see cloned data is copied) --> next (keep defaults) --> next (keep defaults) --> Finish

Note: If you see any issue in finding few jars, please double check the build path and just make sure the folder path is right for the jars. Else delete the folder which has the jars and eclipse refresh project will load the jars in the righ path

3) Run PnlCalculator As Spring Boot Application in eclipse, the server will listen on default Tomcat port 8080

sample request: open postman ( plugin) in chrome browser, use 
url:  http://localhost:8080/pnl/calc , 
type: POST , 
Headers: Content-Type : application/json, 
Body: give the json request ( transactions in JSON)
HIT SEND
see output body in JSON format


Testing:

Add JUNIT Jar & Run PnlCalculatorServiceTest at  com.omega.api.service



