from tkinter import *

game = Tk()

#Settings of the Window
game.configure(bg='green') #Color of the Background
game.geometry('1440x720') #Size of the Window
game.title('Minesweeper') #Title of the Window
game.resizable(False, False) #Window not resizable

top_frame = Frame(
    game,
    bg = 'white', #Color of TopFrame
    width = 1440, #Width of window
    height = 180 #Height of window
)
top_frame.place(x=0, y=0) # Location of Left Corner of window for Top Frame

side_frame = Frame(
    game,
    bg = 'white',
    width = 360,
    height = 540
)

side_frame.place(x=0, y=180)

#Code that Runs the Window
game.mainloop()
