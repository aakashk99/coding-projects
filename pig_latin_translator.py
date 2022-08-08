import time
def pig_latin(sentence):
	words = sentence.split(" ")
	pig = []
	vowels = ["a", "e", "i", "o", "u", "A", "E", "I", "O", "U"]
	for word in words:
		if word[0] in vowels:
			pword = word + "-yay"
			pig.append(pword)
		else:
			pigword = word + "-" + word[0] + "ay"
			pigw = pigword[1:]
			pig.append(pigw)
	newsentence = " ".join(pig)
	print(newsentence)

state = input('Start?[Y/N] ').lower().strip()
while state == "y":
	sent = input('Please type a sentence to translate: ')
	print(pig_latin(sent))
	state = input('Continue[Y/N] ').lower().strip()
else:
	print('Thank You. Hope your experience was satisfactory.')
	time.sleep(3)
