import _thread
import time
import sys
import os
import signal
from datetime import datetime

read_capacity = 0
countdown = False


# Define a function for the thread
def read_dev(thread_name, delay):
    count = 0
    while count < 50000:
        time.sleep(delay)
        count += 1
        print("%s: %s" % (thread_name, time.ctime(time.time())))
        with open("/dev/zero") as f:
            tsp1 = datetime.now()
            value = f.read(2000000)
            tsp2 = datetime.now()
            print("read value time " + str(tsp2.microsecond - tsp1.microsecond) + " microsecond")
            print("value is " + str(sys.getsizeof(value)) + " bytes")
            if countdown:
                global read_capacity
                read_capacity += 2
        f.close()


# Create two threads as followsl
try:
    _thread.start_new_thread(read_dev, ("Thread-0", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-1", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-2", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-3", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-4", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-5", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-6", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-7", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-8", 0.01,))
    _thread.start_new_thread(read_dev, ("Thread-9", 0.01,))
    time.sleep(3)
    countdown = True
    print("start to statistic in 10 seconds: *********************************************************************")
    time.sleep(10)
    countdown = False
    print("total thruput in 10 seconds : " + str(read_capacity) + " megabytes")
    os.kill(os.getpid(), signal.SIGUSR1)
except:
    print("Error: unable to start thread")

while 1:
    pass
