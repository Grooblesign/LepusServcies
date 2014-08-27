import httplib, urllib
import sys

params = urllib.urlencode({'message': sys.argv[1]})
headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}
conn = httplib.HTTPConnection("localhost", 8080)
conn.request("POST", "/lepusservices/rest/mail/messagesXML", params, headers)
response = conn.getresponse()
print response.status, response.reason
data = response.read()
print data