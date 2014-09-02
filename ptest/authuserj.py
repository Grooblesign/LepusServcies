import json
import urllib2
import sys

data = {'username':sys.argv[1],'password':sys.argv[2]}
data = json.dumps(data)

#url = "http://localhost:8080/lepusservices/rest/authentication/user"
url = "http://10.22.99.182/lepusservices/rest/authentication/user"

req = urllib2.Request(url, data, {'Content-Type': 'application/json'})

f = urllib2.urlopen(req)
response = f.read()
f.close()

print response

