#!/usr/bin/python3
import pygame
import time

#Initiate game window
pygame.init()
screen = pygame.display.set_mode((500,600))

#Create Button Class
class Button:
    def __init__(self, position, font, color, text, size, reference):
        self.x, self.y = position
        self.size = size
        if text == "O":
            self.centerx = (self.size[0]/2 - self.font.size("O")[0]/2)
            self.centery = (self.size[1]/2 - self.font.size("O")[1]/2)
        elif text == "X":
            self.centerx = (self.size[0]/2 - self.font.size("O")[0]/2)
            self.centery = (self.size[1]/2 - self.font.size("O")[1]/2)
        else:
            self.centerx = self.size[0]/2
            self.centery = self.size[0]/2 
        self.reference = reference
        self.font = pygame.font.SysFont("Arial", font)
        self.mark = text
        self.text = self.font.render(text,1, pygame.Color("Black"))
        self.color = color
        self.rect = pygame.Rect(self.x, self.y, self.size[0], self.size[1])
    def changeText(self, text):
        self.mark = text
        self.text = self.font.render(text,1, pygame.Color("Black"))
        if text == "O":
            self.centerx = (self.size[0]/2 - self.font.size("O")[0]/2)
            self.centery = (self.size[1]/2 - self.font.size("O")[1]/2)
        elif text == "X":
            self.centerx = (self.size[0]/2 - self.font.size("X")[0]/2)
            self.centery = (self.size[1]/2 - self.font.size("X")[1]/2)
        else:
            self.centerx = self.size[0]/2 - self.font.size(text)[0]/2
            self.centery = self.size[1]/2 - self.font.size(text)[1]/2
    def show(self):
        self.surface = pygame.Surface(self.size)
        self.surface.fill(self.color)
        self.surface.blit(self.text, (self.centerx, self.centery))
        screen.blit(self.surface, (self.x,self.y))
        pygame.display.update()
    def click(self, player):
        if player == 0 and self.reference not in usedMove:
            self.changeText("O")
            self.show()
            pygame.display.update()
            print("Click1")
        elif player == 1 and self.reference not in usedMove:
            self.changeText("X")
            self.show()
            pygame.display.update()
            print("Click2")
        usedMove.append(self.reference)

#Create Used Move List to track used moves                
usedMove = []

#Instantiate the Tic-Tac-Toe Grid
font = 50 
moveIndicator = Button((0, 15), font, "White", "", (500, 70), "Move")           
result = Button((0,455), font, "White", "", (500, 90), "Result")
A1 = Button((90,105), font, "White", "", (100,100), "A1")
A2 = Button((200,105), font, "White", "", (100,100), "A2")
A3 = Button((310,105), font, "White", "", (100,100), "A3")
B1 = Button((90,215), font, "White", "", (100,100), "B1")
B2 = Button((200,215), font, "White", "", (100,100), "B2")
B3 = Button((310,215), font, "White", "", (100,100), "B3")
C1 = Button((90,325), font, "White", "", (100,100), "C1")
C2 = Button((200,325), font, "White", "", (100,100), "C2")
C3 = Button((310,325), font, "White", "", (100,100), "C3")

result.show()
moveIndicator.show()

A1.show()
A2.show()
A3.show()
B1.show()
B2.show()
B3.show()
C1.show()
C2.show()
C3.show()

