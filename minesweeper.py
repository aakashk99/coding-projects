from tkinter import *

game = Tk()

#Game Settings
Width = 1440
Height = 720

#Settings of the Window
game.configure(bg='green') #Color of the Background
game.geometry(f'{Width}x{Height}') #Size of the Window
game.title('Minesweeper') #Title of the Window
game.resizable(False, False) #Window not resizable

#Settings of Top Frame
top_frame = Frame(
    game,
    bg = 'white', #Color of TopFrame
    width = Width, #Width of window
    height = 180 #Height of window
)
top_frame.place(x=0, y=0) #Location of TopLeft Corner of window for Top Frame

#Settings of Side Frame
side_frame = Frame(
    game,
    bg = 'white', #Color of TopFrame
    width = 360, #Width of window
    height = Height - 180 #Height of window
)

side_frame.place(x=0, y=180) #Location of TopLeft Corner of window for Side Frame


#Code that Runs the Window
game.mainloop()
