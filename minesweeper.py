from tkinter import *

game = Tk()

#Game Settings
Width = 1440
Height = 720

#Utility Functions
def height_prct(percentage):
    return Height*(percentage/100)

def width_prct(percentage):
    return Width*(percentage/100)

#Settings of the Window
game.configure(bg='black') #Color of the Background
game.geometry(f'{Width}x{Height}') #Size of the Window
game.title('Minesweeper') #Title of the Window
game.resizable(False, False) #Window not resizable

#Settings of Top Frame
top_frame = Frame(
    game,
    bg = 'black', #Color of TopFrame
    width = Width, #Width of window
    height = height_prct(25) #Height of window
)
top_frame.place(x=0, y=0) #Location of TopLeft Corner of window for Top Frame

#Settings of Side Frame
side_frame = Frame(
    game,
    bg = 'black', #Color of TopFrame
    width = width_prct(25), #Width of window
    height = height_prct(75) #Height of window
)
side_frame.place(x=0, y=height_prct(25)) #Location of TopLeft Corner of window for Side Frame

#Settings of Center Frame
center_frame = Frame(
    game,
    bg = 'black',
    width = width_prct(75),
    height = height_prct(75)
)
center_frame.place(x=width_prct(25), y=height_prct(25))
#Code that Runs the Window
game.mainloop()
