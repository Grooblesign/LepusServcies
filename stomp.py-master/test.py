import time
import sys

import stomp

class MyListener(object):
    def on_connecting(self, host_and_port):
        print('on_connecting %s %s' % host_and_port)
    def on_error(self, headers, message):
        print('received an error %s' % (headers, message))
    def on_message(self, headers, body):
        print('on_message %s %s' % (headers, body))
    def on_heartbeat(self):
        print('on_heartbeat')
    def on_send(self, frame):
        print('on_send %s %s %s' % (frame.cmd, frame.headers, frame.body))
    def on_connected(self, headers, body):
        print('on_connected %s %s' % (headers, body))
    def on_disconnected(self):
        print('on_disconnected')
    def on_heartbeat_timeout(self):
        print('on_heartbeat_timeout')
    def on_before_message(self, headers, body):
        print('on_before_message %s %s' % (headers, body))
        return (headers, body)

conn = stomp.Connection()
conn.set_listener('', MyListener())
conn.start()
conn.connect()

conn.subscribe(destination=sys.argv[1], id=1, ack='auto')

#conn.send(body=' '.join(sys.argv[1:]), destination='testQueue')

time.sleep(30)
conn.disconnect()