#Initialize Game
game = True
player = 0 
while game:
    result.changeText("Game In Progress")
    result.show()
    x,y = pygame.mouse.get_pos()
    pygame.display.update()

    #Player 1 Move
    if player == 0:
        moveIndicator.changeText("Player 1 Move")
        moveIndicator.show()
        if pygame.event.poll().type == pygame.MOUSEBUTTONDOWN and pygame.mouse.get_pressed()[0]:
            if A1.rect.collidepoint(x,y) and A1.reference not in usedMove:
                A1.changeText("O")
                A1.show()
                usedMove.append(A1.reference)
                player = 1
            elif A2.rect.collidepoint(x,y) and A2.reference not in usedMove:
                A2.changeText("O")
                A2.show()
                usedMove.append(A2.reference)
                player = 1
            elif A3.rect.collidepoint(x,y) and A3.reference not in usedMove:
                A3.changeText("O")
                A3.show()
                usedMove.append(A3.reference)
                player = 1
            elif B1.rect.collidepoint(x,y) and B1.reference not in usedMove:
                B1.changeText("O")
                B1.show()
                usedMove.append(B1.reference)
                player = 1
            elif B2.rect.collidepoint(x,y) and B2.reference not in usedMove:
                B2.changeText("O")
                B2.show()
                usedMove.append(B2.reference)
                player = 1
            elif B3.rect.collidepoint(x,y) and B3.reference not in usedMove:
                B3.changeText("O")
                B3.show()
                usedMove.append(B3.reference)
                player = 1
            elif C1.rect.collidepoint(x,y) and C1.reference not in usedMove:
                C1.changeText("O")
                C1.show()
                usedMove.append(C1.reference)
                player = 1
            elif C2.rect.collidepoint(x,y) and C2.reference not in usedMove:
                C2.changeText("O")
                C2.show()
                usedMove.append(C2.reference)
                player = 1
            elif C3.rect.collidepoint(x,y) and C3.reference not in usedMove:
                C3.changeText("O")
                C3.show()
                usedMove.append(C3.reference)
                player = 1

    #Player 1 Wins
    if A1.mark == "O" and A2.mark == "O" and A3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if B1.mark == "O" and B2.mark == "O" and B3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if C1.mark == "O" and C2.mark == "O" and C3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A1.mark == "O" and B1.mark == "O" and C1.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A2.mark == "O" and B2.mark == "O" and C2.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A3.mark == "O" and B3.mark == "O" and C3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A1.mark == "O" and B2.mark == "O" and C3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if C1.mark == "O" and B2.mark == "O" and A3.mark == "O":
        result.changeText("Player 1 Wins!")
        result.show()
        time.sleep(3)
        game = False

    #Player 2 Moves
    if player == 1:
        moveIndicator.changeText("Player 2 Move")
        moveIndicator.show()
        if pygame.event.poll().type == pygame.MOUSEBUTTONDOWN and pygame.mouse.get_pressed()[0]:
            if A1.rect.collidepoint(x,y) and A1.reference not in usedMove:
                A1.changeText("X")
                A1.show()
                usedMove.append(A1.reference)
                player = 0
            elif A2.rect.collidepoint(x,y) and A2.reference not in usedMove:
                A2.changeText("X")
                A2.show()
                usedMove.append(A2.reference)
                player = 0
            elif A3.rect.collidepoint(x,y) and A3.reference not in usedMove:
                A3.changeText("X")
                A3.show()
                usedMove.append(A3.reference)
                player = 0
            elif B1.rect.collidepoint(x,y) and B1.reference not in usedMove:
                B1.changeText("X")
                B1.show()
                usedMove.append(B1.reference)
                player = 0
            elif B2.rect.collidepoint(x,y) and B2.reference not in usedMove:
                B2.changeText("X")
                B2.show()
                usedMove.append(B2.reference)
                player = 0
            elif B3.rect.collidepoint(x,y) and B3.reference not in usedMove:
                B3.changeText("X")
                B3.show()
                usedMove.append(B3.reference)
                player = 0
            elif C1.rect.collidepoint(x,y) and C1.reference not in usedMove:
                C1.changeText("X")
                C1.show()
                usedMove.append(C1.reference)
                player = 0
            elif C2.rect.collidepoint(x,y) and C2.reference not in usedMove:
                C2.changeText("X")
                C2.show()
                usedMove.append(C2.reference)
                player = 0
            elif C3.rect.collidepoint(x,y) and C3.reference not in usedMove:
                C3.changeText("X")
                C3.show()
                usedMove.append(C3.reference)
                player = 0

    #A2 Wins
    if A1.mark == "X" and A2.mark == "X" and A3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if B1.mark == "X" and B2.mark == "X" and B3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if C1.mark == "X" and C2.mark == "X" and C3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A1.mark == "X" and B1.mark == "X" and C1.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A2.mark == "X" and B2.mark == "X" and C2.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A3.mark == "X" and B3.mark == "X" and C3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if A1.mark == "X" and B2.mark == "X" and C3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False
    if C1.mark == "X" and B2.mark == "X" and A3.mark == "X":
        result.changeText("Player 2 Wins!")
        result.show()
        time.sleep(3)
        game = False

    #Draw
    if len(usedMove) == 9:
        result.changeText("It's a Tie!")
        result.show()
        time.sleep(3)
        game = False