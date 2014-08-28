import httplib, urllib
import sys

params = urllib.urlencode({'wibble': sys.argv[1]})

headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "application/xml"}

#conn = httplib.HTTPConnection("localhost", 8080)
conn = httplib.HTTPConnection("10.22.99.182")

conn.request("POST", "/lepusservices/rest/authentication/wibble", params, headers)

response = conn.getresponse()
print response.status, response.reason

data = response.read()
print data