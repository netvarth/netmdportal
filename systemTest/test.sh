curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jbos/workspace/youNeverWait1.0/systemTest/login.txt http://192.168.1.99:8080/youNeverWait/ws/ui/auth/netlimsLogin -c cookies.txt
curl -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jbos/workspace/youNeverWait1.0/systemTest/branchList.txt  http://192.168.1.99:8080/youNeverWait/ws/ui/lab/branchList -b cookies.txt
curl  http://192.168.1.99:8080/youNeverWait/ws/ui/auth/logout -b cookies.txt