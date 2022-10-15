import numpy as np
import math

#calculates if a number is a prime
def checkprime(number):
    value = abs(np.cos(((np.math.factorial((number-1))+1)/number)*np.pi))
    if value == 1:
        return True
    else:
        return False

state = input("Welcome to Prime Checker. Begin?[Y/n] ").lower().strip()
while state == "y":
    number = int(input("Please enter a number: "))
    if number >= 17:
        print("Sorry your number is too large")
    elif checkprime(number):
        print("Your number is a prime!")
    else:
        print("Your number is not a prime")
    state = input("Continue?[Y/n]").strip().lower()
if state == "n":
    print("Thank you for using Prime Checker. Hope your experience was satisfactory.")
else:
    print("Sorry I do not understand")
