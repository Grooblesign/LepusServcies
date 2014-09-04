import urllib2
import sys

data = "<mailmessage><id>0001</id><from>Paul</from><to>Taz</to><subject>Walk</subject><body>Hello test</body></mailmessage>"

url = "http://localhost:8161/cxfrest/rest/mail"
#url = "http://10.22.99.182/lepusservices/rest/authentication/user"

req = urllib2.Request(url, data, {'Content-Type': 'application/xml'})

f = urllib2.urlopen(req)
response = f.read()
f.close()

print response
