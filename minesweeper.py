from tkinter import *

game = Tk()

#Game Features
Width = 1440
Height = 720
Grid_Size = 5
class Cell:
    all = []
    def __init__(self, x, y, is_mine=False):
        self.is_mine = is_mine
        self.cell_button = None
        self.x = x
        self.y = y
        Cell.all.append(self)

    def left_click(self, event):
        print(event)
        print("I am left clicked")

    def right_click(self, event):
        print(event)
        print("I am right clicked")

    @staticmethod
    def randomize_mines(): #Randomizes location of mines
        pass

    def create_button(self, location):
        self.cell_button = Button(
            location,
            width = 12,
            height = 4,
            text = f'{self.x}, {self.y}'
        )
        self.cell_button.bind('<Button-1>', self.left_click)
        self.cell_button.bind('<Button-3>', self.right_click)

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

#Populating Cells
for x in range(Grid_Size):
    for y in range(Grid_Size):
        c = Cell(x, y)
        c.create_button(center_frame)
        c.cell_button.grid(
            column = y, row = x
        )

#Code that Runs the Window
game.mainloop()
