#!/usr/bin/python3
def adder(x,y):
    str1 = x[::-1]
    str2 = y[::-1]
    result = ""
    carry = 0
    for i in range(len(str1)):
        value1 = int(str1[i])
        value2 = int(str2[i])
        if value1+value2+carry == 2:
            result += "0"
            carry = 1
        elif value1+value2+carry == 3:
            result += "1"
            carry = 1
        else:
            result += str(value1+value2+carry)
            carry = 0
    if carry!=0:
        result += str(carry)
    return result[::-1]

num1 = input("Enter your first binary number: ")
num2 = input("Enter your second binary number: ")
print(adder(num1,num2))
state = input("Continue?[Y/n]").strip().lower()
while state == "y":
    num1 = input("Enter your first binary number: ")
    num2 = input("Enter your second binary number: ")
    print(adder(num1,num2))
    state = input("Continue[Y/n]?").strip().lower()


