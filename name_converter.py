import time
import re
def name_converter(text):
    n = re.search(r"([A-Za-z. ]*) ([A-Za-z]*)", text)
    print("{}, {}".format(n[2], n[1]))
state = input('Welcome. Start[Y/N] ').strip().lower()
while state == "y":
    name = input("Please type your name.")
    name_converter(name)
    state = input("Continue[Y/N] ").lower().strip()
else:
    print('Thank You. Hope your experience was satisfactory. ')
    time.sleep(3)
