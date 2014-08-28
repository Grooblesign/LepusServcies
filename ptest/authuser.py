import httplib, urllib
import sys

params = urllib.urlencode({'username': sys.argv[1], 'password': sys.argv[2]})

headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "application/xml"}

#conn = httplib.HTTPConnection("localhost", 8080)
conn = httplib.HTTPConnection("10.22.99.182")

conn.request("POST", "/lepusservices/rest/authentication/user", params, headers)

response = conn.getresponse()
print response.status, response.reason

data = response.read()
print data