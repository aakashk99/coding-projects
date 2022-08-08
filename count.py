import time

def count_up_to(number):
	lis = []
	for x in range(1, number+1):
		lis.append(str(x))
	order = " ".join(lis)
	return order
def count_down_from(number):
    lis = []
    for x in range(1,number+1):
            lis.append(str(x))
    order = list(reversed(lis))
    say = " ".join(order)
    return say

status = input("Start[Y/N] ").strip().lower()
while status == "y":
	try:
		num = int(input("Select a number ").strip())
	except ValueError:
		print("ERROR: Please Input a Number ")
		time.sleep(3)
		num = int(input("Select a number").strip())
	Option = input("Count Down or Count Up? ").strip().replace(" ", "").lower()
	if Option == "countdown":
		print(count_down_from(num))
	elif Option == "countup":
		print(count_up_to(num))
	else:
		print("Sorry,I do not understand. ")
	status = input("Continue[Y/N]").strip().lower()
else:
	print("Thank You. Hope your experience was satisfactory!")
	time.sleep(1)
