import time
def toseconds(hours, minutes, seconds):
	total = hours*3600 + minutes*60 +seconds
	return "That's {} seconds".format(total)

print("Welcome to the program")
state = input('Start?[Y/N] ').strip().lower()
while state == "y":
	h = int(input('Number of Hours: '))
	m = int(input('Number of Minutes: '))
	s = int(input('Number of Seconds: '))
	print(toseconds(h, m, s))
	state = input('Continue[Y/N] ').strip().lower()
else:
	print("Thank You. I hope your experience was satisfactory")
	time.sleep(3)
