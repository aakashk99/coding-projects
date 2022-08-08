import re
import time
def word_frequency(text):
    new = re.sub(r'[^\w\s]', '', text).lower()
    t = new.split()
    d = {}
    for word in t:
        if word not in d:
            d[word] = 1
        else:
            d[word] += 1
    frequencies = sorted(d.items(), key = lambda x:x[1], reverse = True)
    return frequencies

state = input("Welcome. \n Would you like to begin?[Y/N] ").strip().lower()
while state == "y":
    statement = input("Please paste your selected text. ")
    print(word_frequency(statement))
    state = input("Countinue?[Y/N] ").strip().lower()
if state == "n":
    print("Thank You. Hope your experience was satisfactory. ")
    time.sleep(3)
