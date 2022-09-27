import time
def verifier(word):
    reversedWord = word[::-1]
    if word == reversedWord:
        return True

state = input("Begin?[Y/n]").strip().lower()
while state == "y":
    word = input("Please enter the word you wish to check: ")
    if verifier(word):
        print("Your word is a palindrome")
    else:
        print("Your word is not a palindrome")
    state = input("Continue?[Y/n]").strip().lower()
if state == "n":
    print("Thank you. Hope your experience was satisfactory")
    time.sleep(2)
else:
    print("Sorry. I don't understand")
    state = input("Begin?[Y/n]")
